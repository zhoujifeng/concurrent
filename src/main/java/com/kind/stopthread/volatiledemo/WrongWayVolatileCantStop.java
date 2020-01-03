package com.kind.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 演示用valatile的局限
 * 陷入阻塞时，volatile是无法停止线程的
 * 此例子中
 * 生成者的生产速度快，消费者的消费速度慢
 * 所以阻塞队列满了以后，生产者堵塞
 * Created by Administrator on 2019/10/29.
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多的数据了");

        //一旦消费者不需要更多的数据了，我们应该让生成者也停下来
        producer.canceled = true;
        System.out.println(producer.canceled);


    }
}


class Producer implements Runnable {

    public volatile boolean canceled = false;
    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !canceled) {

                if (num % 100 == 0) {
                    //如果阻塞队列满了，那么当前线程会永远阻塞在当前位置，就算某一时刻
                    //canceled变为了true，当前线程也永远不会看到
                    storage.put(num);
                    System.out.println(num + "是100的倍数，被放入仓库");
                }
                num++;

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者运行结束");
        }

    }
}

class Consumer {

    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        } else {
            return true;
        }
    }


}
