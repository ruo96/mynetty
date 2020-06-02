package com.wrh.lambda.basicuse.sum;

import lombok.Data;

import java.io.Serializable;

@Data
public class RealtimeRechargePO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer gameId;

    private Long recharge;
}
