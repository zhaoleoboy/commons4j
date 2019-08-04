package com.ying;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;

public class HttpClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("*********Client Read....****************");
        HttpResponse response = null;
        if (msg instanceof HttpResponse) {
            response = (HttpResponse) msg;
            System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaderNames.CONTENT_TYPE));
        } else if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            ByteBuf byteBuf = content.content();
            System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
            byteBuf.release();
        }
    }
}
