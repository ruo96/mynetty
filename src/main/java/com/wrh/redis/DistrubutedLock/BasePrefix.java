package com.wrh.redis.DistrubutedLock;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:35 2019/12/9 0009
 * @Modified By:
 */
public class BasePrefix implements KeyPrefix {

    private int expireSeconds;

    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public BasePrefix( String prefix) {
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
