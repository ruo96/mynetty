package com.wrh.algorithum.dongtaiguihua;

import org.junit.Test;

/**
 * @author wuruohong
 * @Classname lineScissor
 * @Description TODO
 * @Date 2021/3/17 18:57
 */
public class lineScissor {
    @Test
    public void Test11() {
        System.out.println("cuttingRope(3) = " + cuttingRope(4));

    }
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for(int i = 3;i <= n;i++) {
            // 下列循环是为了求 dp[i] 多大 所以每次得出一次 tempMAXJ 都得和dp[i] 比较一次，直到找到 dp[i] 最大值
            for (int j = 2; j < i;j++) {
                int tempMAXJ = Math.max((i-j)*j, j * dp[i-j]); // 当前 剪了 j 的时候较大值 j 可以去2 、3 、4。。。每剪一次就有一种乘积的可能
                dp[i] = Math.max(tempMAXJ, dp[i]);
                System.out.println("dp[i] = " + dp[i]);
            }
        }
        return dp[n];
    }
}
