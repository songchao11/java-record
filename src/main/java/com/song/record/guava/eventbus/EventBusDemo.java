package com.song.record.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * Created by song on 2018/8/3.
 */
public class EventBusDemo {

    public static void main(String[] args){
        EventBus eventBus = new EventBus("rose");

        //注册订阅者
        eventBus.register(new EventListener());
//        eventBus.register(new MultiEventListener());
        //发布事件
        eventBus.post(new OrderEvent("hello"));
        eventBus.post(new OrderEvent("world"));
        eventBus.post(new OrderEvent("!"));
        eventBus.post("你好");

    }

}
