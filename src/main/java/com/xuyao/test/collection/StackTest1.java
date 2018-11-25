package com.xuyao.test.collection;

import java.util.Stack;


public class StackTest1 {
    public static void main(String[] args) {
        String expression = "((())";
        char[] chars = expression.toCharArray();
        Stack<Character> stacks = new Stack<>();
        boolean flag = true;
        for (char aChar : chars) {
            //System.out.println(aChar);
            if(aChar == '('){
                stacks.push(aChar);
            }else if(aChar == ')'){
                if(stacks.isEmpty()){
                    flag = false;
                    break;
                }
                Character pop = stacks.pop();
                if(pop != '('){
                    flag = false;
                    break;
                }
            }
        }
        if(flag && !stacks.isEmpty()) flag = false;
        System.out.println(flag ? "括号平衡" : "括号不平衡");
    }
}
