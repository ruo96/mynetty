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

    @Test
    public void Test32() {
     /** 求取最短路径*/
         int[][] arr = {{1,2,3,4},{5,6,7,8}};
        System.out.println("dp(arr) = " + dp(arr));
    }

    public int dp(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        if (m <=0 || n <= 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        // 初始化
        dp[0][0] = arr[0][0];
        // 初始化最左边的列
        for(int i = 1; i < m; i++){
            dp[i][0] = dp[i-1][0] + arr[i][0];
        }
        // 初始化最上边的行
        for(int i = 1; i < n; i++){
            dp[0][i] = dp[0][i-1] + arr[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + arr[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
