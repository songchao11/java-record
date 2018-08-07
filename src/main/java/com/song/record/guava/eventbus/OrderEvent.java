package com.song.record.guava.eventbus;

/**
 * 事件
 * Guava发布-订阅模式中传递的事件，是一个普通的POJO类
 */
public class OrderEvent {

    private String message;

    public OrderEvent(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
