package com.atguigu.juc_threadwaitnotfyTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gbz
 * @date 2019-02-19 18:42
 *  题目：现在两个线程，可以操作初始值为零的一个变量，实现一个线程对该变量加1，一个线程对该变量减1，
    实现交替，来10轮，变量初始值为零。

    1 高内聚低耦合前提下，线程   操作      资源类
    2 判断+干活+通知
    3 避免虚假唤醒，线程判断用while
 */
public class ThreadWaitNotifDemo {
    public static void main(String[] args) {
        ShareSource shareSource = new ShareSource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareSource.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareSource.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareSource.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareSource.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}

class ShareSource{
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
              lock.lock();
              try{
                  while(num != 0){
                      condition.await();
                  }
                  num++;
                  System.out.println(Thread.currentThread().getName()+"\t"+num);
                  //唤醒线程
                  condition.signalAll();
              }finally {
                  lock.unlock();
              }
          }
    public void decrement() throws InterruptedException {
        lock.lock();
        try{
            while(num == 0){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            //唤醒线程
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    /*public synchronized void increment() throws InterruptedException {
        //切记不能使用if,避免虚假唤醒
        //wait notif Object类中的方法
        while (num != 0){
            //等待
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        //唤醒线程
        this.notifyAll();
    }

    public synchronized void decrement()throws InterruptedException{

        while (num == 0){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"\t"+num);


        this.notifyAll();
    }*/
}
