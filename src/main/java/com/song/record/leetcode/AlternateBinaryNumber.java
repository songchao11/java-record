package com.song.record.leetcode;

/**
 * 交替二进制数
 * 描述：
 * 给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。
 * Created by song on 2019/2/13 9:27
 */
public class AlternateBinaryNumber {

    public static boolean hasAlternatingBits(int n){
        //1.将十进制数转化为二进制数
        String s = Integer.toBinaryString(n);
        char[] chars = s.toCharArray();
        for (int i = 0;i < chars.length-1;i++){
            if (chars[i] == chars[i+1]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(AlternateBinaryNumber.hasAlternatingBits(7));
    }

}
