package com.wrh.designMode.chain;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:26 2019/11/25 0025
 * @Modified By:
 */
public class TestChain {
    public static void main(String[] args) {
        Task task3 = new Task1();
        Task task2 = new Task2(task3);
        Task task1 = new Task3(task2);

        task1.run();
    }
}
