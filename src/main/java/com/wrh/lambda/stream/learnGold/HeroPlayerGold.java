package com.wrh.lambda.stream.learnGold;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:41 2018/8/30 0030
 * @Modified By:
 */
@Slf4j
@Data
public class HeroPlayerGold {


    /** 使用的英雄名字 */

    private    String            hero;

    /** 玩家的ID */

    private   String            player;

    /** 获得的金币数 */

    private    int            gold;

    public HeroPlayerGold(String    hero, String  player, int  gold) {

        this .hero = hero;

        this.player = player;

        this.gold = gold;
    }

}
