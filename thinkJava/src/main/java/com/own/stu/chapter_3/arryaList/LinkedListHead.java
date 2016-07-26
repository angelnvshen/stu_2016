package com.own.stu.chapter_3.arryaList;

/**
 * Created by CHANEL on 2016/4/30.
 */
public class LinkedListHead<T> {

    private static class Node<T>{
        public Node( T d, Node<T> n){
            data = d; next = n;
        }

        public Node(){
            this(null, null);
        }

        public Node(T d){
            this(d, null);
        }

        public T data;
        public Node<T>   next;
    }

    private Node<T> head;
    private int theSize;

    void init(){
        theSize = 0;
        head = new Node<T>();
        head.next = null;
    }

    public boolean contain(T x){
        Node<T> tem = head.next;
        while (tem != null){
            if(x.equals(tem.data))
                return true;
            else
                tem = tem.next;
        }
        return false;
    }

    public int size(){
        return theSize;
    }

    LinkedListHead(){
        init();
    }

    boolean add(T x){
        if(contain(x))
            return false;
        else {
            Node<T> p = new Node<T>(x);
            p.next = head.next;
            head.next = p;
            theSize ++;
        }
        return true;
    }

    boolean remove(T x){
        if(!contain(x))
            return false;
        else {
            Node<T> p = head.next;
            Node<T> trailer = head;
            while (!p.data.equals(x)) {
                trailer = p;
                p = p.next;
            }
            trailer.next = p.next;
            theSize--;
        }
        return true;
    }

    void print(){
        Node<T> p = head.next;
        while (p != null){
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

}
