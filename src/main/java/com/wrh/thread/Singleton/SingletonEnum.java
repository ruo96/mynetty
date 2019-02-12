package com.wrh.thread.Singleton;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:32 2019/2/11 0011
 * @Modified By:
 */
/*枚举本身是final的, 不允许被继承*/
public enum SingletonEnum {

    INSTANCE;

    private byte[] data = new byte[1024];

    SingletonEnum(){
        System.out.println("INSTANCE will be initialized immediately");
    }

    public static void main(String[] args) {
        /*调用该方法会主动使用Singleton,INSTANCE将会被实例化*/
        System.out.println("开始调用了");
        SingletonEnum.getInstance();
    }

    public static SingletonEnum getInstance(){
        return INSTANCE;
    }

}
