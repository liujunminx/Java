package com.nowcoder.problem.multithread;

public class Test03 extends Thread{
    @Override
    public void run() {
        System.out.println("In run");
        yield();// 线程礼让，由运行状态变为可运行状态
        System.out.println("Leaving run");
    }

    public static void main(String[] args) {
        new Test03().start();
    }
}
