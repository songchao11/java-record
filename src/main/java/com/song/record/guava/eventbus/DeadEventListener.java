package com.song.record.guava.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * 如果EventBus发送的消息都不是订阅者关心的称之为Dead Event
 */
public class DeadEventListener {

    private boolean isDelivered = true;

    @Subscribe
    public void listen(DeadEvent event){
        isDelivered = false;
        //Source通常是EventBus
        System.out.println(event.getSource().getClass()+" "+event.getEvent());

    }

    public boolean isDelivered(){
        return isDelivered;
    }

}
