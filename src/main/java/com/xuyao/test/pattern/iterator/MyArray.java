package com.xuyao.test.pattern.iterator;

public class MyArray<V> implements MyIteratable{

    private V[] arr;
    private int index;

    public MyArray(V[] arr){
        this.arr = arr;
    }

    @Override
    public MyIterator<V> iterator() {
        return new Ite();
    }

    class Ite<V> implements MyIterator<V>{

        @Override
        public boolean hasNext() {
            return index < arr.length;
        }

        @Override
        public V next() {
            return (V) arr[index++];
        }

    }
}
