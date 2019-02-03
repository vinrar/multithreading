package ThreadSynchronization;

public class Synchronized {
    private volatile int count1 = 0;
    private int count2 = 0;

    public synchronized void increment(){
        count2 += 1;
    }

    public static void main(String args[]) {
        Synchronized sync = new Synchronized();
        sync.doWork();
    }

    public void doWork() {

        //Thread t1 and t2 demostrate the short coming of volatile keyword
        Thread t1 = new Thread(new Runnable() {
            public void run(){
                for(int i = 0; i < 10000; i ++){
                    count1++;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run(){
                for(int i = 0; i < 10000; i ++){
                    count1 ++;
                }
            }
        });

        t1.start();
        t2.start();

        //OP is expecting the end output of the function is 20k, but it can sometimes come less than 20k
        //What is happening here is though we have count1 as volatile, when we are trying to perform operations like += 1
        //These operations are not atomic and hence at one point of time both threads let's say copied a value of 100
        //Thread 1 when doing count += 1, copies the value of 100(since values are passed by value for primitive types)
        //adds one to the value. Let's say the value is 101. And the operation count = 101 updates the value of count.
        //Now thread 2 might also be at the same stage and would update the value to 101. Because it copied the initial 100
        //and not the updated 101. This value could have been missed during copying the primitives to local memory.
        //So, both the threads updated the value to 101. And hence the bug.
        System.out.println("This value is 0 as threads are async: " + count1);//This returns 0. As this gets executed in the main thread.
        //Since the sout is in the main thread it gets executed if the threads are not joined.
        //We need to join the threads.
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Volatile. Count1: " + count1);

        Thread t3 = new Thread(new Runnable() {
            public void run(){
                for(int i = 0; i < 10000; i ++){
                    increment();
                }
            }
        });

        Thread t4 = new Thread(new Runnable() {
            public void run(){
                for(int i = 0; i < 10000; i ++){
                    increment();
                }
            }
        });

        t3.start();
        t4.start();

        try {
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //This prints count2 value.
        System.out.println("Synchronized. Count2: " + count2);
    }
}