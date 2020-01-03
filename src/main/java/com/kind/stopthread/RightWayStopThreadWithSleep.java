package com.kind.stopthread;

/**
 * 带有sleep的中断线程的写法
 * 演示当有阻塞的时候，刚好被中断
 * 当线程被阻塞时，收到中断，则会响应中断，并抛出被中断的异常
 * Created by Administrator on 2019/10/21.
 */
public class RightWayStopThreadWithSleep{

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try{
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.sleep(500);
        thread.interrupt();
    }
}
