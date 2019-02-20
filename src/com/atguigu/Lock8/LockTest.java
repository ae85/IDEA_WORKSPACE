package com.atguigu.Lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author gbz
 * @date 2019-02-19 19:57
题目：多线程8锁
1 一般访问，请问先打印短信还是邮件？
2 短信暂停4秒钟，请问先打印短信还是邮件？
3 新增普通开机方法， 请问先打印短信还是开机？
4 有两部手机，请问先打印短信还是邮件？
5 静态同步方法，1部手机，请问先打印短信还是邮件？
6 静态同步方法，2部手机，请问先打印短信还是邮件？
7 一个普通同步方法，一个静态同步方法，1部手机，请问先打印短信还是邮件？
8 一个普通同步方法，一个静态同步方法，2部手机，请问先打印短信还是邮件？



 1和2：一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了
其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
synchronized实现同步的基础：Java中的每一个对象都可以作为锁。
具体表现为以下3种形式。
对于普通同步方法，锁是当前实例对象。
对于静态同步方法，锁是当前类的Class对象。
对于同步方法块，锁是Synchonized括号里配置的对象。


 */
public class LockTest {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(()->{
            phone.sendSMS();
        },"发短信").start();

        try {TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace(); }

        new Thread(()->{
           //phone.sendEmail();
            //phone.openPhone();
            phone1.sendEmail();
        },"发邮件").start();
    }
}

class Phone{
    public static synchronized void sendSMS(){
        try {TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) {e.printStackTrace(); }
        System.out.println("发短信");
    }
    public  synchronized void sendEmail(){
        System.out.println("发邮件");
    }
    public  void openPhone(){
        System.out.println("手机开机");
    }
}
