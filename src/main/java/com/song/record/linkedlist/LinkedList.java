package com.song.record.linkedlist;

/**
 * 双向链表
 */
public class LinkedList<T> {

    /*
     * 节点类
     */
    private static class Node<T>{
        T item;
        Node<T> prev;//前置节点
        Node<T> next;//后置节点
        public Node(Node<T> prev, T item, Node<T> next){
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    /*
     * 第一个节点的引用(头结点)
     */
    Node<T> first;

    /*
     * 最后一个节点的引用(尾节点)
     */
    Node<T> last;

    /*
     * 链表中元素数目
     */
    private int size = 0;

    /*
	 * 操作次数
	 */
    int modCount = 0;

    public LinkedList(){}

    /*
     * 把传入的元素变为第一个元素
     * 将传入的元素插到链表最前面的步骤:
     * ①取出添加之前的头结点设为f
     * ②将需要插入的元素封装成一个节点newNode,其后置指针指向f
     * ③如果原来的头结点为null(此时链表为空),就将尾节点指向newNode;
     * 否则使原来的头结点f的前置指针指向newNode
     * ④头节点指向新的头结点
     */
    public void linkFirst(T t){
        //使节点f指向原来的头结点
        Node<T> f = first;
        //新建一个节点newNode,其节点的前指针指向null,后指针指向原来的头结点
        Node<T> newNode = new Node<T>(null, t, f);
        //如果原来的头结点为null,更新尾节点,否则使原来的头结点f的前置指针指向新的头节点newNode
        if(f == null){
            last = newNode;
        }else{
            f.prev = newNode;
        }
        //头节点指向新的头结点
        first = newNode;
        size++;
        modCount++;
    }

    /*
     * 将元素添加到最后
     * A  B  C
     * 将元素添加到最后的步骤:
     * ①取出添加之前的尾节点设为l
     * ②将需要插入的元素封装成一个节点newNode,并将其前置指针指向l
     * ③如果尾节点为null(说明此时链表为空),就将头结点指向newNode;
     * 否则就将原来尾节点的后置指针指向newNode
     * ④尾节点指向新的尾节点
     */
    public void linklast(T t){
        //使节点l指向原来的尾节点
        Node<T> l = last;
        //新建节点newNode,让它的前置指针指向l
        Node<T> newNode = new Node<T>(l, t, null);
        //如果原来的尾节点为null,更新头节点,否则使原来的尾节点l的后置指针指向新的头结点newNode
        if(l == null){
            first = newNode;
        }else{
            l.next = newNode;
        }
        //尾节点指向新的尾结点
        last = newNode;
        size++;
        modCount++;
    }

    /*
     * 向list里添加元素
     */
    public boolean add(T t){
        linklast(t);
        return true;
    }

    /*
     * 返回list大小
     */
    public int size(){
        return size;
    }

    /*
     * 在指定节点之前插入元素
     * A   B
     * 将元素插入B节点之前,需要做如下操作:
     * ①获得指定节点B的前驱A
     * ②将需要插入的元素封装成一个节点S,并指定他的前置指针和后置指针分别为A和B
     * ③将B节点的前置指针更改为S
     * ④首先判断指定节点B的前驱是否为null.如果为null,就直接将节点S设为头结点;
     * 如果不为null,就将节点A的后置指针更改为S
     */
    private void linkBefore(T t, Node<T> succ){
        //获得指定节点的前驱
        Node<T> pred = succ.prev;
        //新建节点newNode,前置指针指向pred,后置指针指向succ
        Node<T> newNode = new Node<T>(pred, t, succ);
        //succ的前置指针指向newNode
        succ.prev = newNode;
        //如果指定节点的前驱为null,则将newNode设为头结点,否则更新pred的后置节点
        if(pred == null){
            first = newNode;
        }else{
            pred.next = newNode;
        }
        size++;
        modCount++;
    }
    /*
     * 根据下标获取指定节点(下标从零开始)
     */
    private Node<T> node(int index){
        Node<T> x = first;
        for(int i = 0;i < index;i++){
            x = x.next;
        }
        return x;
    }
    /*
     * 上面node方法优化
     * 步骤:
     * 判断index是否小于size的一半,若小,则从头节点向后遍历
     * 若大,则从尾节点向前遍历
     */
    private Node<T> nodeOptimizing(int index){
        Node<T> x = null;
        if(index < size/2){
            x = first;
            for(int i = 0;i < index;i++){
                x = x.next;
            }
        }else{
            x = last;
            for(int i = size-1;i > index;i--){
                x = x.prev;
            }
        }
        return x;
    }
    /*
     * 获取指定下标的值
     */
    public T get(int index) throws Exception{
        checkIndex(index);
        return nodeOptimizing(index).item;
    }
    /*
     * 检查下标的合法性
     */
    private void checkIndex(int index) throws Exception{
        if(index < 0 || index > size){
            throw new Exception("下标非法异常");
        }
    }

    /*
     * 删除指定位置元素
     */
    public T remove(int index) throws Exception{
        checkIndex(index);
        return unLink(nodeOptimizing(index));
    }

    /*
     * 删除指定元素
     * 步骤:
     * ①首先判断obj是否为null,若为null,需要用"==";不为null,就使用equals
     * ②循环遍历找到obj对应的节点并删除
     */
    public boolean remove(Object obj){
        Node<T> x = first;
        if(obj == null){
            while(x.item != obj){
                //没有找到需要删除的节点
                if(x.next == null){
                    return false;
                }
                x = x.next;
            }
            unLink(x);
            return true;
        }else{
            while(!x.item.equals(obj)){
                if(x.next == null){
                    return false;
                }
                x = x.next;
            }
            unLink(x);
            return true;
        }
    }

    /*
     * 在指定位置添加元素
     */
    public void add(int index, T t) throws Exception{
        checkIndex(index);
        linkBefore(t, nodeOptimizing(index));
    }

    /*
     * 删除指定节点,并返回指定节点元素的值
     * 步骤:
     * ①获取指定节点的前置节点(prev)和后置节点(next)
     * ②若前置节点为null,则说明x为头结点,此时只要将next置为新的头结点即可,否则就将prev的next指向next,并把x的prev置为null
     * ③若后置节点为null,则说明x为尾节点,此时只要将prev置为新的尾节点,否则将后置节点的prev指向prev,并把x的next置为null
     * ④将x的item置为null,size-1
     */
    private T unLink(Node<T> x){
        T element = x.item;
        Node<T> next = x.next;
        Node<T> prev = x.prev;
        //若prev为null,则说明x为头结点,此时只要将next置为新的头结点,否则将prev的next指向next,x的prev置为null
        if(prev == null){
            first = next;
        }else{
            prev.next = next;
            x.prev = null;
        }
        //若next为null,则说明x为尾节点,此时只要将prev置为新的尾节点,否则将next的prev指向prev,x的next置为null
        if(next == null){
            last = prev;
        }else{
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        modCount++;
        return element;
    }

    /*
     * 设置指定位置的值
     */
    public boolean set(int index, T t) throws Exception{
        checkIndex(index);
        Node<T> x = nodeOptimizing(index);
        x.item = t;
        return true;
    }


}
