package com.wrh.designMode.zhuangshiqi;

/**
 * @author wuruohong
 * @Classname EmbroideryDecorator
 * @Description TODO
 * @Date 2021/9/23 11:38
 */
public class MickeyDecorator extends Decorator {

    public MickeyDecorator(Clothes clothes) {
        super(clothes);
    }

    @Override
    public void makeClothes() {
        System.out.println("绣米老鼠前");
        super.makeClothes();
        System.out.println("给衣服绣上米老鼠");
    }
}
