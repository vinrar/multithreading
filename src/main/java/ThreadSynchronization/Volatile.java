package ThreadSynchronization;

import java.util.Scanner;

class Runner extends Thread {
    //Without volatile the variable may get cached into CPU cache.
    //When that happens, each thread may be referring to a different variable in cache.
    //Volatile will keep the variable in the main memory.
    //So whenever a variable changes volatile, it changes it in the main memory
    //Whenever a read happens
    private volatile boolean running = true;
    public void run() {
        while(running) {
            System.out.println("Loo[p]er");
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown(){
        this.running = false;
    }
}

public class Volatile {

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.start();

        Scanner in = new Scanner(System.in);
        in.nextLine();
        runner.shutdown();
    }
}