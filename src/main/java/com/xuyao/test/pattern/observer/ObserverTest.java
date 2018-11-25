package com.xuyao.test.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverTest {

    class Watcher implements Observer{
        @Override
        public void update(Observable o, Object arg) {
            Watched watched = (Watched) o;
            System.out.println(watched.getName() + " has do，Watcher received");
        }
    }

    class Watcher1 implements Observer{
        @Override
        public void update(Observable o, Object arg) {
            Watched watched = (Watched) o;
            System.out.println(watched.getName() + " has do，Watcher1 received");
        }
    }

    class Watched extends  Observable{
        private String name;

        public Watched(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void doSomething(boolean flag){
            if(flag){
                setChanged();
                notifyObservers();
            }
        }
    }

    public static void main(String[] args) {
        ObserverTest test = new ObserverTest();
        Watcher er = test.new Watcher();
        Watcher1 er1 = test.new Watcher1();
        Watched ed = test.new Watched("w1");
        ed.addObserver(er);
        ed.addObserver(er1);
        ed.doSomething(true);

        Watched ed1 = test.new Watched("w2");
        ed1.addObserver(er);
        ed1.addObserver(er1);
        ed1.doSomething(false);

    }
}
