package com.song.record.linkedlist;

/**
 * Created by song on 2018/8/7.
 */
public class TestSingle {

    public static void main(String[] args) throws Exception {
        SingleLinkedList<String> sList = new SingleLinkedList<String>();
        System.out.println(sList.isEmpty());
        sList.add("AA");
        sList.add("BB");
        sList.add("CC");
        sList.add("DD");
        sList.add("EE");
        sList.add("FF");
        sList.add("GG");

        for(int i = 0;i < sList.size();i++){
            System.out.print(sList.get(i)+"\t");
        }
        System.out.println();
        sList.add(2, "PIG");
        for(int i = 0;i < sList.size();i++){
            System.out.print(sList.get(i)+"\t");
        }
        System.out.println();
        sList.remove(5);
        sList.set(6, "HAHAHA");
        for(int i = 0;i < sList.size();i++){
            System.out.print(sList.get(i)+"\t");
        }
        System.out.println();
        System.out.println(sList.get(2));

    }

}
