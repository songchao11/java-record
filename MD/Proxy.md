**代理模式**

代理(Proxy)是一种设计模式,提供了对目标对象另外的访问方式,即通过代理对象访问目标对象.这样做的好处是:可以在目标对象实现的基础上,增强额外的功能操作,即扩展目标对象的功能.

举个例子来说明代理的作用:如果我们公司有一个产品需要找一个明星来代言,那么我们并不是直接联系明星本人,而是联系明星的经纪人.明星就是一个目标对象,他只要负责代言活动,而其他的一些琐碎事情就交给他的代理人

代理模式的关键点:代理对象与目标对象,代理对象是对目标对象的扩展,并会调用目标对象

#### 1.静态代理 ####
静态代理在使用时,需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者继承相同的父类

代码示例:

接口:ClothFactory
```
//接口
interface ClothFactory{
	void productCloth();
}
```
目标对象:NickClothFactory

```
//被代理类(目标对象)
class NickClothFactory implements ClothFactory{

	@Override
	public void productCloth() {
		System.out.println("Nick工厂生产Nick");
	}
}
```
代理对象:ProxyFactory

```
//代理类(代理对象)
class ProxyFactory implements ClothFactory{

	//接收保存目标对象
	ClothFactory clothFactory;
	
	//创建代理类的对象时,实际传入一个被代理类的对象
	public ProxyFactory(ClothFactory cFactory){
		this.clothFactory = cFactory;
	}
	
	@Override
	public void productCloth() {
		System.out.println("代理类开始执行,收代理费");
		clothFactory.productCloth();
	}
	
}
```

```
public class TestClothProduct {

	public static void main(String[] args) {
		NickClothFactory nick = new NickClothFactory();
		ProxyFactory pro = new ProxyFactory(nick);
		pro.productCloth();
	}
}
```
静态代理总结:

①可以做到在不修改目标对象功能的情况下,对目标对象进行功能扩展

②因为代理对象需要与目标对象实现相同的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都需要维护.

#### 2动态代理 ####
==动态代理有如下特点:==

①代理对象不需要实现接口

②代理对象的生成,是利用JDK的api,动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)

③动态代理也叫:JDK代理,接口代理

==JDK中生成代理对象的API:==

代理类所在的包:java.lang.reflect.Proxy

jdk实现代理只需要使用newProxyInstance方法,该方法需要三个参数

```
/*
 * ClassLoader loader:指定当前目标对象使用类加载器,获取加载器的方法是固定的
 * Class<?>[] interfaces:目标对象实现的接口的类型,使用泛型方式确认类型
 * InvocationHandler h:事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
 */
static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h )
```
代码示例:

接口:Subject

```
interface Subject{
	void action();
}
```
被代理类(目标对象):RealSubject

```
//被代理类
class RealSubject implements Subject{
	@Override
	public void action() {
		System.out.println("我是被代理类");
	}
}
```
代理工厂类:MyInvocationHandler

```
class MyInvocationHandler implements InvocationHandler{
	Object obj;//实现了接口的被代理类的对象的声明
	//作用:①给被代理类的对象实例化 ②返回一个代理类的对象
	public Object blind(Object obj){
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}
	//当通过代理类的对象发起对被重写的方法调用时,都会转化为对如下invoke方法的调用
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//method方法的返回值
		Object returnVal = method.invoke(obj, args);
		return returnVal;
	}
}
```
测试类:

```
public class TestProxy {
	public static void main(String[] args) {
		//1.被代理类的对象
		RealSubject real = new RealSubject();
		//2.创建一个实现了InvocationHandler接口的类的对象
		MyInvocationHandler handler = new MyInvocationHandler();
		//3.调用blind()方法,动态的返回一个同样实现了real所在类实现的接口Subject的代理类对象
		Object obj = handler.blind(real);
		Subject sub = (Subject)obj;//此时sub就是代理类对象
		sub.action();//转到对InvacationHandler接口的实现类的invoke()方法的调用
		
	}
}
```
==总结==:代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理

jdk动态代理的代理对象在创建时,需要使用业务实现类所实现的接口作为参数(因为在后面地理方法时需要根据接口内的方法名进行调用).如果业务实现类是没有实现接口而是直接定义业务方法的话,就无法使用jdk动态代理了.并且如果业务实现类中新增了接口中没有的方法,这些方法是无法被代理的(因为无法被调用)

#### 3.CGlib实现动态代理 ####

cglib是针对类来实现代理的,原理是对指定的业务类生成一个子类,并覆盖其中业务方法实现代理.因为采用的是继承,所以不能对final修饰的类进行代理.

①首先定义业务类,无需实现接口(当然实现接口也是可以的,不影响)

```
class BookImpl{
	public void addBook(){
		System.out.println("新增书籍");
	}
}
```
②实现MethodInterceptor方法代理接口,并创建代理类

```
class BookCGlib implements MethodInterceptor{
	private Object target;//业务类对象,供代理方法中进行真正的业务方法调用
	
	//相当于jdk动态代理中的绑定
	public Object getInstance(Object target){
		this.target = target;//给业务方法赋值
		//创建加强器,用来创建动态代理类
		Enhancer enhancer = new Enhancer();
		//为加强器指定要代理的业务类(即:为下面生成的代理类指定父类)
		enhancer.setSuperclass(this.target.getClass());
		//设置回调:对于代理类上所有方法的调用,都会调用CallBack,而CallBack则需要实现intercept()方法进行拦截
		enhancer.setCallback(this);
		return enhancer.create();
	}

	//实现回调方法
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("-----预处理-------");
		proxy.invokeSuper(obj, args);//调用业务类(父类中的方法)
		System.out.println("-----调用后操作-----");
		return null;
	}
	
} 
```
③创建业务类和代理类对象,然后通过代理类对象.getInstance(业务类对象),返回一个动态代理类对象(它是业务类的子类,可以用业务类引用指向它.最后通过动态代理类对象进行方法调用)

```
public class TestCGlib {
	public static void main(String[] args) {
		BookImpl bookImpl = new BookImpl();
		BookCGlib cglib = new BookCGlib();
		BookImpl bookCGlib = (BookImpl) cglib.getInstance(bookImpl);
		bookCGlib.addBook();
	}
}
```

==比较:==

①静态代理是通过在代码中显式定义一个业务实现类一个代理,在代理类中对同名的业务方法进行包装.用户通过代理类调用包装过的业务方法;

②jdk动态代理是通过接口中的方法名,在动态生成的代理类中调用业务实现类的同名方法

③CGlib动态代理是通过继承业务类,生成的动态代理类是业务类的子类,通过重写业务方法进行代理











