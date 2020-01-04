package com.kind.threadobjectclassmethods;

import java.io.ObjectOutputStream;

/**
 * 展示wait和notify的基本用法
 * 1.研究代码的执行顺序
 * 2.证明wait释放🔒
 */
public class Wait {

    public static Object object = new Object();

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println("线程"+Thread.currentThread().getName()+"执行了");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object){
                System.out.println("线程"+Thread.currentThread().getName()+"调用了notify()");
                object.notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }
}
