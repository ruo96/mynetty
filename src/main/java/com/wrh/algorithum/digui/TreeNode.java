package com.wrh.algorithum.digui;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/11/21 15:54
 */
@Setter
@Getter
public class TreeNode {

    private Integer id;

    private Integer parentId;

    private String label;

    private List<TreeNode> children;

    public TreeNode(Integer id, Integer parentId, String label) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
    }
}
