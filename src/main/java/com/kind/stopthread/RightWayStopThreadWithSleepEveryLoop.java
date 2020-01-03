package com.kind.stopthread;

/**
 * 如果在执行中，每次循环都会调用sleep或者wait等方法，
 * 那么不需要每次迭代都检查是否需要中断
 * Created by Administrator on 2019/10/21.
 */
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try{
                while (num <= 10000 ) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.sleep(5000);
        thread.interrupt();
    }
}
