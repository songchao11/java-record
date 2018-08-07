package com.song.record.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * Created by song on 2018/8/3.
 */
public class TestDeadEventListener {

    public static void main(String[] args){
        EventBus eventBus = new EventBus("Rose");
        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);
        eventBus.post(new OrderEvent("hi"));
        System.out.println(deadEventListener.isDelivered());
    }

}
