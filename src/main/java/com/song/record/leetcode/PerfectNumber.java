package com.song.record.leetcode;

/**
 * 完美数
 * 描述：
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 *
 * 给定一个 正整数 n， 如果他是完美数，返回 True，否则返回 False
 *
 * 示例：
 * 28 = 1 + 2 + 4 + 7 + 14
 * Created by song on 2019/2/13 10:45
 */
public class PerfectNumber {

    public static boolean checkPerfectNumber(int num) {
        if (num < 1){
            return false;
        }
        int half = num / 2;
        int sum = 0;
        for (int i = 1;i <= half;i++){
            if (num % i == 0){
                sum += i;
            }
        }
        if (num == sum){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println(PerfectNumber.checkPerfectNumber(0));
    }

}
