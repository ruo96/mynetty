package com.wrh.algorithum.tree;

import cn.hutool.core.math.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wuruohong
 * @Classname TestTree
 * @Description TODO
 * @Date 2020/12/21 18:42
 */
@Slf4j
public class TestTree {
    @Test
    public void Test11() {
        //根据长度判断树的层数
        System.out.println(Ntimes(3));
    }

    private int getHeightFromLength(int length){
        int a = length + 1;
        return 0;
    }

    private int Ntimes(int index) {
        return 2 << index-1;
    }


    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        //从两个子节点开始判断
        return recur(root.left, root.right);
    }

    public boolean recur(TreeNode left, TreeNode right) {
        //如果左右子节点都为空，说明当前节点是叶子节点，返回true
        if (left == null && right == null) {
            return true;
        }
        //如果当前节点只有一个子节点或者有两个子节点，但两个子节点的值不相同，直接返回false
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        //然后左子节点的左子节点和右子节点的右子节点比较，左子节点的右子节点和右子节点的左子节点比较
        return recur(left.left, right.right) && recur(left.right, right.left);
    }

    public class TreeNode{
        private TreeNode left;
        private TreeNode right;
        private int val;
    }


    /**
     * 队列的解法
     * @param root
     * @return
     */
    public boolean isSymmetricQueue(TreeNode root) {
        //队列
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null)
            return true;
        //左子节点和右子节点同时入队
        queue.add(root.left);
        queue.add(root.right);
        //如果队列不为空就继续循环
        while (!queue.isEmpty()) {
            //每两个出队
            TreeNode left = queue.poll(), right = queue.poll();
            //如果都为空继续循环
            if (left == null && right == null) {
                continue;
            }
            //如果一个为空一个不为空，说明不是对称的，直接返回false
            if (left == null ^ right == null) {
                return false;
            }
            //如果这两个值不相同，也不是对称的，直接返回false
            if (left.val != right.val) {
                return false;
            }
            //这里要记住入队的顺序，他会每两个两个的出队。
            //左子节点的左子节点和右子节点的右子节点同时
            //入队，因为他俩会同时比较。
            //左子节点的右子节点和右子节点的左子节点同时入队，
            //因为他俩会同时比较
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
}
