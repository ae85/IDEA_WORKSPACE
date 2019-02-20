package com.atguigu.juc_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author gbz
 * @date 2019-02-19 20:47
 */
public class CallableDemo {
    public static void main(String[] args) {

        FutureTask<String> futureTask = new FutureTask(()->{
            return "456";
        });
        Thread a = new Thread(futureTask, "A");
        a.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
        }
    }
}

class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "123";
    }
}
