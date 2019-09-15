package com.song.record.linkedlist;

/**
 * 单链表实现
 */
public class SingleLinkedList<T> {

    /**
     * 节点类
     */
    private class Node<T> {
        T item;
        Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * 链表长度
     */
    private int size = 0;

    /*
     * 头结点
     */
    private Node<T> head;

    public SingleLinkedList() {
    }

    /**
     * 将元素添加到链表最前面
     * 步骤：
     * ①将元素封装成一个节点newNode(暂时不设指向next)
     * ②判断头结点是否为null，若为null，直接将newNode设为头结点
     * ③若不为null，则将newNode的next指向头结点，再将newNode设为新的头结点
     */
    public void linkFirst(T t) {
        Node<T> newNode = new Node<T>(t, null);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    /**
     * 将元素添加到链表最后
     * 步骤：
     * ①首先将元素封装成一个节点newNode，因为是添加到链表最后，所以newNode的next指向null
     * ②判断头结点是否为null，若为null，则直接将newNode设为头结点
     * ③若不为null，则根据下标找到链表的最后一个节点
     * ④然后将最后一个节点的next指向newNode
     * 思考：其实可以设一个尾节点，这样将元素添加到链表最后就更加方便
     */
    public void linkLast(T t) {
        Node<T> newNode = new Node(t, null);
        Node<T> l = head;
        if (head == null) {
            head = newNode;
        } else {
            for (int i = 0; i < size - 1; i++) {
                l = l.next;
            }
            l.next = newNode;
        }
        size++;
    }

    /**
     * 将元素插入到指定位置
     * A  Y  B
     * 将元素插入到Y位置步骤：
     * ①如果index为0，则此时是将元素插到头部
     * ②根据下标获取Y位置的前一个节点A和后一个节点B
     * ③然后将元素封装成一个节点newNode，newNode的next指向B
     * ④最后将前一个节点A的next指向newNode
     */
    public void add(int index, T t) {
        Node<T> f = head;
        Node<T> newNode = null;
        if (index == 0) {
            newNode = new Node(t, f);
            head = newNode;
        } else {
            for (int i = 0; i < index - 1; i++) {
                f = f.next;
            }
            Node<T> oldNext = f.next;
            newNode = new Node(t, oldNext);
            f.next = newNode;
        }
        size++;
    }

    /**
     * 添加元素到尾部
     */
    public boolean add(T t) {
        linkLast(t);
        return true;
    }

    /**
     * 根据下标获取指定节点
     * 根据下标循环遍历，直到找到index位置为止
     */
    public Node<T> node(int index) {
        Node<T> f = head;
        for (int i = 0; i < index; i++) {
            f = f.next;
        }
        return f;
    }

    /**
     * 根据下标获取元素
     */
    public T get(int index) {
        return node(index).item;
    }

    /**
     * 获取链表长度
     */
    public int size() {
        return size;
    }

    /*
     * 判断链表是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 删除指定元素
     * 注意：若链表里有两个或者两个以上的相同元素，只移除第一个找到的元素
     * 步骤：
     * ①定义两个节点：一个是需要删除元素的节点current，另一个是需要删除节点的上一个节点previous(都让他们从head开始)
     * ②然后通过需要删除的元素obj，找到obj对应的节点以及上一个节点
     * ③若被删除的节点是头节点(此时current和previous都是head节点)，就直接将head的下一个节点直接定义为头结点
     * ④若删除的节点不是头结点，就将被删除的上一个节点(previous)的next指向被删除节点(current)的下一个节点
     * ⑤再将size-1
     */
    public boolean remove(Object obj) {
        //判断链表是否为空
        if (size == 0) {
            return false;
        }
        Node<T> current = head;//将要被删除的节点
        Node<T> previous = head;//将要被删除节点的上一个节点
        while (current.item != obj) {
            //current.next=null就说明整个链表已经遍历结束，但没有找到需要被删除的元素
            if (current.next == null) {
                return false;
            } else {
                previous = current;
                current = current.next;
            }
        }
        //被删除的节点是头结点
        if (current == head) {
            head = head.next;
        } else {
            previous.next = current.next;
        }
        size--;
        return true;
    }

    /**
     * 删除指定位置的元素
     * 步骤：
     * ①首先定义两个节点：将要被删除的节点(current)和将要被删除节点的上一个节点(previous)
     * ②根据下标找到要被删除的节点和上一个节点
     * ③如果要被删除的节点是头结点，就将头结点的下一个节点直接设为头结点
     * ④否则就将previous.next指向current.next
     * ⑤再将size-1
     */
    public T remove(int index) {
        T result = null;
        Node<T> current = head;//将要被删除的节点
        Node<T> previous = head;//将要被删除节点的上一个节点
        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }
        result = current.item;
        if (current == head) {
            head = head.next;
        } else {
            previous.next = current.next;
        }
        size--;
        return result;
    }

    /**
     * 更改指定位置的元素
     * 步骤：
     * ①根据下标找到指定节点
     * ②然后再将节点的值替换掉
     */
    public boolean set(int index, T t) {
        Node<T> l = head;
        for (int i = 0; i < index; i++) {
            l = l.next;
        }
        l.item = t;
        return true;
    }

    /**
     * 删除头结点
     *
     * @return
     */
    public T deleteHead() {
        T obj = head.item;
        head = head.next;
        size--;
        return obj;
    }

    /**
     * 打印链表内元素
     */
    public void display() {
        if (size > 0) {
            Node node = head;
            int tempSize = size;
            if (tempSize == 1) {//当前链表只有一个节点
                System.out.println("[" + node.item + "]");
                return;
            }
            while (tempSize > 0) {
                if (node.equals(head)) {
                    System.out.print("[" + node.item + "->");
                } else if (node.next == null) {
                    System.out.print(node.item + "]");
                } else {
                    System.out.print(node.item + "->");
                }
                node = node.next;
                tempSize--;
            }
            System.out.println();
        } else {//如果链表一个节点都没有，直接打印[]
            System.out.println("[]");
        }
    }

}
