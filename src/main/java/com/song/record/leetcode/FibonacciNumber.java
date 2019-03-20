package com.song.record.leetcode;

/**
 * 斐波那契数
 * 问题描述：斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * Created by song on 2019/2/12 11:02
 */
public class FibonacciNumber {

    public static int fib(int n){
        if (n < 2){
            return n;
        } else {
            return fib(n-1) + fib(n-2);
        }
    }

    public static void main(String[] args){

        System.out.println(FibonacciNumber.fib(3));
    }

}
