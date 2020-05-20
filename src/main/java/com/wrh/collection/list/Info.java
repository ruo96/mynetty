package com.wrh.collection.list;

import lombok.Data;

/**
 * @author wuruohong
 * @Classname Info
 * @Description TODO
 * @Date 2020/5/12 14:33
 */
@Data
public class Info {
    int msgId;
    int score; //质量分
    int type; //0为系统消息，1为推荐消息

    public Info(int msgId, int score, int type) {
        this.msgId = msgId;
        this.score = score;
        this.type = type;
    }
}
