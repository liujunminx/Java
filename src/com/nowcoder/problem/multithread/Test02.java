package com.nowcoder.problem.multithread;

/**
 * 输出pong ping
 */
public class Test02 {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                pong();
            }
        };
        t.run();// 易错，调用run只是调用普通方法，调用start才是启动线程
        System.out.println("ping");
    }

    static void pong(){
        System.out.println("pong");
    }
}
