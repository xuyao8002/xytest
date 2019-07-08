package com.xuyao.test.collection;

import java.util.ArrayList;
import java.util.List;

class MinStack {

    private List<Integer> stack;
    private List<Integer> minStack;

    public MinStack() {
        stack = new ArrayList<>();
        minStack = new ArrayList<>();
    }
    
    public void push(int x) {
        stack.add(x);
        if(minStack.size() == 0){
            minStack.add(x);
        }else{
            Integer min = minStack.get(minStack.size() - 1);
            min = Math.min(min, x);
            minStack.add(min);
        }
    }
    
    public void pop() {
        int last = stack.size() - 1;
        stack.remove(last);
        Integer tmpMin = minStack.get(last);
        minStack.remove(last);
    }
    
    public int top() {
        return stack.get(stack.size() - 1);
    }
    
    public int getMin() {
        return minStack.get(minStack.size() - 1);
    }

}