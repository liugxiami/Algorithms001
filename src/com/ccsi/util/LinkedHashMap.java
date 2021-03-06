package com.ccsi.util;

/**
 * Created by gxliu on 2016/12/18.
 */
public class LinkedHashMap {
    public class Entry{
        int key;
        Object value;
        Entry next;
        Entry pre;

        public Entry(int key, Object value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.pre = null;
        }
    }
    private Entry[] entries;
    private int capacity=37;
    private int count;

    public LinkedHashMap() {
        this.entries = new Entry[capacity];
        this.count = 0;
    }

    public int hashCode(int key){
        return (key*37+key%100/10)%capacity;
    }

    public void put(int key, Object value){
        int position=hashCode(key);
        if(entries[position]==null){
            entries[position]=new Entry(key,value);
            count++;
        }else{
            Entry curr=entries[position];
            while(curr.next!=null){
                if(curr.key==key){
                    curr.value=value;
                    return;
                }
                curr=curr.next;
            }
            curr.next=new Entry(key,value);
            curr.next.pre=curr;
            count++;
        }

    }
    public Entry positionOf(int key){
        int postion=hashCode(key);
        if(entries[postion]==null)return null;
        Entry curr=entries[postion];
        while(curr!=null){
            if(curr.key==key)return curr;
            curr=curr.next;
        }
        return curr;
    }
    public void remove(int key){
        Entry curr=positionOf(key);
        if(curr==null)return;

        if(curr.pre==null){
            curr.next.pre=null;
        }else{
            curr.pre.next=curr.next;
        }

        if(curr.next==null){
            curr.pre.next=null;
        }else{
            curr.next.pre=curr.pre;
        }

        count--;

    }
    public boolean contains(int key){
        return positionOf(key)!=null;
    }
    public int size(){
        return this.count;
    }

    public static class Student{
        int num;
        String name;

        public Student(int num, String name) {
            this.num = num;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Student s1=new Student(10,"Sean");
        Student s2=new Student(21,"Hannah");
        Student s3=new Student(32,"Ella");
        Student s4=new Student(43,"Huiying");

        HashMap map=new HashMap();
        map.put(s1.num,s1);
        map.put(s2.num,s2);
        map.put(s3.num,s3);
        map.put(s4.num,s4);

        System.out.println(map.size());
        map.remove(s1.num);
        System.out.println(map.contains(s2.num));
        System.out.println(map.size());

    }
}
