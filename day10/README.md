day10高级类特性
==



## 1. main方法

```java
public class MainTest {
    public static void main(String[] args) {
    }
}
```



## 2. 初始化块

初始化块(代码块)作用, 对java对象进行初始化。 代码块修饰符只能是空或static



### 静态代码块 

+ static { }

* **随着类的加载而加载，且只能被加载一次**，内存中同样只有一份
* 一个类中可以有多个静态代码块，多个代码块之间按照顺序结构执行
* **静态代码块要早于构造器执行**
* **静态代码块中只能执行静态的属性或者静态的方法**
* 静态的要早于非静态执行



### 非静态代码块

+ { }, 里面可以有输出语句

* 可以对类的属性(静态的、非静态的)进行初始化操作，同时也可以用本类中的方法(静态、非静态的)
* **一个类可以有多个非静态的代码块，多个代码块之间按照顺序结构执行**
* **创建一个类的对象，非静态代码块就加载一次**
* **非静态代码块的执行要早于构造器**



### 属性赋值顺序  

+ 默认初始化 
+ 静态代码块
+ 显式初始化/代码块初始化（顺序）
+ 构造器中赋值  
+ 通过方法对对象的属性进行修改  

```java
public class OrderTest {
    public static void main(String[] args) {
        Order o1 = new Order();
        System.out.println(o1);
        Order o2 = new Order();
        System.out.println(o2);
    }
}

class Order {
    // 实例变量
    private int orderId = 100;
    private String orderName;

    // 类变量（静态属性）
    private static double price = 0.0;

    // 非静态代码块（初始化块）
    {
        orderId = 200;
        orderName = "mai mai";
        System.out.println("我是非静态初始化块1");
    }

    // 非静态代码块
    {
        System.out.println("我是非静态初始化块2");
    }

    // 构造器
    public Order() {
        super();
        orderId = 600;
        System.out.println("我是空参构造器");
    }

    public Order(int orderId, String orderName) {
        this.orderId = orderId;
        this.orderName = orderName;
    }

    // 非静态代码块
    {
        System.out.println("我是非静态初始化块0");
    }

    // 静态代码块
    static {
        Order.price = 99.9;
//        orderId = 301; // 静态代码块中不能调用非静态的属性
        showInfo();
        System.out.println("我是静态代码块1");
    }

    static {
        System.out.println("我是静态代码块2");
    }


    public String toString() {
        return "Order{ orderId=" + orderId +
                ", orderName='" + orderName + "'" +
                ", price=" + Order.price +
                " }";
    }

    public static void showInfo() {
        System.out.println("静态方法");
    }
}
```



## 3. final关键字

**final可以修饰类，属性，方法**。不能修饰构造器 
目的：禁止被修改、被继承  

### 修饰类

这个类不能被继承。如String，StringBuffer，System类

### 修饰方法

**不能被重写**, 功能确定不变的，就定义为final方法，如Object类中的getClass。 final于访问权限修饰符顺序可先可后，建议先权限修饰符，再final。如：public static void walk() { }

### 修饰属性

此属性就是常量。建议常量名字母全大写。常量一旦初始化，就不能修改。

赋值：**可以显示的赋值，代码块，构造器，没有默认值。且必须先赋值才能使用**

```java
public class finalTest {}

final class Nvwa {
    private String color;
    private double height;
    private boolean sex;
    private String nation;
}

class Animal {
    private String name;
    private final boolean status = true;
    private int age;
    protected final int legs;

    // 构造器
    public Animal() {
        super();
//        status = false; // 不能*/
        legs = 4;
    }

    // 方法
    public void shout() {
        System.out.println("叫");
    }

    public static void walk() {
        System.out.println("走");
    }
}

/*
class Yello extends Nvwa {

}
// 不能继承final修饰的类
*/

/*
class myString extends String {

}
// 不能继承final修饰的类
*/

class Dog extends Animal {
    public void shout() {
        System.out.println("汪汪叫");
    }

    /* // final修饰的方法不能被重写
    public void walk() {

    }
    */
}


/*
public class Something {
    public int addOne(final int x) {
        return ++x; // 报错，不能再修改了
    }
}
*/

class Something {
    public static void main(String[] args) {
        Other o = new Other();
        new Something().addOne(o);
    }

    public void addOne(final Other o) { // final修饰的时对象的引用地址，只要引用地址值不变就可以
        o.i++;
    }
}

class Other {
    public int i;
}

```



## 4. Abstract 抽象类

类的设计应该保证父类和子类能够共享特征。有时将一个父类设计得非常抽象，以至于它没有具体的实例，这样的类叫做抽象类。**只能修饰类、方法  目的：让继承的子类去实现(重写方法)**

### 修饰类

* 不能被实例化
* 抽象类有构造器（凡是类都有构造器）
* **有抽象方法的类一定是抽象类**
* **抽象类可以没有抽象方法**
* abstract于访问权限修饰符顺序可先可后，建议先权限修饰符，再abstract。如：public abstract class aa { }

### 修饰方法

* 格式：没有方法体。如：public abstract void walk();
* 抽象方法只保留方法的功能，具体的执行交给继承该抽象的子类去实现，实现方法：子类重写抽象方法
* 有抽象方法的抽象类的**子类**，必须实现所有的抽象方法，才能实例化。**未全部重写的类只能定义为抽象类**
* 抽象类的子类，可以为抽象类，可以只实现一部分抽象方法，还可以再添加新的抽象方法。那么子类必须实现多层父类的抽象方法



**注意事项**

* **abstract不能于private、final、static修饰符公用**
* 不能修饰属性、构造器

```java
public class AbstractTest {
    public static void main(String[] args) {
//        Person p1 = new Person();
//        p1.eat();
//        System.out.println(p1);

        Person s1 = new Student("灵气", 30); // 多态
        System.out.println(s1);

        Worker w1 = new Worker();
        w1.walk();

    }
}

abstract class Person {
    private String name;
    protected int age;
//    protected abstract double height; // 不能修饰属性

    // 构造器
    public Person() {
        super();
    }

    /*
    // 不能修饰构造器
    public abstract Person(String name) {
        super();
        this.name = name;
    }
    */

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 方法
    public void eat() {
        System.out.println("eating");
    }

    public abstract void walk();

//    public static abstract void sleep(); // abstract不能与static一起

//    private abstract void see(); // abstract不能与 private一起

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Person{" +
                " name='" + name + "'" +
                ", age=" + age +
                " }";
    }

}

class Worker extends Person {

    // 构造器
    public Worker() {
        super();
    }

    // 方法
    public void eat() {
        System.out.println("工人吃饭");
    }

    public void walk() {
        System.out.println("工人走路");
    }

}

class Student extends Person {
    // constructor
    public Student() {
        super();
    }

    public Student(String name, int age) {
        super(name, age);
    }

    // methods
    public void eat() {
        System.out.println("student eating");
    }

    public void walk() {
        System.out.println("student walking");
    }

}

abstract class Man extends Person {
    public void walk() {
        System.out.println("男人走路");
    };

    public abstract void smoking();
}

class Driver extends Man {
    @Override
    public void smoking() {
        System.out.println("帅气地吸烟");
    }
}
```



## 5. interfacer接口

接口用途：被实现类去实现接口定义的功能（面向接口编程）
* interface是与类等级平级的一个概念
* 接口可以看做是一个特殊的抽象类。
* **接口是常量, 抽象方法的集合，只能有常量和抽象方法，不能包含变量和非default方法**
* 没有构造器，也就不能实例化
* 接口定义的是一种功能。此功能可以被类实现（implements）
* **接口里所有的常量都是被  public static final 修饰。不写也会默认加上**
* **所有的方法都是被 public abstract 修饰。不写默认也会加上**
* 实现接口的类，必须重写所有的抽象方法，才能实例化。若没有重写所有的方法，则此类只能定义为抽象类
* 类可以同时实现多个接口。相当于多重继承。类的继承是单个多层继承的
* 接口可以被接口继承，接口允许多重多层继承，如：interface GG extends AA, DD { }
* 一个类可以同时承继父类、实现接口，必须先写extends，后写implements，如：class EE extends FF implements AA, DD { }
* **接口与实现类之间存在多态性**


```java
public class InterfaceTest {
    public static void main(String[] args) {
        Audi a1 = new Audi("奥迪R8 V10 Spyder", 229.98);
        System.out.println(a1);
        System.out.println(a1.run());
    }
}

interface AA {
    // 常量
    // 接口里所有的常量都是被  public static final 修饰。不写也会默认加上
    public static final int I = 99;
    boolean FLAG = false;
//    int age; // 不能有变量，此处会报错

    // 方法
    // 抽象方法：所有的方法都是被 public abstract 修饰。不写默认也会加上
    abstract void eat();
    void walk();

}

interface DD {
    int lifeValue();
}


abstract class BB implements AA{

}

class CC implements AA{

    public void eat() {
        System.out.println("eating ...");
    }

    public void walk() {
        System.out.println("walking ...");
    }

}

class FF {
    public boolean status() {
        return true;
    }
}

class EE extends FF implements AA, DD {
    // 多重实现，还可以同时继承一个类
    public void eat() {

    }

    public void walk() {

    }

    public int lifeValue() {
        return 100;
    }
}

interface GG extends AA, DD {
    // 接口允许多重继承
}

//
interface Car {
    public static final int WHEEL = 4;

    // 是自动档
    boolean isAutoGear();
}

interface ChinaCar extends Car {
    /*
    接口继承
    * */
    String NATION = "China";

    String run ();
}


class Audi implements ChinaCar{
    private String model;
    private double price; // 单位：万


    // 构造器
    public Audi() {
        super();
    }

    public Audi(String model, double price) {
        this.model = model;
        this.price = price;
    }

    // 方法
    public boolean isAutoGear() {
        return true;
    }

    public String run() {
        return "120 KM/H在跑";
    }

    public String toString() {
        return "Audi{ " +
                "model='" + model + "'" +
                ", price=" + price + "万" +
                ", isAutoGear=" + isAutoGear() +
                " }";
    }
}
```
### 多态示例


```java
public class InterfacePolymorphicTest {
    public static void main(String[] args) {

        Duck d1 = new Duck();
        InterfacePolymorphicTest.ru(d1); // 相当于 runner r = new Duck();
        InterfacePolymorphicTest.sw(d1); // 相当于 swimer r = new Duck();
        System.out.println(InterfacePolymorphicTest.fl(d1)); // 相当于 flier r = new Duck();

    }

    // 多态
    public static void ru(runner r) {
        r.run();
    }

    public static void sw(swimer s) {
        s.swim();
    }

    public static String fl(flier f) {
        return f.fly();
    }
}

interface runner {
    void run();
}

interface swimer {
    public abstract void swim();
}

interface flier {
    String fly();
}

class Duck implements runner, swimer, flier {

    // 构造器
    public Duck() {
        super();
    }

    // 方法
    public void run() {
        System.out.println("两脚掌跑路");
    }

    public void swim() {
        System.out.println("河水里游");
    }

    public String fly() {
        return "展翅飞翔";
    }
}
```


### 接口用法总结
* 通过接口可以实现不相关类的相同行为，而不需要考虑这些类之间的层次关系
* 通过接口可以指明类需要实现的多个方法，一般用于定义对象的扩张功能
* 接口主要用来定义规范。解除耦合关系
