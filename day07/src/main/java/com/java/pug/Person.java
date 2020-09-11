package com.java.pug;

public class Person {
    public String name;
    public String a = "a";
    private int age;

    public Person() {
        this.name = "人";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void walk() {
        System.out.println(name + "走路");
    }

    public void speek() {
        System.out.println(name + "说话 " + this.a);
    }

    public void showInfo() {
        System.out.printf("name: [%s], age: %s years old\n", name, age);
    }
}
