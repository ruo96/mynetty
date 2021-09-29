package com.wrh.designMode.zhuangshiqi;

/**
 * @author wuruohong
 * @Classname Decorator
 * @Description 装饰类
 * @Date 2021/9/23 11:36
 */
public class Decorator implements Clothes {

    private Clothes clothes;

    public Decorator(Clothes clothes) {
        this.clothes = clothes;
    }

    @Override
    public void makeClothes() {
        System.out.println("装饰类 前");
        clothes.makeClothes();
        System.out.println("装饰类 后");
    }
}
