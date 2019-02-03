package ThreadSynchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
    private int id;

    public Processor(int id){
        this.id = id;
    }

    public void run() {
        System.out.println("Starting: " + id);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        System.out.println("Completing: " + id);
    }
}

public class ThreadPools {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i = 1; i <= 5; i++){
            executor.submit(new Processor(i));
            Thread.sleep(500);
        }
        executor.shutdown();//Stops taking more tasks and orderly executes the submitted tasks
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
            //Waits until all the threads are executed in specific amount of time.
            //If the threads are not executed or if the threads are interrupted, throws an interrupted exception
            //Returns true if the executor is successfully terminated
            //Returns false if the executor exceeds the time limit
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}