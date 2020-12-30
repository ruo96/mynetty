package com.wrh.algorithum.editdistance;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestEditDistance
 * @Description TODO
 * @Date 2020/12/28 16:43
 */
@Slf4j
public class TestEditDistance {

    @Test
    public void Test15() {
        System.out.println(minDistanceGood("b",""));

    }

    public int minDistance1(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int dp[][] = new int[n1 + 1][n2 + 1];
        for (int j = 1; j <= n2 ; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        for (int i = 1; i <= n1 ; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[n1][n2];
    }


    /**
     * 编辑距离
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int dp[][] = new int[n1 + 1][n2 + 1];
        for (int j = 1; j <= n2 ; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        for (int i = 1; i <= n1 ; i++) {
            dp[i][0] = dp[i-1][0] + 1;
        }

        for (int i = 1; i <= n1 ; i++) {
            for (int j = 1; j <= n2 ; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[n1][n2];
    }

    public int minDistanceGood(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0) return len2;
        if (len2 == 0) return len1;
        int[] dp = new int[len2 + 1];
        for (int i = 1; i <= len2; i++) {
            dp[i] = i;
        }
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int temp1, temp2, temp3;
        for (int i = 1; i <= len1; i++) {
            temp1 = i;
            temp2 = i - 1;
            for (int j = 1; j <= len2; j++) {
                temp3 = dp[j];
                if (chars1[i - 1] == chars2[j - 1]) {
                    //dp[i][j] = dp[i - 1][j - 1];
                    dp[j] = temp2;
                } else {
                    //dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                    dp[j] = Math.min(Math.min(temp2, temp1), dp[j]) + 1;
                }
                temp1 = dp[j];
                temp2 = temp3;
            }
        }
        return dp[len2];
    }
}
