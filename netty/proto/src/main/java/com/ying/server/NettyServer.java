package com.ying.server;

import com.ying.config.NettyProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service("nettyServer")
@Slf4j
public class NettyServer {
    /**
     * 通过springboot读取静态资源,实现netty配置文件的读写
     */
    @Autowired
    private NettyProperties prop;

    private ChannelFuture channelFuture;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    @PostConstruct
    public void init() throws Exception {
        log.info("Setting resource leak detector level to {}", prop.getNetty().getLeakDetectorLevel());
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.valueOf(prop.getNetty().getLeakDetectorLevel().toUpperCase()));

        log.info("Starting Server");
        //创建boss线程组 用于服务端接受客户端的连接
        bossGroup = new NioEventLoopGroup(prop.getNetty().getBossGroupThreadCount());
        // 创建 worker 线程组 用于进行 SocketChannel 的数据读写
        workerGroup = new NioEventLoopGroup(prop.getNetty().getWorkerGroupThreadCount());
        // 创建 ServerBootstrap 对象
        ServerBootstrap b = new ServerBootstrap();
        //设置使用的EventLoopGroup
        b.group(bossGroup, workerGroup)
                //设置要被实例化的为 NioServerSocketChannel 类
                .channel(NioServerSocketChannel.class)
                // 设置 NioServerSocketChannel 的处理器
                .handler(new LoggingHandler(LogLevel.INFO))
                // 设置连入服务端的 Client 的 SocketChannel 的处理器
                .childHandler(new NettyServerInitializer());
        // 绑定端口，并同步等待成功，即启动服务端
        channelFuture = b.bind(prop.getBindPort()).sync();
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    log.info("启动服务器成功！");
                } else {
                    log.error("启动服务器失败，进行断线重连！");
//        future.channel().eventLoop().schedule(new Ru)
                }
            }
        });

    }

    @PreDestroy
    public void shutdown() throws InterruptedException {
        log.info("Stopping Server");
        try {
            // 监听服务端关闭，并阻塞等待
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅关闭两个 EventLoopGroup 对象
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
        log.info("server stopped!");

    }

}