package com.wrh.nettyhttpserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author wuruohong
 * @Classname HttpServerInitializer
 * @Description TODO
 * @Date 2022/2/28 20:05
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    private DispatcherServlet servlet;

    public HttpServerInitializer(DispatcherServlet servlet) {
        this.servlet = servlet;
    }
    public HttpServerInitializer() {

    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("encoder", new HttpResponseEncoder());
        pipeline.addLast("aggregator", new HttpObjectAggregator(2147483647));
        pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
        pipeline.addLast("deflater", new HttpContentCompressor());
        pipeline.addLast("handler", new HttpRequestHandler(servlet));
    }
}
