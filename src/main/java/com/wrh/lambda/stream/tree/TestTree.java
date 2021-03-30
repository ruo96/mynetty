package com.wrh.lambda.stream.tree;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wuruohong
 * @Classname TestTree
 * @Description TODO
 * @Date 2021/3/29 19:04
 */
@Slf4j
public class TestTree {

    @Test
    public void Test15() {
        List<Menu> menus = Arrays.asList(
                new Menu(1,"根节点",0),
                new Menu(2,"子节点1",1),
                new Menu(3,"子节点1.1",2),
                new Menu(4,"子节点1.2",2),
                new Menu(5,"子节点1.3",2),
                new Menu(6,"子节点2",1),
                new Menu(7,"子节点2.1",6),
                new Menu(8,"子节点2.2",6),
                new Menu(9,"子节点2.1.1",7),
                new Menu(10,"子节点2.1.1",7),
                new Menu(11,"子节点3",1),
                new Menu(12,"子节点3.1",11)
        ) ;

        List<Menu> collect = menus.stream().filter(m->m.getParentId() == 0)
                .map(m->{
                    m.setChildLists(getChildrens(m,menus));
                    return m;
                }).collect(Collectors.toList());
        System.out.println("JSON.toJSONString(collect) = " + JSON.toJSONString(collect));
    }

    private List<Menu> getChildrens(Menu root, List<Menu> all) {
        List<Menu> children = all.stream().filter(m->{
            return Objects.equals(m.getParentId(), root.getId());
        }).map(m->{
            m.setChildLists(getChildrens(m,all));
            return m;
        }).collect(Collectors.toList());
        return children;
    }
}
