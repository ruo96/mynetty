package com.wrh.algorithum.telephoneCall;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wuruohong
 * @Classname TestTelephoneCall
 * @Description TODO
 * @Date 2021/11/11 15:49
 */
public class TestTelephoneCall {

    @Test
    public void Test12() {
        System.out.println("letterCombinations(\"23\") = " + letterCombinations("23"));

    }

    //BFS
    public List<String> letterCombinations(String digits) {
        LinkedList<String> res = new LinkedList<>();
        //空判断
        if (digits == null || digits.isEmpty()) {
            return res;
        }

        char[][] tab = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
                {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        res.add("");
        while (res.peek().length() != digits.length()) {
            String remove = res.poll();//出队
            System.out.println("remove = " + remove  + "    length: " + remove.length());
            System.out.println("digits.charAt(remove.length()) = " + digits.charAt(remove.length()));
            System.out.println("digits.charAt(remove.length()) - '2' = " + (digits.charAt(remove.length()) - '2'));
            char[] chars = tab[digits.charAt(remove.length()) - '2'];
            System.out.println("chars = " + Arrays.toString(chars));
            //相当于当前节点的所有子节点
            for (int i = 0; i < chars.length; i++) {
                res.add(remove + chars[i]);//入队
            }
            System.out.println("res = " + res);
            System.out.println("======================================");
        }
        return res;
    }

    @Test
    public void Test54() {
        int i = 'a' - 'b';
        System.out.println("i = " + i);
        int j = '1';
        System.out.println("j = " + j);

        final int k = 0;

    }


    public static TreeNode getTreeNode() {
        TreeNode root = new TreeNode();
        root.setVal(0);

        TreeNode left0 = new TreeNode();
        left0.setVal(1);
        TreeNode right0 = new TreeNode();
        right0.setVal(2);

        root.setLeft(left0);
        root.setRight(right0);

        TreeNode left1 = new TreeNode();
        left1.setVal(3);
        TreeNode right1 = new TreeNode();
        right1.setVal(4);

        left0.setLeft(left1);
        left0.setRight(right1);

        TreeNode left2 = new TreeNode();
        left2.setVal(5);
        TreeNode right2 = new TreeNode();
        right2.setVal(6);

        right0.setLeft(left2);
        right0.setRight(right2);

        return root;


    }
    @Test
    public void Test47() {
        TreeNode root = getTreeNode();
        levelOrder(root);
    }
    /** 二叉树的BFS遍历代码如下*/
    public void levelOrder(TreeNode tree) {
        //链表，这里我们可以把它看做队列
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(tree);//相当于把数据加入到队列尾部
        while (!list.isEmpty()) {
            //poll方法相当于移除队列头部的元素
            TreeNode node = list.poll();
            //访问当前节点
            System.out.println(node.getVal());
            //遍历当前节点的左子节点和右子节点
            if (node.getLeft() != null) {
                list.add(node.getLeft());
            }
            if (node.getRight() != null) {
                list.add(node.getRight());
            }
        }
    }

    /**
     * 因为最多有两个子节点所以是二叉树，如果最多有n个子节点我们可以称它为n叉树，
     * 那么n叉树的子节点比较多，我们不可能一次性全部写完，可以使用for循环来遍历，代码如下
     * @param tree
     */
    public void levelOrder1(TreeNode tree) {
        //链表，这里我们可以把它看做队列
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(tree);//相当于把数据加入到队列尾部
        while (!list.isEmpty()) {
            //poll方法相当于移除队列头部的元素
            TreeNode node = list.poll();
            //访问当前节点
            System.out.println(node.getVal());
            //遍历当前节点的所有子节点
            /*for (int i = 0; i < node.child.count; i++) {
                list.add(node.child[i]);
            }*/
        }
    }


    /**
     * 这个是第二种解法 dfs
     * 对于一棵树的遍历，除了BFS以外我们很自然的会想到DFS，这里我们可以把它看做是一棵树的前序遍历。
     * 网上说这种是回溯算法，实际上这里往回走的时候并不需要撤销选择，因为字符串每次都会生成一个新的对象，
     * 所以并不会造成其他分支的污染，关于回溯算法具体可以看下450，什么叫回溯算法，一看就会，一写就废。我们来看下这题的代码
     * @param digits
     * @return
     */
    public List<String> letterCombinations1(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty())
            return res;
        char[][] tab = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
                {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        dfs(res, 0, digits, tab, "");
        return res;
    }

    /**
     * @param res
     * @param index  表示访问到第几个数字了，也可以认为访问到树的第几层了
     * @param digits
     * @param tab
     * @param path   从根节点到叶子结点的路径
     */
    private void dfs(List<String> res, int index, String digits, char[][] tab, String path) {
        //到叶子节点了，就把这条路径选择的字符添加到res中
        if (path.length() == digits.length()) {
            res.add(path);
            return;
        }
        char[] chars = tab[digits.charAt(index) - '2'];
        //访问当前节点的所有子节点
        for (int i = 0; i < chars.length; i++) {
            dfs(res, index + 1, digits, tab, path + chars[i]);
            //因为字符串是创建了一个新的对象，所以这里不需要撤销
        }
    }
}
