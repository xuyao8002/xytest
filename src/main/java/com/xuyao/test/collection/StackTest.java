package com.xuyao.test.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        String expression = "12 3 4 + *";
        String[] items = expression.split("\\s");
        String numPattern = "\\d+";
        Set<String> opers = opers();
        Stack<Integer> nums = new Stack<>();
        for (String item : items) {
            if(opers.contains(item)){
                Integer pop = nums.pop();
                Integer pop1 = nums.pop();
                if(ADD.equals(item)){
                    nums.push(pop + pop1);
                }else if(SUBTRACT.equals(item)){
                    nums.push(pop - pop1);
                }else if(MULTIPLY.equals(item)){
                    nums.push(pop * pop1);
                }else if(DIVIDE.equals(item)){
                    nums.push(pop / pop1);
                }
            } else if (item.matches(numPattern)) {
                nums.push(Integer.valueOf(item));
            }else{
                System.out.println("没用的");
            }
        }
        System.out.println(nums.pop());
    }
    static final String ADD = "+";
    static final String SUBTRACT = "-";
    static final String MULTIPLY = "*";
    static final String DIVIDE = "/";
    public static Set<String> opers(){
        Set<String> opers = new HashSet<>();
        opers.add(ADD);
        opers.add(SUBTRACT);
        opers.add(MULTIPLY);
        opers.add(DIVIDE);
        return opers;
    }
}
