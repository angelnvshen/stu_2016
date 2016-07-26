package com.own.stu.chapter_3.arryaList;

import org.omg.CORBA.Any;

import java.util.*;

/**
 * Created by CHANEL on 2016/4/20.
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;

    private AnyType [] theItems;

    public MyArrayList(){
        clear();
    }

    public void clear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void trimToSize(){
        ensureCapacity(size());
    }

    public AnyType get(int idx){
        if(idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();

        return theItems[idx];
    }

    public AnyType set(int idx, AnyType newVal){
        if(idx <0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType old = theItems[idx];
        theItems[ idx ] = newVal;
        return old;
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < theSize)
            return;

        AnyType [] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for(int i=0;i<size();i++)
            theItems[i]=old[i];
    }

    public boolean add(AnyType x){

        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x){
        if(theItems.length == size())
            ensureCapacity(size()*2 + 1);
        for(int i=theSize;i>idx;i--)
            theItems[i] = theItems[i-1];
        theItems[idx] = x;
        theSize++;
    }

    public AnyType remove(int idx){
        AnyType removeItem = theItems[idx];
        for(int i=idx;i<size()-1;i++)
            theItems[i]=theItems[i+1];
        theSize--;
        return removeItem;
    }

    public Iterator<AnyType> iterator() {
        return new ArrayListIterator(this);
    }

    private class ArrayListIterator<AnyType> implements Iterator<AnyType>{
        private int current = 0;

        private MyArrayList<AnyType> theList;

        public ArrayListIterator(MyArrayList<AnyType> list){
            theList = list;
        }

        public boolean hasNext() {
            return current < theList.size();
        }

        public AnyType next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return theList.theItems[current++];
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    /*private class ArrayListIterator<AnyType> implements Iterator<AnyType>{
        private int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public AnyType next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return theItems[current++]; // error found here
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }*/

    public static void main(String[] args) {

        /*MyArrayList<String> list = new MyArrayList<String>();
        for(int i=0;i<10;i++)
            list.add(i+"");

        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next());*/

        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        List<Integer> lst = new ArrayList<Integer>();
        lst.add(1);
        lst.add(3);
        lst.add(4);
        lst.add(6);

        printLots(list, lst);

    }

    public static <T> void printLots(List<T> L, List<Integer> P){
        Iterator<T> tl = L.iterator();
        Iterator<Integer> tp = P.iterator();
        int i = 0;
        T itemL = null;
        Integer itemP = 0;
        while(tl.hasNext() && tp.hasNext()){
            itemP = tp.next();
            while(i < itemP && tl.hasNext()){
                itemL = tl.next();
                i++;
            }
            System.out.println(itemL);
        }
    }

    private static <T> void changeItemSingleList(Node beforep){
        Node p, afterp;
        p = beforep.next;
        afterp = p.next;
        p.next = afterp.next;
        beforep.next = afterp;
        afterp.next = p;
    }

    private static <T> void changeItemDoubleList(Node p){
        Node beforep, afterp;
        beforep = p.prev;
        afterp = p.next;

        p.next = afterp.next;
        p.prev = afterp;

        p.next.prev = p;

        afterp.next = p;
        afterp.prev = beforep;

        beforep.next = afterp;
    }

    private static class Node<T>{
        public Node( T d, Node<T> p, Node<T> n ){
            data = d; prev = p; next = n;
        }

        public T data;
        public Node<T>   prev;
        public Node<T>   next;
    }

    public static <T extends Comparable<? super T>> void intersection(List<T> L1, List<T> L2, List<T> intersect){
        Iterator<T> tl1 = L1.iterator();
        Iterator<T> tl2 = L2.iterator();

        T itemL1 = null, itemL2 = null;

        if(tl1.hasNext() && tl2.hasNext()) {
            itemL1 = tl1.next();
            itemL2 = tl2.next();
        }

        while(itemL1 != null && itemL2 != null){
            int compareResult = itemL1.compareTo(itemL2);
            if(compareResult == 0) {
                intersect.add(itemL1);
                itemL1 = tl1.hasNext() ? tl1.next() : null;
                itemL2 = tl2.hasNext() ? tl2.next() : null;
            }else if(compareResult > 0 ) {
                itemL2 = tl2.hasNext() ? tl2.next() : null;
            }
            else {
                itemL1 = tl1.hasNext() ? tl1.next() : null;
            }
        }
    }

    public static <T extends Comparable<? super T>> void union(List<T> L1, List<T> L2, List<T> union){
        Iterator<T> tl1 = L1.iterator();
        Iterator<T> tl2 = L2.iterator();

        T itemL1 = null, itemL2 = null;

        if(tl1.hasNext() && tl2.hasNext()) {
            itemL1 = tl1.next();
            itemL2 = tl2.next();
        }

        while(itemL1 != null && itemL2 != null){
            int compareResult = itemL1.compareTo(itemL2);
            if(compareResult == 0) {
                union.add(itemL1);
                itemL1 = tl1.hasNext() ? tl1.next() : null;
                itemL2 = tl2.hasNext() ? tl2.next() : null;
            }else if(compareResult > 0 ) {
                union.add(itemL2);
                itemL2 = tl2.hasNext() ? tl2.next() : null;
            }
            else {
                union.add(itemL1);
                itemL1 = tl1.hasNext() ? tl1.next() : null;
            }
        }
    }

    public static void pass(int m, int n){
        ArrayList<Integer> lst = new ArrayList<Integer>();

        for(int i=0;i<n;i++) {
            lst.add(i);

            lst.trimToSize();
        }
        lst.addAll(null);

    }

    public void addAll(Iterable<? extends AnyType> items){

    }
}












