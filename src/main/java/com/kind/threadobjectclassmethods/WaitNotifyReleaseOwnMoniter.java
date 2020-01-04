package com.kind.threadobjectclassmethods;

/**
 * 描述：证明wait只释放当前的那把锁
 */
public class WaitNotifyReleaseOwnMoniter {

    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    System.out.println("线程"+Thread.currentThread().getName()+"已经获得A锁");
                    synchronized (resourceB){
                        System.out.println("线程"+Thread.currentThread().getName()+"已经获得B锁");

                        try {
                            resourceA.wait();
                            System.out.println("线程"+Thread.currentThread().getName()+"已经释放了A锁");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    System.out.println("线程"+Thread.currentThread().getName()+"已经获得A锁");
                    //这里加了notify会引起死锁
                    //resourceA.notifyAll();
                    synchronized (resourceB){
                        System.out.println("线程"+Thread.currentThread().getName()+"已经获得B锁");
                        }
                    }
                }
        }).start();
    }
}
