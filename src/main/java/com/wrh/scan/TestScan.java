package com.wrh.scan;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author wuruohong
 * @Classname TestScan
 * @Description TODO
 * @Date 2020/8/28 15:50
 */
@Slf4j
public class TestScan {

    @Test
    public void Test15() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("please input a num: ");
        int sc = myScanner.nextInt();
        System.out.println("input is : "+sc);
    }
}
