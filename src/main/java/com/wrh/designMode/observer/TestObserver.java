package com.wrh.designMode.observer;

/**
 * @author wuruohong
 * @Classname TestObserver
 * @Description TODO
 * @Date 2021/4/1 10:59
 */
public class TestObserver {
    public static void main(String[] args) {
        MyObservable myObservable = new MyObservable();
        myObservable.addObserver(new ReaderObserver("小明"));
        myObservable.addObserver(new ReaderObserver("小张"));
        myObservable.addObserver(new ReaderObserver("小爱"));

        myObservable.publish("发表了一篇文章");
    }
}
