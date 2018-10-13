package com.song.record.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by song on 2018/10/13.
 */
public class Tree {

    /**
     * 获取树目录结构
     * @return
     */
    public List<TreeResultDto> tree(){
        //获取所有子节点
        List<TreeResultDto> treeList = getTreeList();
        //获取父节点
        List<TreeResultDto> parentList = getParentList();
        if (treeList != null){
            //过滤条件set
            Set<Long> set = new HashSet<>(treeList.size());
            parentList.forEach(treeResultDto -> getChild(treeResultDto, treeList, set));
            return parentList;
        }
        return null;
    }

    private void getChild(TreeResultDto treeResultDto, List<TreeResultDto> treeList, Set<Long> set){
        List<TreeResultDto> childList = new ArrayList<>();
        treeList.stream()
                .filter(t -> !set.contains(t.getId()))
                .filter(t -> treeResultDto.getId() == t.getParentId())
                .forEach(c -> {
                    //放入set,递归循环时就可以跳过这条数据，提高效率
                    set.add(c.getId());
                    //获取当前节点的子节点
                    getChild(c, treeList, set);
                    childList.add(c);
                });
        treeResultDto.setChildList(childList);
    }


    /**
     * 模拟所有子节点数据
     * @return
     */
    private List<TreeResultDto> getTreeList(){
        List<TreeResultDto> list = new ArrayList<>();
        TreeResultDto tree1 = new TreeResultDto(2L, "杭州", 1L);
        TreeResultDto tree2 = new TreeResultDto(3L, "滨江", 2L);
        TreeResultDto tree3 = new TreeResultDto(4L, "富阳", 2L);
        TreeResultDto tree4 = new TreeResultDto(5L, "巨力新村", 4L);
        TreeResultDto tree5 = new TreeResultDto(6L, "安徽", 1L);
        TreeResultDto tree6 = new TreeResultDto(7L, "安庆", 6L);
        TreeResultDto tree7 = new TreeResultDto(8L, "芜湖", 6L);
        TreeResultDto tree8 = new TreeResultDto(9L, "太湖", 7L);
        list.add(tree1);
        list.add(tree2);
        list.add(tree3);
        list.add(tree4);
        list.add(tree5);
        list.add(tree6);
        list.add(tree7);
        list.add(tree8);
        return list;
    }

    /**
     * 模拟父节点数据
     * @return
     */
    private List<TreeResultDto> getParentList(){
        List<TreeResultDto> list = new ArrayList<>();
        TreeResultDto parent = new TreeResultDto(1L, "中国", 0L);
        list.add(parent);
        return list;
    }

    public static void main(String[] args){
        Tree tree = new Tree();
        List<TreeResultDto> list = tree.tree();
        System.out.println(list.toString());
    }

}
