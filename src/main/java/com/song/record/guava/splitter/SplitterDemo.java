package com.song.record.guava.splitter;

import com.google.common.base.Splitter;

/**
 * Splitter可以将一个字符串按照指定的分隔符拆分成可迭代遍历的字符串集合，Iterable
 */
public class SplitterDemo {

    public static void main(String[] args){
        System.out.println(Splitter.on(",").limit(3).trimResults().split("a, c, f, h,l,s"));
        System.out.println(Splitter.fixedLength(3).split("accvnasnvibbiob"));
        System.out.println(Splitter.on(" ").omitEmptyStrings().splitToList("a  bd f"));
        System.out.println(Splitter.on(",").omitEmptyStrings().splitToList("1,,,,,4,5,,6"));
        System.out.println(Splitter.on(" ").trimResults().split("1 2 3"));
        System.out.println(Splitter.on(";").withKeyValueSeparator(":").split("a:1;b:2;c:3"));
    }

}
