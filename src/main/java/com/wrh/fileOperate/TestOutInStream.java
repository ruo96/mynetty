package com.wrh.fileOperate;

import java.io.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 8:40 2019/6/20 0020
 * @Modified By:
 */
public class TestOutInStream {


        public static void main(String[] args) {
            // 初始化
            Wanger wanger = new Wanger();
            wanger.setName("王二");
            wanger.setAge(18);
            System.out.println(wanger);

            // 把对象写到文件中
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("chenmo"));){
                oos.writeObject(wanger);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 从文件中读出对象
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("chenmo")));){
                Wanger wanger1 = (Wanger) ois.readObject();
                System.out.println(wanger1);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


}
