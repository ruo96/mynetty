package com.wrh.designMode.strategyMode.example;

import java.math.BigDecimal;

/**
 * @author wuruohong
 * @Classname QuoteManager
 * @Description 在讲策略模式之前，我们先看一个日常生活中的小例子：
 *
 * 现实生活中我们到商场买东西的时候，卖场往往根据不同的客户制定不同的报价策略，比如针对新客户不打折扣，针对老客户打9折，针对VIP客户打8折...
 *
 * 现在我们要做一个报价管理的模块，简要点就是要针对不同的客户，提供不同的折扣报价。
 * @Date 2021/1/28 20:07
 */
public class QuoteManager {
    public BigDecimal quote(BigDecimal originalPrice, String customType){
        if ("新客户".equals(customType)) {
            System.out.println("抱歉！新客户没有折扣！");
            return originalPrice;
        }else if ("老客户".equals(customType)) {
            System.out.println("恭喜你！老客户打9折！");
            originalPrice = originalPrice.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
            return originalPrice;
        }else if("VIP客户".equals(customType)){
            System.out.println("恭喜你！VIP客户打8折！");
            originalPrice = originalPrice.multiply(new BigDecimal(0.8)).setScale(2,BigDecimal.ROUND_HALF_UP);
            return originalPrice;
        }
        //其他人员都是原价
        return originalPrice;
    }
}
