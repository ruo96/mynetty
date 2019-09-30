package com.wrh.lambda.stream.learnGold;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:44 2018/8/30 0030
 * @Modified By:
 */
public class TestLearnCollect {

    public static void main(String[] args) {
        learnCollect();
    }

    private static void learnCollect() {

        List<HeroPlayerGold > lists = new ArrayList<>();
        lists.add( new HeroPlayerGold ( "盖伦","RNG-Letme" ,100 ));
        lists.add( new HeroPlayerGold ( "诸葛亮","RNG-Xiaohu" ,300 ));
        lists.add( new HeroPlayerGold ( "露娜","RNG-MLXG",300));
        lists.add( new HeroPlayerGold ( "狄仁杰","RNG-UZI",500));
        lists.add( new HeroPlayerGold ( "牛头","RNG-Ming",500));

        lists.stream().collect( () -> new HashSet<>(),
                                (set,elem)-> set.add(elem),
                                (setA,setB)->setA.addAll(setB)
        ).forEach( System.out::println);

        lists.stream().collect( HashSet::new,
                                HashSet::add,
                                HashSet::addAll
        ).forEach(System.out::println);
    }
}
