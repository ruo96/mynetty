package com.wrh.designMode.strategyMode.example;
import java.math.BigDecimal;
/**
 * @author wuruohong
 * @Classname QuoteManagerImprove
 * @Description 代码比刚开始的时候要好一点，它把每个具体的算法都单独抽出来作为一个方法，当某一个具体的算法有了变动的时候，只需要修改响应的报价算法就可以了。
 *
 * 但是改进后的代码还是有问题的，那有什么问题呢？
 *
 * 1.当我们新增一个客户类型的时候，首先要添加一个该种客户类型的报价算法方法，然后再quote方法中再加一个else if的分支，是不是感觉很是麻烦呢？
 * 而且这也违反了设计原则之一的开闭原则（open-closed-principle）.
 * 2.我们经常会面临这样的情况，不同的时期使用不同的报价规则，比如在各个节假日举行的各种促销活动时、商场店庆时往往都有普遍的折扣，但是促销时间一旦过去，报价就要回到正常价格上来。
 * 按照上面的代码我们就得修改if else里面的代码很是麻烦
 * 那有没有什么办法使得我们的报价管理即可扩展、可维护，又可以方便的响应变化呢？当然有解决方案啦，就是我们下面要讲的策略模式。
 * @Date 2021/1/28 20:07
 */
public class QuoteManagerImprove {
    public BigDecimal quote(BigDecimal originalPrice, String customType){
        if ("新客户".equals(customType)) {
            return this.quoteNewCustomer(originalPrice);
        }else if ("老客户".equals(customType)) {
            return this.quoteOldCustomer(originalPrice);
        }else if("VIP客户".equals(customType)){
            return this.quoteVIPCustomer(originalPrice);
        }
        //其他人员都是原价
        return originalPrice;
    }

    /**
     * 对VIP客户的报价算法
     * @param originalPrice 原价
     * @return 折后价
     */
    private BigDecimal quoteVIPCustomer(BigDecimal originalPrice) {
        System.out.println("恭喜！VIP客户打8折");
        originalPrice = originalPrice.multiply(new BigDecimal(0.8)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }

    /**
     * 对老客户的报价算法
     * @param originalPrice 原价
     * @return 折后价
     */
    private BigDecimal quoteOldCustomer(BigDecimal originalPrice) {
        System.out.println("恭喜！老客户打9折");
        originalPrice = originalPrice.multiply(new BigDecimal(0.9)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return originalPrice;
    }

    /**
     * 对新客户的报价算法
     * @param originalPrice 原价
     * @return 折后价
     */
    private BigDecimal quoteNewCustomer(BigDecimal originalPrice) {
        System.out.println("抱歉！新客户没有折扣！");
        return originalPrice;
    }
}
