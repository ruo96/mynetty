package com.wrh.attach;

import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @author wuruohong
 * @Classname MyAttachMain
 * @Description TODO
 * @Date 2021/12/16 11:59
 */
public class MyAttachMain {
    public static void main(String[] args) {
        VirtualMachine vm = null;
        try {
            vm = VirtualMachine.attach("8516");//MyBizMain进程ID
            vm.loadAgent("D:\\pro\\agent\\target\\agent-0.0.1-SNAPSHOT.jar");//java agent jar包路径
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (vm != null) {
                try {
                    vm.detach();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
