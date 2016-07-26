package com.own.stu.traditional.threadLocal;

import java.util.Random;

/**
 * Created by CHANEL on 2016/6/7.
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> local = new ThreadLocal<String>();
    public static void main(String[] args) {

        for(int i=0;i<2;i++){

            new Thread(new Runnable() {
                public void run() {
                    final int num = new Random().nextInt();
                    local.set(num + "");
                    Student.getStudent().setAge(12);
                    Student.getStudent().setName("萌" + num);
                    print();
                    printMore();
                }
            }).start();
        }
    }

    public static void print(){
        Student student = Student.getStudent();
        System.out.println("print : " + Thread.currentThread().getName() + " , " +local.get());
        System.out.println("print : " + Thread.currentThread().getName() + " , name: " +
                student.getName() + " , age : " + student.getAge());

    }

    public static void printMore(){
        Student student = Student.getStudent();
        System.out.println("printMore : " + Thread.currentThread().getName() + " , " +local.get());
        System.out.println("printMore : " + Thread.currentThread().getName() + " , name: " +
                student.getName() + " , age : " + student.getAge());
    }
}
class Student{
    private Student(){

    }

    public static Student getStudent(){
        Student student = studentLocal.get();
        if(student == null){
            student = new Student();
            studentLocal.set(student);
        }
        return student;
    }
    private static ThreadLocal<Student> studentLocal = new ThreadLocal<Student>();
    private String name;
    private int age;

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
}