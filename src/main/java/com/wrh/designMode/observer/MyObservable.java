package com.wrh.designMode.observer;

import lombok.Getter;

import java.util.Observable;

/**
 * @author wuruohong
 * @Classname MyObservable
 * @Description TODO
 * @Date 2021/4/1 10:41
 */
@Getter
public class MyObservable extends Observable {

    private String article;

    public void publish(String article) {
        // 发表文章
        this.article = article;

        // 改变状态
        this.setChanged();

        // 通知所有观察者
        this.notifyObservers();

        /** 观察目标的逻辑是先发表文章，再改变观察目标的状态，再通知所有观察者。*/
    }

}
