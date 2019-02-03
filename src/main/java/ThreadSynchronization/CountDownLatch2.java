package ThreadSynchronization;

import java.util.concurrent.*;

/*
    Create a program which executes n times when the CountDownLatch value is n.
    ^^ It tried to solve the above problem with CountDownLatch, but I realized the entire purpose of CountdownLatch is something different.
    Let's say your worker is calling and API(which is important and need to be in sync) and updating DB(which can be async)
    You can countdown once the API call is made. And await() once the latch goes to 0.
    After that the main thread continues it's execution. And all the DB updates will happen in async(best example ever :P).

    For using CountDownLatch, you always have to know prior how many counts should you be keeping.
*/
public class CountDownLatch2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(20);
        for(int i = 0; i < 100; i ++){
            executor.submit(new Processor(latch));
        }
        executor.shutdown();
        latch.await();//This part of the code will await for the further instruction
        executor.shutdownNow();//This codes stops all the tasks in the executor.
        // But do I have control over it? Will it stop as soon as the latch.await() returns?

        System.out.println("End of Main thread");
    }

    public  static class Processor implements Runnable{
        CountDownLatch latch;
        public Processor(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run(){
            System.out.println("Running thread: " + Thread.currentThread().getName() + " latch value: " + latch.getCount());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            System.out.println("Executed thread: " + Thread.currentThread().getName() + " latch value: " + latch.getCount());
        }
    }
}