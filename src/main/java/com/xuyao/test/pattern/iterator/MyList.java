package com.xuyao.test.pattern.iterator;

import java.util.List;

public class MyList<V> implements MyIteratable{
    List<V> list;
    private int index;

    public MyList(List<V> list){
        this.list = list;
    }

    @Override
    public MyIterator<V> iterator(){
        return new Ite();
    }

    class Ite<V> implements MyIterator<V>{

        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        @Override
        public V next() {
            return (V) list.get(index++);
        }
    }

}
