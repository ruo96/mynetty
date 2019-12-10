package com.wrh.redis.DistrubutedLock;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:35 2019/12/9 0009
 * @Modified By:
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
