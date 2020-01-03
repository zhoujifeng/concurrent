package com.kind.stopthread.volatiledemo;

/**
 * 演示用volatile的局限，看似可行
 * Created by Administrator on 2019/10/29.
 */
public class WrongWayVolatile implements Runnable{

    private volatile boolean canceled = false;


    @Override
    public void run() {
        int num = 0;
        while(num <= 10000 && !canceled){
            if(num % 100 == 0){
                System.out.println(num + "是100的倍数");
            }
            num++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();
        thread.sleep(5000);
        wrongWayVolatile.canceled = true;
    }
}
