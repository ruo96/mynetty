package com.wrh.thread.piped;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:24 2019/2/18 0018
 * @Modified By:
 */
public class TestPiped {

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();

        out.connect(in);

        Thread printThread = new Thread(new Print(in),"PrintThread");

        printThread.start();

        int receive = 0;
        try{
            while((receive = System.in.read()) != -1){
                System.out.println("输入中..." + receive);
                out.write(receive);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
        }

    }

    static class Print implements Runnable{

        private PipedReader in;

        public Print(PipedReader in){
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;

            try{
                while((receive = in.read()) != -1){
                    System.out.println("读取中..." + receive);
                    System.out.print((char)receive);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
