package com.bssrdf;

import java.util.concurrent.*;
class PrintInOrder{
    
    Semaphore run2, run3;
    
    
    public PrintInOrder() {
        run2 = new Semaphore(0);
        run3 = new Semaphore(0);
    }
    
    Runnable printFirst = new Runnable() {
        public void run() {
            System.out.print("first");
            run2.release();
        }
    };
    
    Runnable printSecond = new Runnable() {
        public void run() {
            try {
                run2.acquire();
            } catch (InterruptedException e) {
                System.out.println("Error");
            }
            System.out.print("second");
            run3.release();
        }
    };
    
    Runnable printThird = new Runnable() {
        public void run() {
            try {
                run3.acquire();
            } catch (InterruptedException e) {
                System.out.println("Error");
            }
            System.out.print("third");
        }
    };
    
    public static void main(String[] args) {
        PrintInOrder bar = new PrintInOrder();
        
        Thread one = new Thread(bar.printFirst);
        Thread two = new Thread(bar.printSecond);
        Thread three = new Thread(bar.printThird);
        
        one.start();
        two.start();
        three.start();

    }
}
