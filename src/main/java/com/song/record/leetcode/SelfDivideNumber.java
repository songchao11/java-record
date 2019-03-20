package com.song.record.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 自除数
 * 描述：
 * 自除数 是指可以被它包含的每一位数除尽的数。
 *
 * 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 *
 * 还有，自除数不允许包含 0 。
 *
 * 给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。
 *
 * 示例：
 * 输入：
 * 上边界left = 1, 下边界right = 22
 * 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 *
 * Created by song on 2019/2/13 11:17
 */
public class SelfDivideNumber {

    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left;i <= right;i++){
            if (check(i)){
                list.add(i);
            }
        }
        return list;
    }

    private static boolean check(int n){
        int d = n;
        while (d != 0){
            int c = d % 10;
            if (c == 0 || (n % c) != 0){
                return false;
            }
            d /= 10;
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(SelfDivideNumber.selfDividingNumbers(1, 48));
    }

}
