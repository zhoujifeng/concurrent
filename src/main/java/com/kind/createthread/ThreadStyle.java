package com.kind.createthread;

/**
 * 用thread方式实现线程
 * Created by Administrator on 2019/10/18.
 */
public class ThreadStyle extends Thread{

    public static void main(String[] args) {

        Thread thread = new ThreadStyle();
        thread.start();
    }

    @Override
    public void run(){
        System.out.print("用Thread类实现线程");
    }

}
