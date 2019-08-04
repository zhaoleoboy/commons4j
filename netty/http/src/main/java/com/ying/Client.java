package com.ying;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.ying.model.User;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

public class Client {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 8000;
        new Client().start(ip, port);
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
                        pipeline.addLast(new HttpResponseDecoder());
                        pipeline.addLast(new HttpRequestEncoder());
                        pipeline.addLast(new HttpObjectAggregator(1024));
                        pipeline.addLast(new HttpClientHandler());
                    }
                });
        try {
            URI uri = new URI("http://127.0.0.1:8000");
            User user = new User();
            user.setName("ying");
            user.setSex("男");
            user.setBirth(new Date());
            byte[] bytes = JSON.toJSONString(user).getBytes();
            Channel channel = bootstrap.connect(ip, port).sync().channel();
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                    uri.toASCIIString(), Unpooled.wrappedBuffer(bytes));
            request.headers().set(HttpHeaderNames.HOST, "127.0.0.1");
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
            channel.writeAndFlush(request);
            channel.close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }

    }
}
