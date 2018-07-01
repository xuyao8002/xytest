package com.xuyao.test;


    public class SayServiceImpl implements ISayService{

        @Override
        public void say(String string) {
            System.out.println("I say: " + string);
        }
    }
