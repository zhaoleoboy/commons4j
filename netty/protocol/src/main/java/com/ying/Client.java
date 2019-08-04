package com.ying;

import com.ying.model.LuckHeader;
import com.ying.model.LuckMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.UUID;

public class Client {

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 8000;
//        for (int i = 0; i < 100; i++) {
        Client client = new Client();
        client.start(ip, port);
//        }
    }

    public void start(String ip, int port) {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new LuckClientInitializer());
        try {
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            Channel channel = future.channel();

            int version = 1;
            String sessionId = UUID.randomUUID().toString();
            String content = "I'm the luck protocol!";
            LuckHeader header = new LuckHeader(version, content.length(), sessionId);
            LuckMessage message = new LuckMessage(header, content);

            channel.writeAndFlush(message);

            channel.close();
//            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
