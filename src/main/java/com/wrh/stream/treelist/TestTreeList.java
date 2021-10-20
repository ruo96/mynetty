package com.wrh.stream.treelist;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wuruohong
 * @Classname TestTreeList
 * @Description 树形结构查询
 * @Date 2021/10/20 16:57
 */
public class TestTreeList {
    @Test
    public void Test11() {
//模拟从数据库查询出来
        List<Menu> menus = Arrays.asList(
                new Menu(1,"根节点",0),
                new Menu(2,"子节点1",1),
                new Menu(3,"子节点1.1",2),
                new Menu(4,"子节点1.2",2),
                new Menu(5,"子节点1.3",2),
                new Menu(6,"子节点2",1),
                new Menu(7,"子节点2.1",6),
                new Menu(8,"子节点2.2",6),
                new Menu(9,"子节点2.2.1",8),
                new Menu(10,"子节点2.2.2",8),
                new Menu(11,"子节点3",1),
                new Menu(12,"子节点3.1",11)
        );

        //获取父节点
        List<Menu> collect = menus.stream().filter(m -> m.getParentId() == 0).map(
                (m) -> {
                    m.setChildList(getChildrens(m, menus));
                    return m;
                }
        ).collect(Collectors.toList());
        System.out.println("-------转json输出结果-------");
        System.out.println(JSON.toJSON(collect));
    }

    /**
     * 递归查询子节点
     * @param root  根节点
     * @param all   所有节点
     * @return 根节点信息
     */
    private List<Menu> getChildrens(Menu root, List<Menu> all) {
        List<Menu> children = all.stream().filter(m -> {
            return Objects.equals(m.getParentId(), root.getId());
        }).map(
                (m) -> {
                    m.setChildList(getChildrens(m, all));
                    return m;
                }
        ).collect(Collectors.toList());
        return children;
    }
}
