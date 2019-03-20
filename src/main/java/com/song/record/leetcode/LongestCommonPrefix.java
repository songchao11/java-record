package com.song.record.leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class LongestCommonPrefix {

    public static void main(String[] args){
        String[] strs = new String[]{"flower","flight","flow"};
        System.out.println(LongestCommonPrefix.longestCommonPrefix(strs));

    }

    /**
     *
     */
    public static String longestCommonPrefix(String[] strs){
        if (strs == null || strs.length == 0){
            return "";
        }
        String tx = strs[0];
        int i = 1;
        while (i < strs.length){
            while (strs[i].indexOf(tx) != 0){
                tx = tx.substring(0, tx.length()-1);
            }
            i ++;
        }
        return tx;
    }

}
