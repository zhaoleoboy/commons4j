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
//        for (int i = 0; i < 100; i++) {
        Channel channel = new Client().start(ip, port);
//        }
        Scanner sc = new Scanner( System.in );
        System.out.print( "Please enter a string : " );
        channel.writeAndFlush(sc.next());
    }

    public Channel start(String ip, int port) {
        Channel channel = null;
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
                        ch.pipeline().addLast(new TimeClientHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            future.channel().closeFuture().sync();
            channel = future.channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return channel;
    }
}
