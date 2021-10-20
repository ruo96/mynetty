package com.wrh.aop.jdkaop;

/**
 * @author wuruohong
 * @Classname Star
 * @Description TODO
 * @Date 2021/10/18 16:14
 */
public class Star implements ShowService {

    private String name;

    @Override
    public void sing(String songName) {
        System.out.println(this.name + " sing a song: " + songName);
    }

    @Override
    public void dance() {
        System.out.println(this.name + " dance ");
    }

    public Star(String name) {
        this.name = name;
    }

    public Star() {

    }
}
