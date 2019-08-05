package com.ying;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 8000;
        Client client = new Client();
        client.start(ip, port);
    }

    public void start(String ip, int port) {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new ImClientHandler());
                    }
                });
        try {
            Channel channel = bootstrap.connect(ip, port).sync().channel();
            for (; ; ) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.next();
                channel.writeAndFlush(input + "\r\n");
                if ("bye".equalsIgnoreCase(input)) {
                    channel.closeFuture().sync();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
