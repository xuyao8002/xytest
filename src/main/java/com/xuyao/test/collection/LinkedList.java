package com.xuyao.test.collection;

public class LinkedList<E> {
    private static class Node<E>{
        E e;
        Node<E> prev;
        Node<E> next;
        Node(Node<E> prev, E e, Node<E> next){
            this.e = e;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node<E> first;

    private Node<E> last;

    public void add(E e){
        Node<E> t = last;
        Node<E> n = new Node<>(t,e,null);
        last = n;
        if(t == null){
            first = n;
        }else{
            t.next = n;
        }
        size++;
    }

    public E get(int index){
        if(first == null) return null;
        Node<E> e = first;
        if(index < (size / 2)){
            e = first;
            for(int i = 0; i < index; i++){
                e = e.next;
            }
            return e.e;
        }else{
            e = last;
            for(int i = size - 1; i > index; i--){
                e = e.prev;
            }
            return e.e;
        }
        //return null;
    }
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.get(2));
    }
}
