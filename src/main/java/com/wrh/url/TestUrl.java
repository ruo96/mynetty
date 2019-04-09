package com.wrh.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:26 2019/3/12 0012
 * @Modified By:
 */
public class TestUrl {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");

        InputStream is = url.openStream();

        InputStreamReader isr = new InputStreamReader(is,"utf-8");

        BufferedReader br = new BufferedReader(isr);

        String line;

        while(( line = br.readLine()) != null){
            System.out.println(line);
        }
        br.close();
    }
}
