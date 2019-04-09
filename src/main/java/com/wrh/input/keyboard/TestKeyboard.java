package com.wrh.input.keyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:42 2019/3/12 0012
 * @Modified By:
 */
public class TestKeyboard {

    public static void main(String[] args) throws IOException {

        //方法1
        /*Scanner input = new Scanner(System.in);
        while(true){

            String s =  input.nextLine();
            System.out.println("s is : " + s);
        }*/

//方法2
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while(true){

            String s = input.readLine();
            System.out.println(" s is : " + s);
        }
    }
}
