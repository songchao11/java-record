package com.song.record.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * 多个订阅者
 */
public class MultiEventListener {

    @Subscribe
    public void listen(OrderEvent event){
        System.out.println("receive message: "+event.getMessage());
    }

    @Subscribe
    public void listen(String message){
        System.out.println("receive message: "+message);
    }

}
