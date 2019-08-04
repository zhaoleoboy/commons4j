package com.ying;

import com.ying.codec.LuckDecoder;
import com.ying.codec.LuckEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class LuckServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LuckDecoder());
        pipeline.addLast(new LuckEncoder());
        pipeline.addLast(new LuckServerHandler());
    }
}
