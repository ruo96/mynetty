package com.wrh.cloud.sentinel;

/**
 * @author wuruohong
 * @Classname MySentinelResource
 * @Description TODO
 * @Date 2021/8/11 20:52
 */
public interface MySentinelResource {

    /**
     * 流控资源
     */
    public static final String FLOW_RESOURCE = "flowResource";

    /**
     * 流控资源
     */
    public static final String DEGRADE_RESOURCE = "degradeResource";
}
