package com.ying;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * 服务端
 */
public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        server.start(8000);
    }

    public void start(int port) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup(8);
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
                                    //.addLast(new HttpResponseEncoder())
                                    // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
                                    //.addLast(new HttpRequestDecoder())
                                    .addLast(new HttpServerCodec()) //等价于上面两个
                                    .addLast(new HttpObjectAggregator(100 * 1024 * 1024))
                                    .addLast(new HttpServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true);
            Channel channel = bootstrap.bind(port).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}
