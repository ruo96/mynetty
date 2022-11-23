package com.wrh.algorithum.digui;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wuruohong
 * @description :  父子结构 tree  自动构建
 * @Date : 2022/11/21 15:55
 */
public class TreeBuild {

    public List<TreeNode> nodeList = new ArrayList<>();

    public TreeBuild(List<TreeNode> nodeList) {
        this.nodeList = nodeList;
    }


    public List<TreeNode> getRootNode() {
        System.out.println("call getRootNode 1");
        List<TreeNode> rootNodeList = new ArrayList<>();
        for (TreeNode treeNode : nodeList) {
            if (0 == treeNode.getParentId()) {
                rootNodeList.add(treeNode);
            }
        }
        return rootNodeList;
    }

    public List<TreeNode> buildTree() {
        List<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode treeRootNode : getRootNode()) {
            treeRootNode = buildChileTree(treeRootNode);
            treeNodes.add(treeRootNode);
        }
        return treeNodes;
    }

    public TreeNode buildChileTree(TreeNode pNode) {
        List<TreeNode> childTree = new ArrayList<>();
        for (TreeNode treeNode : nodeList) {
            if (treeNode.getParentId().equals(pNode.getId())) {
                childTree.add(buildChileTree(treeNode));
            }
        }
        pNode.setChildren(childTree);
        return pNode;
    }

    public static void main(String[] args) {
        // 模拟测试数据（通常为数据库的查询结果）
        List<TreeNode> treeNodeList = new ArrayList<>();
        treeNodeList.add(new TreeNode(1,0,"顶级节点A"));
        treeNodeList.add(new TreeNode(2,0,"顶级节点B"));
        treeNodeList.add(new TreeNode(3,1,"父节点是A"));
        treeNodeList.add(new TreeNode(4,2,"父节点是B"));
        treeNodeList.add(new TreeNode(5,2,"父节点是B"));
        treeNodeList.add(new TreeNode(6,3,"父节点的ID是3"));

        // 创建树形结构（数据集合作为参数）
        TreeBuild treeBuild = new TreeBuild(treeNodeList);
        // 原查询结果转换树形结构
        treeNodeList = treeBuild.buildTree();
        // AjaxResult：个人封装返回的结果体
        System.out.println("treeNodeList = " + JSON.toJSONString(treeNodeList));
    }
}
