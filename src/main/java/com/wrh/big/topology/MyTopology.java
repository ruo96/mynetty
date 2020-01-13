package com.wrh.big.topology;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;

import java.util.Map;

/**
 * @Classname MyTopology
 * @Description TODO
 * @Date 2020/1/8 21:59
 * @Created by wuruohong
 */
@Slf4j
public class MyTopology extends BaseRichSpout {
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector collector) {
        log.info("open: {}",map.get("test"));
//        this.collector = collector;
    }

    @Override
    public void nextTuple() {
//        if(count)
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
