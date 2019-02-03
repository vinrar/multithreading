package WaitAndNotify;

import java.util.Scanner;

public class Processor {
    public void producer() throws InterruptedException {
        synchronized (this) { //Acquires the lock of this object
            System.out.println("Producer is called");
            wait();//Once the wait method is called, lock is released on the wait method.
            System.out.println("Producer has resumed");
        }
    }

    public void consumer() {
        synchronized (this) {
            System.out.println("Consumer is called");
            System.out.println("Press enter to continue");
            Scanner in = new Scanner(System.in);
            in.nextLine();
            notify();
            System.out.println("Producer is notified..");
        }
    }
}