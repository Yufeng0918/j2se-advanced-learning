day12 异常处理
==



## 1. 定义

在java语言中，将程序执行中发生的不正常的情况称为“异常”， 不包括开发过程中的语法错误和逻辑错误。**java.lang.Throwable**



### 分类

#### Error 

错误，程序中不进行处理。java虚拟机无法解决的严重问题，如JVM系统内部错误，资源耗尽等

#### Exception 

在编写程序时，就要考虑到这些异常的处理。编程错误或偶然外因导致的一般性问题。如空指针、网络中断等

* **编译时异常**，在编译期间会出现的异常（执行javac 命令时，出现异常，必须处理（将异常进行捕获，转化为运行时异常），否则编译出错不能运行。Exception的子类中除了RuntimeException以外的
* **运行时异常**（执行java命令时，出现异常，可不处理）。RuntimeException的所有子类



### 解决方法

* 遇到错误就终止程序
* 由程序员在编写程序时，就考虑到错误的检测、错误消息的提示及错误的处理



### 特点

执行一个程序时，如果出现异常，那么异常后面的代码就不再执行



### 异常类层次

[java异常类层次结构详情](./java异常类层次.md)



## 2. 处理机制

异常处理的抓抛模型。异常类对象的生成

- 由虚拟机自动生成，自动抛出异常
- 由开发人员手动创建，手动抛出异常

如果一个方法内抛出异常，该异常对象会被抛给调用者方法中处理。  如果异常没有在调用者方法里处理,它继续被抛给这个调用方法的上层方法。  这个过程将一起继续下去，直到异常被。这个过程被称为捕获异常(catch)

**如果一个异常回到main()方法，并且main()也不处理，则程序运行终止**

程序员通常只能处理Exception，而对Error无能为力



## 3. 处理方式1：抓取异常

**try-catch-finally**

```java
try {
    // 可能出现异常的代码
} catch(Exception1 e) {
    // 异常1处理方法
} catch(Exception2 e) {
    // 异常2处理方法
} catch(Exception3 | Exception4 | Exception5 e) {
    // 异常2处理方法
    e.printStackTrace();
} finally {
    // 一定要执行的代码
}
```


finall 是可选的

try 块内声明的变量为局部变量。出了try { }就不能被调用了

catch 语句内对异常的处理
- getMessage()    -- 返回String关键错误信息
- printStackTrace()    -- 打印异常类名和异常信息，以及异常出现在程序中的位置。返回值void

可以多个catch语句，try中抛出的异常类对象从上往下匹配catch中的异常类的类型，一旦匹配就执行catch中的代码。执行完就跳出后面的catch语句。如果异常处理了，其后的代码继续执行

**对于运行时异常，可以不显式的进行处理；对于编译时异常，必须要显式的进行处理**

若catch中多个异常类型是"并列"关系，哪个在上都可以。若catch中多个异常类型是"包含"关系，**必须子类异常类放在父类异常类的上面进行处理，否则编译报错。**

finally中的语句一定会被执行。不管try、catch中是否仍有异常未被处理，以及是否有return语句，除了是明确指定退出程序外，如System.exit(1)

try-catch可以嵌套， 一个catch抓取多个异常时，多个异常类型之间用 "|" 分隔



## 4. 处理方式2：声明抛出异常

在方法的声明处，显式的抛出该异常对象的类型

* 如果一个方法中(包括编译、运行时)肯能出现某种异常，但是并不能确定如何处理这种异常，则此方法应显式的声明抛出异常，表明该方法将不对这些异常进行处理，而由该方法的调用者负责处理

* 在方法中声明用throws语句可以声明抛出异常的列表，throws的异常列表可以是方法中产生的异常类型，也可以是它的父类

  ```java
  public void readFile() throws FileNotFoundException, IOException { }
  ```

* 当在此方法内部出现异常时，会抛出一个异常类的对象，抛给方法的调用者

* 异常对象可以逐层向上抛，一直到main方法中。在向上抛的时候，可以用try-catch-finally进行处理

* main()方法中抛出异常时到JVM中

* **重写方法不能抛出比被重写方法范围更大的异常类型**。 在多态的情况下，对method()方法的调用时异常的捕获按照父类声明的异常处理



## 5. 手动抛出异常

Java异常类对象除在程序执行过程中出现异常时由系统自动生成并抛出，也可根据需要人工创建并抛出。
+ 首先要生成异常类的对象，让后通过throw语句抛出异常对象
+ 抛出的异常对象必须是Throwable或其子类的实例

```JAVA
IOException e = new IOException();
throw e;
```



## 6. 自定义异常类

一般的，用户定义的异常类都是RuntimeException的子类。通常要编写几个重载的构造器, 同样可以通过throw抛出自定义异常类的对象

自定义异常最重要的是异常类的名字，当异常出现时，可以根据名字判断异常类型

```java
class MyException extends Exception {
   	static final long serialVersionUID = 1L;
	private int idnumber;
 	public MyException(String message, int id) {
		super(message);
		this.idnumber = id;
 	} 
	public int getId() {
		return idnumber;
 	}
}
```

