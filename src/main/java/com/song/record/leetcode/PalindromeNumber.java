package com.song.record.leetcode;

/**
 * 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 比如121是回文数，1221也是回文数
 */
public class PalindromeNumber {

    public static void main(String[] args){
        System.out.println(PalindromeNumber.isPalindrome2(121));
    }

    /**
     * 将数字进行反转，再将反转后的数字与原数字进行比较。
     * 但此方法会有一个问题，如果反转后的数字大于int.MAX,则会导致数字溢出
     */
    public static boolean isPalindrome1(int x){
        if (x < 0){
            return false;
        }
        int reversed = 0;//反转数
        int remainder;//余数
        int original = x;
        while (x != 0){
            remainder = x % 10;
            reversed = reversed * 10 + remainder;
            x /= 10;
        }
        return reversed == original;
    }

    /**
     * 反转后半部的数字
     * 如何知道反转到了一半：当原始数字小于反转后的数字时，就代表已经处理到一半数字了
     * 如果是奇数位个数字的数，中间的数字不影响反转数的比较，就将其去掉
     */
    public static boolean isPalindrome2(int x){
        //负数和最后一位数为0的数不可能是回文数(0除外)
        if (x < 0 || (x % 10 == 0 && x != 0)){
            return false;
        }
        int reversed = 0;
        while (x > reversed){
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        return x == reversed || x == reversed / 10;
    }

}
