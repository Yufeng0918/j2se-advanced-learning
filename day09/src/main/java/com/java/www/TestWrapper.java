/*

* */

package com.java.www;

import org.junit.Test;

public class TestWrapper {
    @Test
    public void test1() {
        int i = 10;
        System.out.println(i);
       boolean b18 = false;

        Integer i21 = new Integer(i);
        System.out.println(i21.toString());

        Float f = new Float("12.6F");
        System.out.println(f);

        Boolean b1 = new Boolean("true"); // true
        System.out.println(b1);
        Boolean b2 = new Boolean(false);
        System.out.println(b2);
        Boolean b3 = new Boolean("true100");
        System.out.println(b3);

        Order o1 = new Order();
        System.out.println(o1.status);

    }

    @Test
    public void test2() {
        Integer i44 = new Integer(208);
        int i45 = i44.intValue();
        System.out.println(i45);
        Float f48 = new Float(2.3);
        float f49 = f48.floatValue();
        System.out.println(f49);
        System.out.println(f48.intValue()); // 2
        Boolean b52 = new Boolean(true);
        boolean b53 = b52.booleanValue();
        System.out.println(b53);

    }

    @Test
    public void test3() {
        int i61 = 18;
        Integer i62 = i61;
        System.out.println(i62.toString());
        Boolean b63 = false;

        Integer i66 = new Integer(15);
        int i67 = i66;
        System.out.println(i66);

    }

    @Test
    public void test4() {
        //  �������ݡ���װ�� -> StringString�ࣺ����String.valueOf(Xxx x)����
        int i75 = 22;
        String s76 = i75 + ""; // "22"
        Float f77 = 24.0F; // �Զ�װ��
        String s78 = String.valueOf(f77);
        System.out.println(s78);
        String s80 = String.valueOf(true); // "true"
        System.out.println(s80);

    }

    @Test
    public void test5() {
        // String�� -> �����������͡���װ�ࣺ���ð�װ���parseXxx(String str)����
        int i88 = Integer.parseInt("33");
        System.out.println(i88);
        boolean b90 = Boolean.parseBoolean("1"); // ��"true"������Ϊfalse
        System.out.println(b90);
        boolean b92 = Boolean.parseBoolean("true");
        System.out.println(b92);
        float f94 = Float.parseFloat("22.2F");
        System.out.println(f94);
    }

}

class Order {
    Boolean status;

}