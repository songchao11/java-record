package com.song.record.leetcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 整数转罗马数字
 */
public class Integer2Roman {

    public static void main(String[] args){
//        System.out.println(Integer2Roman.intToRoman(468));
//        System.out.println(isNumber("0"));
//        Pattern pattern=Pattern.compile("(-|\\+)?(180\\.0{0,6}|(\\d{1,2}|1([0-7]\\d))\\.\\d{0,6})"); // 经度
//        Pattern pattern=Pattern.compile("(-|\\+)?(90\\.0{0,6}|(\\d|[1-8]\\d)\\.\\d{0,6})"); // 纬度
//        Pattern pattern=Pattern.compile("^[a-zA-Z0-9\\W][^((?!V|:|\\*|\\?|'|<|\\||‘|%|>|&).)*$]{0,13}$");
        Pattern pattern=Pattern.compile("^(-|\\+)?(90\\.0{0,6}|(\\d|[1-8]\\d)\\.\\d{0,6})$");
        Matcher match=pattern.matcher("90");
        System.out.println(match.matches());
    }

    public static String intToRoman(int num) {//CDLXVIII
        StringBuilder sb = new StringBuilder();
        String[] letters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int ptr = 0;
        while (ptr < letters.length) {
            for (int i = 0; i < num / values[ptr]; i++) {
                sb.append(letters[ptr]);
            }
            num %= values[ptr];
            ptr++;
        }
        return sb.toString();
    }

    //金额验证
    public static boolean isNumber(String str){
        Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        if(match.matches()==false){
            return false;
        }else{
            return true;
        }
    }


}
