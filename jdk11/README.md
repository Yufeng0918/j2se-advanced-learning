java 11新特性
==



## 1. String类新增方法
```JAVA
public boolean isBlank()
//判断此字符串是否为空白是，制表符、换行符等不算内容

public String strip()
//去除收尾空白，功能与public String trim()相同，但效率更高

public String stripLeading()
//去除首部空白

public String stripTrailing()
//去除尾部空白

public String repeat(int count)
//返回一个此字符串重复count拼接的新字符串

public Stream<String> lines()
//返回使用换行符分隔此字符串后组成的Stream。此时可以调用Stream对象的count()方法获取行数

```




## 2. Optional类新增方法
isEmpty(): 判断此Optional对象的value是否为null

```JAVA
public void test1() {
        Optional<Object> optional = Optional.ofNullable(null);
        System.out.println(optional.isEmpty()); // true
}
```



## 3. 局部变量类型推断升级

在var上添加注解的语法格式

```JAVA
    public void test1() {
        Consumer<String> consumer = (t) -> System.out.println(t.toUpperCase());
        Consumer<String> consumer1 = (@Deprecated String t) -> System.out.println(t.toUpperCase());

        // 错误写法
//        Consumer<String> consumer2 = (@Deprecated t) -> System.out.println(t.toUpperCase());


        Consumer<String> consumer3 = (@Deprecated var t) -> System.out.println(t.toUpperCase());
    }
```



## 4. HttpClient增强

提供同步请求和异步请求， 替换**HttpURLConnection**

```JAVA
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.baidu.com")).build();
HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
HttpResponse<String> response = client.send(request, bodyHandler); // 同步请求方法
String body = response.body();
System.out.println(body);


HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.baidu.com")).build();
HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
CompletableFuture<HttpResponse<String>> sendAsync = client.sendAsync(request, bodyHandler); // 异步请求
sendAsync.thenApply(t -> t.body()).thenAccept(System.out::println);
```





## 5. 简单的编译运行程序

```shell
# 编译 
javac Javastack.java 

#运行 
java Javastack

#在我们的认知里面，要运行一个Java源代码必须先编译，再运行，两步执行动作。
#而在未来的Java11版本中，通过一个java命令就直接搞定了，如以下所示：
java Javastack.java
```

一个命令编译运行源代码的注意点
* 执行源文件中的第一个类,第一个类必须包含主方法。
* 并且不可以使用其它源文件中的自定义类,本文件中的自定义类是可以使用的。



## 6. 其他特性

* 废弃Nashorn引擎
* 新增ZGC垃圾回收引擎
```text
暂不建议用于生产。

优势：
* GC暂停时间不会超过10ms 
* 既能处理几百兆的小堆,也能处理几个T的大堆(OMG)
* 和G1相比,应用吞吐能力不会下降超过15%
* 为未来的GC功能和利用colord指针以及Loadbarriers优化奠定基础初始只支持64位系统

```
* Unicode10 
* DeprecatethePack200ToolsandAPI 
* 新的Epsilon垃圾收集器 
* 完全支持Linux容器（包括Docker）
* 支持G1上的并行完全垃圾收集 
* 最新的HTTPS安全协议TLS1.3 
* Java Flight Recorder
