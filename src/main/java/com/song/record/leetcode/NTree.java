package com.song.record.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2019/2/15 10:04
 */
public class NTree {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Node> list = new ArrayList<>();
        if (root == null){
            return result;
        }
        list.add(root);
        Node temp;
        while (list.size() > 0){
            List<Node> list1 = new ArrayList<>();
            List<Integer> vals = new ArrayList<>();
            for (int i = 0;i < list.size();i++){
                temp = list.get(i);
                vals.add(temp.val);
                List<Node> children = temp.children;
                for (int j = 0;children != null && j < children.size();j++){
                    list1.add(children.get(j));
                }
            }
            result.add(vals);
            list = list1;
        }
        return result;
    }

}

class Node {

    public int val;

    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }

}
