**何为链表**

链表就是一种使用对象引用变量来创建对象间的链的数据结构.对象引用变量可用于创建链式结构,对象引用变量所存储的特点地址一般无关紧要.换句话说,重要的是能够使用引用变量来访问对象,而对象在内存中的位置并不重要

下面这个类就是一个链式结构:
```
public class Person{
    private String name;
    private int age;
    private Person next;
}
```

注意:链表单独需要一个引用变量,用来表示其第一个节点,如果某个节点的next指向null,则表示链表在此处终结

**链表与数组之间的区别**

数组的特点:

- 在内存中数组是一块连续的区域
- 数组需要预留空间,在使用前需要先申请占内存的大小,可能会浪费内存空间
- 插入数据和删除数据效率低,插入数据时,这个位置后面的数据在内存中都要往后移,删除数据时,又需要往前移
- 随机读取效率很高.因为数组是连续的,知道每一个数据的内存地址,可以直接找到给地址的数据
- 不利于扩展,数组定义的空间不足时要重新定义数组

**链表的特点**

- 在内存中可以存在任何地方,不要求连续
- 每一个数据都保存了下一个数据的内存地址,通过这个地址找到下一个数据
- 增加和删除数据很容易
- 查找数据时效率低,因为不具有随机访问性,所以访问某个位置的数据时要从第一个数据开始访问起,然后根据第一个数据保存的下一个数据的地址找到第二个数据,以此类推
- 不指定大小,扩展方便.链表大小不用定义,数据随意增删

**各自的优缺点**

==数组的优点==:

随机访问强

查找速度快

==数组的缺点==:

插入和删除效率低

可能浪费内存

内存空间要求高,必须有足够的连续内存空间

数组大小固定,不能动态扩展

==链表的优点==

插入删除速度快

内存利用率高,不会浪费内存

大小没有固定,拓展很灵活

==链表的缺点==

不能随机查找,必须从第一个开始遍历,查找效率低

增加了指针域,空间开销比较大


   - | 数组 | 链表
---|--- |---
读取 | O(1) | O(n)
插入 | O(n) | O(1)
删除 | O(n) | O(1)


#### 单链表 ####

单链表是链表结构中最简单的.一个单链表的节点(Node)分为两部分,第一部分(item)存放信息,第二部分(next)存放下一个节点的地址.链表最后一个节点存储地址部分指向null.

单向链表只可以向一个方向遍历,一般查找一个节点的时候需要从第一个节点开始每次访问下一个节点,一直访问到需要的位置.

单链表具体实现:

```
/**
 * 单链表实现类
 * @author song
 *
 */
public class SingleLinkedList<T> {
	
	/*
	 * 节点类
	 */
	private class Node<T>{
		T item;
		Node<T> next;
		public Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}
	
	//链表长度
	private int size = 0;
	
	//头结点
	private Node<T> head;
	
	public SingleLinkedList(){}
	
	/*
	 * 将元素添加到链表最前面
	 * 步骤:
	 * ①将元素封装成一个节点newNode(暂时不设指向next)
	 * ②判断头结点是否为null,若为null,直接将节点newNode设置为头结点
	 * ③若不为null,则将newNode的next指向之前的头结点head,然后再将newNode设为新的头结点
	 */
	public void linkFirst(T t){
		Node<T> newNode = new Node<T>(t, null);
		if(head == null){
			head = newNode;
		}else{
			newNode.next = head;
			head = newNode;
		}
		size++;
	}
	
	/*
	 * 将元素添加到链表最后
	 * 步骤:
	 * ①首先将元素封装成一个节点newNode,因为是插入到链表最后,所以newNode的next指向null
	 * ②判断头结点是否为null,若为null,则直接将newNode置为头结点
	 * ③若不为null,则根据下标找到链表的最后一个节点
	 * ④然后将最后一个节点的next指向newNode
	 * 思考:其实可以设置一个尾节点,这样将节点插入到链表末尾就更加方便
	 */
	public void linkLast(T t){
		Node<T> newNode = new Node<T>(t, null);
		Node<T> l = head;
		if(l == null){
			head = newNode;
		}else{
			for(int i = 0;i < size-1;i++){
				l = l.next;
			}
			l.next = newNode;
		}
		size++;
	}
	
	/*
	 * 将元素插入到指定节点之前
	 * A  0  B
	 * 将元素插入到0位置步骤:
	 * ①如果index为0,则说明此时是将元素添加到头部
	 * ②根据下标获取到0位置的前一个节点A以及后一个节点B
	 * ③然后将元素封装成一个节点newNode,newNode的next指向节点B
	 * ④将前一个节点A的next指向newNode
	 */
	public void add(int index, T t){
		Node<T> f = head;
		Node<T> newNode = null;
		//在元素插入第一位
		if(index == 0){
			newNode = new Node<T>(t, f);
			head = newNode;
		}else{
			for(int i = 0;i < index - 1;i++){
				f = f.next;
			}
			Node<T> oldNext = f.next;
			newNode = new Node<T>(t, oldNext);
			f.next = newNode;
		}
		size++;
	}
	
	/*
	 * 添加元素到尾部
	 */
	public boolean add(T t){
		linkLast(t);
		return true;
	}
	
	/*
	 * 根据下标获取指定节点
	 * 根据下标循环遍历,知道找到index位置为止
	 */
	public Node<T> node(int index){
		Node<T> f = head;
		for(int i = 0;i < index;i++){
			f = f.next;
		}
		return f;
	}
	
	/*
	 * 根据下标获取元素
	 */
	public T get(int index) throws Exception{
		checkIndex(index);
		return node(index).item;
	}
	
	/*
	 * 获取链表长度
	 */
	public int size(){
		return size;
	}
	
	/*
	 * 判断链表是否为空
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	/*
	 * 删除指定元素,如果有多个相同元素,就删除第一个
	 * 注意:若链表里面有两个或两个以上的相同元素,只移除第一个找到的元素
	 * 步骤:
	 * ①定义两个节点:一个是需要删除元素的节点current,另一个是需要删除节点的上一个节点previous(都让他们从head节点开始)
	 * ②然后通过需要删除的元素obj,找到obj所对应的节点以及上一个节点
	 * ③若被删除的节点是头结点(此时current和previous都是head节点),就直接将head的下一个节点重新定义为头结点
	 * ④若删除的节点不是头结点,就将被删除的上一个节点(previous)的next指向被删除节点(current)的下一个节点
	 * ⑤再将size-1
	 */
	public boolean remove(Object obj){
		//判断链表是否为空
		if(size == 0){
			return false;
		}
		Node<T> current = head;//将要被删除的节点
		Node<T> previous = head;//被删除节点的上一个节点
		while(current.item != obj){
			//current.next为null就说明整个链表已遍历结束,但没有找到需要被删除的元素
			if(current.next == null){
				return false;
			}else{
				previous = current;
				current = current.next;
			}
		}
		//被删除的节点是头结点
		if(current == head){
			head = head.next;
		}else{
			previous.next = current.next;
		}
		size--;
		return true;
	}

	/*
	 * 删除指定位置的元素
	 * 步骤:
	 * ①首先定义两个节点:要被删除的节点(current)和要被删除节点的上一个节点(previous)
	 * ②根据下标从头开始找到要被删除的节点以及其上一个节点
	 * ③如果要被删除的节点是头结点,就直接将头结点的下一个节点设为新的头结点
	 * ④否则就将previous的next指向current的next
	 * ⑤再将size-1
	 */
	public T remove(int index) throws Exception{
		T item = null;
		checkIndex(index);
		Node<T> current = head;//要被删除的节点
		Node<T> previous = head;//要被删除节点的上一个节点
		for(int i = 0;i < index;i++){
			previous = current;
			current = current.next;
		}
		item = current.item;
		if(current == head){
			head = head.next;
		}else{
			previous.next = current.next;
		}
		size--;
		return item;
	}
	
	/*
	 * 更改指定位置元素值
	 * 步骤:
	 * ①判断下标是否越界
	 * ②根据下标找到指定节点
	 * ③然后将节点的值改掉
	 */
	public boolean set(int index, T t) throws Exception{
		checkIndex(index);
		Node<T> l = head;
		for(int i = 0;i < index;i++){
			l = l.next;
		}
		l.item = t;
		return true;
	}
	
	/*
	 * 下标检查
	 */
	private void checkIndex(int index) throws Exception{
		if(index < 0 || index > size-1){
			throw new Exception("下标异常");
		}
	}

}
```

#### 双向链表 ####

双向链表节点Node分为三部分,第一部分为前置节点(prev),第二部分为节点信息(item),第三部分为后置节点(next)

双向链表既可以从前往后遍历,也可以从后往前遍历


```
/**
 * 双向链表
 * @author song
 *
 * @param <T>
 */
public class LinkedList<T> {
	
	/**
	 * 节点类
	 */
	private static class Node<T>{
		T item;
		Node<T> prev;//前置节点
		Node<T> next;//后置节点
		public Node(Node<T> prev, T item, Node<T> next) {
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
	}
	
	/*
	 * 第一个节点的引用(头)
	 */
	Node<T> first;
	
	/*
	 * 最后一个节点的引用(尾)
	 */
	Node<T> last;
	
	/*
	 * list中元素的数目
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
```

