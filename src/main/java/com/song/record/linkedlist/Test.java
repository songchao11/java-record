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
		list.linkFirst("first");
//		list.linkFirst("BBB");
        list.display();
        list.remove("FFF");
        list.display();
        list.add(2, "XX");
        list.display();
    }

}
