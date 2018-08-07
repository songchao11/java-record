package com.song.record.actual;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印奇数偶数(1 - 100)
 */
public class PrintJiOuNum {

    private int start = 1;

    /*
     * 保证内存可见性
     */
    private boolean flag = false;

    /*
     * 锁
     */
    private final static Lock LOCK = new ReentrantLock();

    public static void main(String[] args){
        PrintJiOuNum number = new PrintJiOuNum();
        new Thread(new JiNum(number), "奇数线程").start();
        new Thread(new OuNum(number), "偶数线程").start();
    }

    /*
     * 奇数线程
     */
    private static class JiNum implements Runnable{

        private PrintJiOuNum number;

        public JiNum(PrintJiOuNum number){
            this.number = number;
        }

        @Override
        public void run() {
            while(number.start <= 100){
                if(!number.flag){
                    try{
                        LOCK.lock();
                        System.out.println(Thread.currentThread().getName()+": " + number.start++);
                        number.flag = true;
                    }finally {
                        LOCK.unlock();
                    }
                }else{
                    try {
                        //防止线程空转
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class OuNum implements Runnable{

        private PrintJiOuNum number;

        public OuNum(PrintJiOuNum number){
            this.number = number;
        }

        @Override
        public void run() {
            while(number.start <= 100){
                if(number.flag){
                    try{
                        LOCK.lock();
                        System.out.println(Thread.currentThread().getName()+": " + number.start++);
                        number.flag = false;
                    }finally{
                        LOCK.unlock();
                    }
                }else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
