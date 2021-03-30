package com.wrh.lambda.stream.tree;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author wuruohong
 * @Classname Menu
 * @Description TODO
 * @Date 2021/3/29 19:02
 */
@Data
@Builder
public class Menu {

    private Integer id;

    public String name;

    public Integer parentId;

    public List<Menu> childLists;

    public Menu(Integer id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Menu(Integer id, String name, Integer parentId, List<Menu> childLists) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.childLists = childLists;
    }
}
