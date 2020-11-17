package com.nowcoder.problem.multithread;

public class Test01 {
    public static void waitForString(){
        Object obj = new Object();
        synchronized (Thread.currentThread()){
            try {
                obj.wait();
                System.out.println("wait...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.notify();
        }
    }

    public static void main(String[] args) {
        waitForString();
    }
}
