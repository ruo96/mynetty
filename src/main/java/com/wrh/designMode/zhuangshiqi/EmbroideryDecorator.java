package com.wrh.designMode.zhuangshiqi;

/**
 * @author wuruohong
 * @Classname EmbroideryDecorator
 * @Description TODO
 * @Date 2021/9/23 11:38
 */
public class EmbroideryDecorator extends Decorator {

    public EmbroideryDecorator(Clothes clothes) {
        super(clothes);
    }

    @Override
    public void makeClothes() {
        System.out.println("绣花朵 前");
        super.makeClothes();
        System.out.println("给衣服绣上花朵");
    }
}
