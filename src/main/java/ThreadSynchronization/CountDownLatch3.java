package ThreadSynchronization;

import java.util.concurrent.CountDownLatch;

/*
I know you're in search of more concrete example of CountDownLatch  and here comes the ultimatum
CountDownLatches are like door latches. They can hold your program unitl the latch is on, once the latch is off
you don't have control anymore on the latch.
 */
public class CountDownLatch3 {
    public static void main(String[] args) {
        int nThreads = 3;

        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(nThreads);

        for(int i = 0; i < nThreads; i++) {
            Thread t1 = new Thread(() -> {
                try {
                    startLatch.await();
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Interrupted exceptions are usually thrown");
                } finally {
                    endLatch.countDown();
                }
            }
            );

            t1.start();
        }

        int startTime = (int) System.currentTimeMillis();
        //Let's say you want to analyze the performance of the slgorithm that you want to run in each thread
        //And you want all the threads to run at once to benchmark the value
        //We will hold on the latch till the expected number of threads are initialized.
        startLatch.countDown();//unleashes all the threads that are awaiting
        try {
            endLatch.await();//Now we will wait unitl all the threads are done. By using this latch.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int endTime = (int) System.currentTimeMillis();

        System.out.println("Total time take: " + (endTime - startTime));
    }
}