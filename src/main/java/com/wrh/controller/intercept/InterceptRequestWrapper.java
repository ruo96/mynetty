package com.wrh.controller.intercept;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zing
 * @date 2018/7/5 15:41
 */
@Slf4j
public class InterceptRequestWrapper extends HttpServletRequestWrapper {

    private byte[] requestBody = new byte[0];

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public InterceptRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        //缓存请求body
        try {
            log.debug("copy request");
            requestBody = StreamUtils.copyToByteArray(request.getInputStream());
//            log.debug("===>requestBody:{}", Arrays.toString(requestBody));
        } catch (IOException e) {
            log.error("copy request filed:{}", e);
            throw e;
        }

    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new CustomServletInputStream(requestBody);
    }

    /**
     * 重写 getReader()
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public byte[] getRequestBodyByte() throws IOException {
        int contentLength = getContentLength();
        if (contentLength < 0) {
            return ArrayUtils.EMPTY_BYTE_ARRAY;
        }
        byte[] buffer = new byte[contentLength];
        for (int i = 0, readLen; i < contentLength; i += readLen) {

            readLen = getInputStream().read(buffer, i, contentLength - i);
            if (readLen == -1) {
                break;
            }
        }
        return buffer;

    }

    private static class CustomServletInputStream extends ServletInputStream {

        private ByteArrayInputStream buffer;

        public CustomServletInputStream(byte[] contents) {
            this.buffer = new ByteArrayInputStream(contents);
        }

        @Override
        public int read() throws IOException {
            return buffer.read();
        }

        @Override
        public boolean isFinished() {
            return buffer.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            log.warn("Not implemented");
        }
    }
}
