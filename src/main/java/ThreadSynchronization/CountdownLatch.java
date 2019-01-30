package ThreadSynchronization;

import java.util.List;
import java.util.concurrent.*;

class Processor1 implements Callable {
    CountDownLatch latch;

    public Processor1(CountDownLatch latch) {
        this.latch = latch;
    }

    public Integer call(){
        System.out.println(" starting Thread Nme : "+Thread.currentThread().getName() + "Latch count: " + latch.getCount());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread "+Thread.currentThread().getName() + " counting down");
        latch.countDown();
        System.out.println("Processor ended.");

        return 1;
    }
}

public class CountdownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Future<Integer> future = null;
        ExecutorService executors = Executors.newFixedThreadPool(2);
        for(int i = 1; i <= 10; i++) {
            future = executors.submit(new Processor1(latch));
//            try {
//                //Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        latch.await();
        executors.shutdown();
        try {
            future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread");
    }
}