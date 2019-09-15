package com.song.record.linkedlist;

/**
 * Created by songchao on 2019/9/15.
 */
public class StackSingleLink<T> {

    private SingleLinkedList<T> singleLinkedList;

    public StackSingleLink(){
        this.singleLinkedList = new SingleLinkedList<T>();
    }

    /**
     * 添加元素
     * @param t
     */
    public void push(T t){
        singleLinkedList.linkFirst(t);
    }

    /**
     * 弹出元素
     * @return
     */
    public T pop(){
        return singleLinkedList.deleteHead();
    }

    /**
     * 打印栈内元素
     */
    public void display(){
        singleLinkedList.display();
    }

    public static void main(String[] args){
        StackSingleLink<String> stack = new StackSingleLink<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.pop();
        stack.push("D");
        stack.display();
    }

}
