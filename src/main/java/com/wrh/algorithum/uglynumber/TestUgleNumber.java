package com.wrh.algorithum.uglynumber;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestUgleNumber
 * @Description TODO
 * @Date 2021/3/15 11:06
 */
@Slf4j
public class TestUgleNumber {

    //我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
    @Test
    public void Test15() {
        System.out.println("nthUglyNumber(1) = " + nthUglyNumber(1));
        System.out.println("nthUglyNumber(2) = " + nthUglyNumber(2));
        System.out.println("nthUglyNumber(3) = " + nthUglyNumber(3));
        System.out.println("nthUglyNumber(4) = " + nthUglyNumber(4));
        System.out.println("nthUglyNumber(5) = " + nthUglyNumber(5));
        System.out.println("nthUglyNumber(6) = " + nthUglyNumber(6));
        System.out.println("nthUglyNumber(7) = " + nthUglyNumber(7));
        System.out.println("nthUglyNumber(8) = " + nthUglyNumber(8));
        System.out.println("nthUglyNumber(9) = " + nthUglyNumber(9));
    }

    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if(dp[i] == n2) a++;
            if(dp[i] == n3) b++;
            if(dp[i] == n5) c++;
        }
        return dp[n - 1];
    }
}
