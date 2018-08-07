package com.song.record.guava.preconditions;

import com.google.common.base.Preconditions;

/**
 * 前置条件Preconditions提供静态方法来检查方法或构造函数，被调用是否给定适当的参数。
 */
public class PreconditionsDemo {

    public static void main(String[] args){
        try{
            sqrt(-1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            sum(1, null);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            getValue(4);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static double sqrt(double input){
        Preconditions.checkArgument(input > 0.0, "Illegal Argument passed: Negative value %s.",
                input);
        return Math.sqrt(input);
    }

    private static int sum(Integer a, Integer b){
        a = Preconditions.checkNotNull(a, "第一个参数为null");
        b = Preconditions.checkNotNull(b, "第二个参数为null");
        return a + b;
    }

    private static int getValue(int input){
        int[] data = {4,3,7,9,2};
        int index = Preconditions.checkElementIndex(input, data.length, "下标错误");
        return data[index];
    }

}
