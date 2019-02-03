package ThreadSynchronization;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
To demonstrate the producer consumer model using Blocking Queue.
*/
public class ProducerConsumer {
    //Array Blocking Queue of size 10
    public static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
    public static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    public static void producer() throws InterruptedException {
        while(true) {
            int value = random.nextInt(100);
            blockingQueue.put(value);
        }
    }

    public static void consumer() throws InterruptedException {
        while(true) {
            Thread.sleep(100);
            if(random.nextInt(10) == 0){
                System.out.println("Taking out: " + blockingQueue.take() + "; Q size: " + blockingQueue.size());
            }
        }
    }
}