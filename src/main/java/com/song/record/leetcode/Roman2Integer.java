package com.song.record.leetcode;

/**
 * 罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，
 * 例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的
 * 数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。
 * 这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 */
public class Roman2Integer {

    public static void main(String[] args){
        System.out.println(Roman2Integer.roman2Int("MCMXCIV"));
    }

    public static int roman2Int(String roman){
        int sum = 0;
        if (roman.indexOf("IV") != -1){
            sum -= 2;
        }
        if (roman.indexOf("IX") != -1){
            sum -= 2;
        }
        if (roman.indexOf("XL") != -1){
            sum -= 20;
        }
        if (roman.indexOf("XC") != -1){
            sum -= 20;
        }
        if (roman.indexOf("CD") != -1){
            sum -= 200;
        }
        if (roman.indexOf("CM") != -1){
            sum -= 200;
        }
        char[] strs = roman.toCharArray();
        for (int i = 0;i < strs.length;i++){
            if (strs[i] == 'I'){
                sum += 1;
            }
            if (strs[i] == 'V'){
                sum += 5;
            }
            if (strs[i] == 'X'){
                sum += 10;
            }
            if (strs[i] == 'L'){
                sum += 50;
            }
            if (strs[i] == 'C'){
                sum += 100;
            }
            if (strs[i] == 'D'){
                sum += 500;
            }
            if (strs[i] == 'M'){
                sum += 1000;
            }
        }
        return sum;
    }

}
