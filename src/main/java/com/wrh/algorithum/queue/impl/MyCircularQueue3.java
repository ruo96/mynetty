package com.wrh.algorithum.queue.impl;

/**
 * @author wuruohong
 * @Classname MyCircularQueue
 * @Description 设计实现双端队列，其实你经常使用的ArrayDeque就是一个经典的双向队列，其基于数组实现，效率非常高。我们这里实现的双向队列模板基于力扣641 设计循环双端队列 。
 * 你的实现需要支持以下操作：
 *
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 * @Date 2021/5/10 14:17
 */
public class MyCircularQueue3 {
    private int data[];// 数组容器
    private int front;// 头
    private int rear;// 尾
    private int maxsize;// 最大长度
    /*初始化 最大大小为k */
    public MyCircularQueue3(int k) {
        data = new int[k+1];
        front = 0;
        rear = 0;
        maxsize = k+1;
    }

    /** 头部插入 */
    public boolean insertFront(int value) {
        if(isFull())
            return false;
        else {
            front=(front+maxsize-1)%maxsize;
            data[front]=value;
        }
        return  true;
    }

    /** 尾部插入 */
    public boolean insertLast(int value) {
        if(isFull())
            return  false;
        else{
            data[rear]=value;
            rear=(rear+1)%maxsize;
        }
        return  true;
    }

    /** 正常头部删除 */
    public boolean deleteFront() {
        if (isEmpty())
            return false;
        else {
            front=(front+1)%maxsize;
        }
        return  true;
    }

    /** 尾部删除 */
    public boolean deleteLast() {
        if(isEmpty())
            return false;
        else {
            rear=(rear+maxsize-1)%maxsize;
        }
        return true;
    }

    /** Get the front item  */
    public int getFront() {
        if(isEmpty())
            return -1;
        return data[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(isEmpty())
            return -1;
        return  data[(rear-1+maxsize)%maxsize];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return front==rear;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (rear+1)%maxsize==front;
    }
}
