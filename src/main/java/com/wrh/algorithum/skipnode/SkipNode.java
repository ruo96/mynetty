package com.wrh.algorithum.skipnode;

/**
 * @author wuruohong
 * @Classname SkipNode
 * @Description TODO
 * @Date 2021/3/16 17:50
 */
public class SkipNode<T> {
    int key;
    T value;
    SkipNode right,down;//左右上下四个方向的指针
    public SkipNode (int key,T value) {
        this.key=key;
        this.value=value;
    }
}
