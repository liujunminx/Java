package com.nowcoder.write_exam.inke;

import com.iot.serial.ICDemo01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class InKeE01 {
    public boolean isValid (String s){
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> hashMap= new HashMap<>();
        hashMap.put(']', '[');
        hashMap.put('}', '{');
        hashMap.put(')', '(');
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            switch (c){
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                case '}':
                case ']':
                    if (stack.pop() != hashMap.get(c) && !stack.isEmpty())
                        return false;
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        InKeE01 inKeE01 = new InKeE01();
        System.out.println(inKeE01.isValid("{[]}"));
    }
}
