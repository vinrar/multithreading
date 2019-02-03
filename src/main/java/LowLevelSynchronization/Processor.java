package LowLevelSynchronization;

import java.util.LinkedList;
import java.util.Random;

public class Processor {

    Object lock = new Object();
    static int MAX_SIZE = 10;
    LinkedList<Integer> list = new LinkedList<Integer>();
    Random random = new Random();

    public void producer() throws InterruptedException {
        while(true) {
            synchronized (lock) {
                if(list.size() == MAX_SIZE)
                    lock.wait();

                list.add(random.nextInt(100));
                lock.notify();
            }
        }
    }

    public void consumer() throws InterruptedException {
        while(true) {
            synchronized (lock) {
                if(list.size() == 0){
                    lock.wait();
                }

                lock.notify();
                int value = list.removeFirst();
                System.out.println("Removing value: " + value + "; List size: " + list.size());
            }
            Thread.sleep(random.nextInt(1000));
        }
    }
}