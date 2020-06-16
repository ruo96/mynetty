package com.wrh.list.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuruohong
 * @Classname GameConfig
 * @Description TODO
 * @Date 2020/6/3 18:19
 */
@Data
public class GameConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**游戏id*/
    private Integer gameId;

    private Integer groupId;
}
