package com.wrh.thread.hongbao.singlethread.simple2;

/**
 * @author wuruohong
 * @Classname T5
 * @Description TODO
 * @Date 2021/4/1 18:10
 */
public class T6 {
    double remain;
    int n;
    T6(double money,int n){
        this.remain=money;
        this.n=n;
    }

    public void Rob(){
        for(int i=1;i<=n;i++){
            System.out.println("红包获得者"+i+"获得了0.01元");
        }
    }
}
