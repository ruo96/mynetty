package com.wrh.algorithum.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author wuruohong
 * @Classname TestBasicQuestion
 * @Description TODO
 * @Date 2020/2/26 15:09
 */
@Slf4j
public class TestBasicQuestion {

    @Test
    public void Test() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            if(checkIfOdd(i)) {
               log.info(">>> [{}] is odd", i);
            } else {
                log.info(">>> [{}] is not odd", i);
            }
        }
    }

    /**
     * 根据输入整数查看是否是奇数
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            if(checkIfOdd(i)) {
                log.info(">>> [{}] is odd", i);
            } else {
                log.info(">>> [{}] is not odd", i);
            }
        }
    }

    static boolean  checkIfOdd(int i) {
        if ( (i & 1) == 0) {
            return false;
        } else {
            return true;
        }
    }
}
