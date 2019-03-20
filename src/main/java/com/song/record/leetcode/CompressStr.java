package com.song.record.leetcode;

/**
 * 压缩字符串
 * 描述：
 * 给定一组字符，使用原地算法将其压缩。
 *
 * 压缩后的长度必须始终小于或等于原数组长度。
 *
 * 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。
 *
 * 在完成原地修改输入数组后，返回数组的新长度。
 *
 * 示例：
 * 输入 ["a","a","b","b","c","c","c"]
 * 输出 返回6，输入数组的前6个字符应该是：["a","2","b","2","c","3"]
 * Created by song on 2019/2/12 11:34
 */
public class CompressStr {

    public static int compress(char[] chars) {
        if (chars.length < 2){
            return chars.length;
        }
        int len = 0;
        for (int i = 0;i < chars.length;i++){
            int index = 1;
            while (i+index < chars.length && chars[i] == chars[i+index]){
                index++;
            }
            if (index != 1){
                String s = String.valueOf(index);
                chars[len] = chars[i];
                for (int k = 0;k < s.length();k++){
                    chars[len+k+1] = s.charAt(k);
                }
                len += s.length() + 1;
                i = i + index - 1;
            } else if (index == 1){
                chars[len] = chars[i];
                len += 1;
            }
        }
        return len;
    }

    public static void main(String[] args){
        char[] chars = new char[]{'a','a','b','b','c','c','c'};
        System.out.println(CompressStr.compress(chars));
    }

}
