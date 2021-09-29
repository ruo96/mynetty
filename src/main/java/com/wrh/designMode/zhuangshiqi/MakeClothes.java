package com.wrh.designMode.zhuangshiqi;

/**
 * @author wuruohong
 * @Classname MakeClothes
 * @Description 被装饰类
 * @Date 2021/9/23 11:36
 */
public class MakeClothes implements Clothes {
    @Override
    public void makeClothes() {
        System.out.println("制作一件衣服");
    }
}
