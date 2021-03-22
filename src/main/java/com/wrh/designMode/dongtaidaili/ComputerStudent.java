package com.wrh.designMode.dongtaidaili;

/**
 * @author wuruohong
 * @Classname ComputerStudent
 * @Description TODO
 * @Date 2021/3/18 10:33
 */
public class ComputerStudent implements Student {

    private String name;

    public ComputerStudent(String name) {
        this.name = name;
    }

    @Override
    public void exam() {
        System.out.println(name + " 参加计算机专业考试");
    }

    @Override
    public void study() {
        System.out.println(name + " 参加学习");
    }
}
