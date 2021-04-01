package com.wrh.designMode.observer;

import lombok.Data;
import lombok.NonNull;

import java.util.Observable;
import java.util.Observer;

/**
 * @author wuruohong
 * @Classname ReaderObserver
 * @Description TODO
 * @Date 2021/4/1 10:52
 */
@Data
public class ReaderObserver implements Observer {

    @NonNull
    private String name;

    private String article;

    @Override
    public void update(Observable o, Object arg) {
        // 更新文章
        updateArticle(o);
    }

    private void updateArticle(Observable o) {
        MyObservable myObservable = (MyObservable) o;
        this.article = myObservable.getArticle();
        System.out.printf("我是读者：%s  文章已更新为：%s \n",this.name, this.article);
    }
}
