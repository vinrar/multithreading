package ThreadSynchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Locks {

    private Random random = new Random();

    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    Object lock1 = new Object();
    Object lock2 = new Object();

    //Using method level synchronization obtains the object's in-built lock
    //Using this locks holds the other thread down
    //public synchronized void stepOne() {
    public void stepOne() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(random.nextInt());
        }
    }

    //public synchronized void stepTwo() {
    public void stepTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt());
        }
    }

    public static void main(String[] args) {
        Locks locks = new Locks();
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                locks.stepOne();
                locks.stepTwo();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 1000; i++) {
                locks.stepOne();
                locks.stepTwo();
            }
        });

        long startTime = System.currentTimeMillis();

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime));
        System.out.println("List1: " + locks.list1.size() + " List2: " + locks.list2.size());
    }
}