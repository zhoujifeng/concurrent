package com.kind.threadobjectclassmethods;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ProducerConsumermodel {

    public static void main(String[] args) {

        Storage storage = new Storage();
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);

        thread1.start();
        thread2.start();




    }


}

class Producer implements Runnable {

    private Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;

    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put();
        }

    }
}

class Consumer implements Runnable {

    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }

    }
}

class Storage {

    private int capecity = 10;
    private LinkedList<Date> storage = new LinkedList();

    public synchronized void put() {
        while (storage.size() == capecity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        storage.add(new Date());
        System.out.println("仓库里有了" + storage.size() + "个产品");
        notify();

    }

    public synchronized void take() {
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("拿到了" + storage.poll() + ",现在仓库里还有" + storage.size() + "个产品");
        notify();
    }
}