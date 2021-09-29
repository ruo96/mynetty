package com.wrh.designMode.zhuangshiqi;

/**
 * @author wuruohong
 * @Classname TestZhuangshiqi
 * @Description TODO
 * @Date 2021/9/23 11:40
 */
public class TestZhuangshiqi {
    public static void main(String[] args) {
        Clothes clothes = new MakeClothes();
        clothes = new EmbroideryDecorator(clothes);
        clothes = new MickeyDecorator(clothes);
        System.out.println("开始制作！");
        clothes.makeClothes();
        System.out.println("成品已完成！");
    }
}
