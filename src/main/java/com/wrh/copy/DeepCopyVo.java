package com.wrh.copy;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:37 2019/10/10 0010
 * @Modified By:
 */
@Data
public class DeepCopyVo {
    private String name;

    private Integer age;

    private char firstChar;

    private Character firstCharacter;

    private int money;

    private List<String> cars;

    private HashSet<String> thing;

    private boolean hide;

    private Boolean show;

    private Map<String, Object> map;

}
