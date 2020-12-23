package com.wrh.algorithum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wuruohong
 * @Classname TestKuohao
 * @Description TODO
 * @Date 2020/12/22 15:51
 */
public class TestKuohao {

    @Test
    public void Test15() {
        System.out.println(generateParenthesis1(3));
    }

    // 把结果集保存在动态规划的数组里
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        // 这里 dp 数组我们把它变成列表的样子，方便调用而已
        List<List<String>> dp = new ArrayList<>(n);

        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp.add(dp0);

        for (int i = 1; i <= n; i++) {
            List<String> cur = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<String> str1 = dp.get(j);
                List<String> str2 = dp.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        // 枚举右括号的位置
                        cur.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp.add(cur);
        }
        return dp.get(n);
    }


    class Node {
        /**
         * 当前得到的字符串
         */
        private String res;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        public Node(String res, int left, int right) {
            this.res = res;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "res='" + res + "" +
            ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


    /**
     * 方法二：广度优先遍历
     * @param n
     * @return
     */
    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));
        // 总共需要拼凑的字符总数是 2 * n
        n = 2 * n;
        while (n > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curNode = queue.poll();
                if (curNode.left > 0) {
                    queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
                }
                if (curNode.right > 0 && curNode.left < curNode.right) {
                    queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
                }
            }
            n--;
        }

        // 最后一层就是题目要求的结果集
        while (!queue.isEmpty()) {
            res.add(queue.poll().res);
        }
        return res;
    }


    /**
     * 方法一：回溯算法（深度优先遍历）
     * 左右都有可以使用的括号数量，即严格大于 0 的时候，才产生分支；
     * 左边不受右边的限制，它只受自己的约束；
     * 右边除了自己的限制以外，还收到左边的限制，即：右边剩余可以使用的括号数量一定得在严格大于左边剩余的数量的时候，才可以“节外生枝”；
     * 在左边和右边剩余的括号数都等于 0 的时候结算。
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        // 执行深度优先遍历，搜索可能的结果
        dfs("", n, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个没有用掉
     * @param right  右边的括号还有几个没有用掉
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        // 因为是递归函数，所以先写递归终止条件
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 因为每一次尝试，都使用新的字符串变量，所以没有显式的回溯过程
        // 在递归终止的时候，直接把它添加到结果集即可，与「力扣」第 46 题、第 39 题区分

        // 如果左边还有剩余，继续递归下去
        if (left > 0) {
            // 拼接上一个左括号，并且剩余的左括号个数减 1
            dfs(curStr + "(", left - 1, right, res);
        }
        // 什么时候可以用右边？例如，(((((()，此时 left < right，
        // 不能用等号，因为只有先拼了左括号，才能拼上右括号
        if (right > 0 && left < right) {
            // 拼接上一个右括号，并且剩余的右括号个数减 1
            dfs(curStr + ")", left, right - 1, res);
        }
    }
}
