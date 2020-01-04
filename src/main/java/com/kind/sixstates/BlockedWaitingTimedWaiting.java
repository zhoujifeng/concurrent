package com.kind.sixstates;

/**
 * 描述： 展示 Blocked,waiting,timedwaiting三种状态
 */
public class BlockedWaitingTimedWaiting implements Runnable{

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting blockedWaitingTimedWaiting = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(blockedWaitingTimedWaiting);
        Thread thread2 = new Thread(blockedWaitingTimedWaiting);
        thread1.start();
        thread2.start();
        //打印出Timed_waiting状态，因为正在运行Thread.sleep(1000)
        System.out.println(thread1.getState());
        //打印出Blocked状态，因为thread2想要获取被synchronized锁，但没有获取到
        System.out.println(thread2.getState());
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出waiting
        System.out.println(thread1.getState());


    }
    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
