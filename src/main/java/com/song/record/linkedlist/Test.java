package com.song.record.linkedlist;

/**
 * Created by song on 2018/8/7.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        LinkedList<String> list = new LinkedList<String>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wang");
        list.add("AA");
        list.add("BB");
        list.add("CCC");
        list.add("SSS");
        list.add("FFF");
        list.add("AWE");
        list.add("OPT");
//		list.linkFirst("AAA");
//		list.linkFirst("BBB");
        for(int i = 0;i < list.size();i++){
            System.out.print(list.get(i)+"\t");
        }
        System.out.println();
        list.remove("WW");
        for(int i = 0;i < list.size();i++){
            System.out.print(list.get(i)+"\t");
        }
        System.out.println();
        list.add(2, "XX");
        for(int i = 0;i < list.size();i++){
            System.out.print(list.get(i)+"\t");
        }
        System.out.println();
        list.set(33, "OO");
        for(int i = 0;i < list.size();i++){
            System.out.print(list.get(i)+"\t");
        }
    }

}
