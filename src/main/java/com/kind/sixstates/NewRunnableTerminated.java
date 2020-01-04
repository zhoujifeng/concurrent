package com.kind.sixstates;

/**
 * 描述： 展示线程的NEW,RUNNABLE,TERMINATED状态，
 * 即使是正在运行中的，也是Runnale状态，而不是Running
 */
public class NewRunnableTerminated implements Runnable {

    public static void main(String[] args) {

        Thread thread = new Thread(new NewRunnableTerminated());
        //打印出new的状态
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出Runnable的状态，即使是正在运行
        System.out.println(thread.getState());

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.getState());
        System.out.println(thread.getState());
        System.out.println(thread.getState());
        System.out.println(thread.getState());
    }

    public void run() {

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }

    }
}
