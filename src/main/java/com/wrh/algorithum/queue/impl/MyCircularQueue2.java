package com.wrh.algorithum.queue.impl;

/**
 * @author wuruohong
 * @Classname MyCircularQueue
 * @Description 我们知道队列是先进先出的，对于链表，我们能采用单链表尽量采用单链表，能方便尽量方便，同时还要兼顾效率。使用链表大概有两个实现方案：
 *
 * 方案一 如果队列头设在链表尾，队列尾设在链表头。那么队尾进队插入在链表头部插入没问题，容易实现，但是如果队头删除在链表尾部进行，如果不设置尾指针要遍历到队尾，但是设置尾指针删除需要将它前驱节点需要双向链表，都挺麻烦的。
 *
 * 方案二如果队列头设在链表头，队列尾设在链表尾，那么队尾进队插入在链表尾部插入没问题(用尾指针可以直接指向next)，容易实现，如果队头删除在链表头部进行也很容易，就是我们前面常说的头节点删除节点。
 *
 * 所以我们最终采取的是方案2的带头节点、带尾指针的单链表!
 * @Date 2021/5/10 14:17
 */
public class MyCircularQueue2 {
    class node {
        int data;// 节点的结果
        node next;// 下一个连接的节点
        public node() {}
        public node(int data) {
            this.data = data;
        }
    }
    node front;//相当于head 带头节点的
    node rear;//相当于tail/end
    int maxsize;//最大长度
    int len=0;
    public MyCircularQueue2(int k) {
        front=new node(0);
        rear=front;
        maxsize=k;
        len=0;
    }
    public boolean enQueue(int value)  {
        if (isFull())
            return  false;
        else {
            node va=new node(value);
            rear.next=va;
            rear=va;
            len++;
        }
        return  true;
    }
    public boolean deQueue() {
        if (isEmpty())
            return false;
        else {
            front.next=front.next.next;
            len--;
            //注意 如果被删完 需要将rear指向front
            if(len==0)
                rear=front;
        }
        return  true;
    }

    public int Front() {
        if(isEmpty())
            return -1;
        return front.next.data;
    }

    public int Rear() {
        if(isEmpty())
            return -1;
        return rear.data;
    }

    public boolean isEmpty() {
        return  len==0;
        //return rear == front;
    }

    public boolean isFull() {
        return len==maxsize;
    }
}
