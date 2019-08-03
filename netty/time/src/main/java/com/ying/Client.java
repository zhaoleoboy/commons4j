package com.ying;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Client {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 8000;
        for (int i = 0; i < 1000; i++) {
            new Client().start(ip, port);
        }
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
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new TimeClientHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }

    }
}
