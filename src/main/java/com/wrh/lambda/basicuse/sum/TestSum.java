package com.wrh.lambda.basicuse.sum;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuruohong
 * @Classname TestSum
 * @Description TODO
 * @Date 2020/6/2 11:15
 */
@Slf4j
public class TestSum {
    @Test
    public void Test1() {
        List<RealtimeRechargePO> list = new ArrayList<>();
        RealtimeRechargePO indie = new RealtimeRechargePO();
        indie.setGameId(3);
        indie.setRecharge(100L);

        List<Integer> indieGameIds = new ArrayList<>();
        indieGameIds.add(1);
        indieGameIds.add(2);

        list.add(indie);

        Long a = list.stream().filter(e-> indieGameIds.contains(e.getGameId())).mapToLong(RealtimeRechargePO::getRecharge).sum();
        Map<Integer, Long> map = new HashMap<>();
        map.put(6, list.stream().filter(e-> indieGameIds.contains(e.getGameId())).mapToLong(RealtimeRechargePO::getRecharge).sum());
        System.out.println(map);
    }
}
