package com.xuyao.test.pattern.listener;


import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

public class ListenerTest {

    class MyEventSource{
        List<MyListener> ls = new ArrayList<>();
        public MyEventSource(){

        }
        public void addLs(MyListener myListener){
            ls.add(myListener);
        }

        public void exec(){
            for (MyListener l : ls) {
                l.doSomething(new MyEvent(this));
            }
        }
    }

    class MyEvent extends EventObject{
        public  MyEvent(Object source){
            super(source);
        }
        public void does(String name){
            System.out.println(name + "has do");
        }
    }


    interface MyListener extends EventListener{
        public void doSomething(MyEvent myEvent);
    }

    public static void main(String[] args) {
        ListenerTest test = new ListenerTest();
        MyEventSource source = test.new MyEventSource();
        source.addLs(new MyListener() {
            String name = "xy";
            @Override
            public void doSomething(MyEvent myEvent) {
                myEvent.does(name);
            }
        });
        source.addLs(new MyListener() {
            String name = "xuye";
            @Override
            public void doSomething(MyEvent myEvent) {
                myEvent.does(name);
            }
        });
        source.exec();
    }
}
