package com.kind.createthread;

/**
 * Created by Administrator on 2019/10/18.
 */
public class RunnableStyle implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
    @Override
    public void run() {
        System.out.print("用Runnable方法实现线程");
    }
}
