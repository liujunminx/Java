package com.nowcoder.problem.ordinary.november;

import org.w3c.dom.NameList;

import java.util.ArrayList;
import java.util.List;

public class Test06 {
    private List names = new ArrayList<>();
    public synchronized void add(String name){
        names.add(name);
    }
    public synchronized void printAll(){
        for (int i = 0; i < names.size(); i++){
            System.out.print(names.get(i) + " ");
        }
    }

    public static void main(String[] args) {
        final Test06 test06 = new Test06();
        for (int i = 0; i < 2; i++){
            new Thread(){
                @Override
                public void run() {
                    test06.add("A");
                    test06.add("B");
                    test06.add("C");
                    test06.printAll();
                }
            }.start();
        }
    }
}
