package com.song.record.guava.listenablefuture;

import com.google.common.util.concurrent.*;
import com.sun.istack.internal.Nullable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * 并发
 */
public class ListenableFutureDemo {

    public static void main(String[] args){
        //将ExecutorService装饰成ListeningExecutorService
        ListeningExecutorService service = MoreExecutors.listeningDecorator(
                Executors.newCachedThreadPool()
        );

        //通过异步方法计算返回结果
        ListenableFuture<String> future = service.submit(() -> {
            System.out.println("call execute..");
            return "task success";
        });

        //有两种方法可以执行此Future，并执行Future完成之后的回调函数
        future.addListener(() -> {//该方法会在多线程运算完的时候，指定的Runnable参数传入的对象会被指定的Executor执行
            try {
                System.out.println("result: "+future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }, service);

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("callback result: "+result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        },service);

    }

}
