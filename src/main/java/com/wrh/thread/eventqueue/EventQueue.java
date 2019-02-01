package com.wrh.thread.eventqueue;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:12 2019/2/1 0001
 * @Modified By:
 */
public class EventQueue {
    private final int max;

    private static int i = 1;

    public EventQueue(int max) {
        this.max = max;
    }
    static class Event{}

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue(){
        this(DEFAULT_MAX_EVENT);
    }

    public void offer(Event event) throws InterruptedException {
        synchronized (eventQueue){
            if(eventQueue.size() >= max){
                console("this queue is full");
                eventQueue.wait();
            }
            console(String.valueOf(i++));
            console("====this new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take() throws InterruptedException {
        synchronized (eventQueue){
            if(eventQueue.isEmpty()){
                console("this queue is empty");
                eventQueue.wait();
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            console("this event" + event +  " is handled");
            return event;
        }
    }

    private void console(String message){
        System.out.printf( "%s:%s\n",currentThread().getName(),message);
    }
}
