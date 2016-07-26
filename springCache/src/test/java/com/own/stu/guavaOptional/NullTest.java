package com.own.stu.guavaOptional;

/**
 * Created by bf50 on 2016/2/19.
 */

/**
 * null本身不是对象，也不是Objcet的实例：
 　　null只是一个关键字，用来标识一个不确定的对象，他既不是对象，也不是Objcet对象的实例。
 */
public class NullTest {
    public static void testNull(){
        int age = 0;
        System.out.println("user age:"+age);

        long money;
        money=10L;
        System.out.println("user money"+money);

        String name = null;
        System.out.println("user name:"+name);
    }
}
