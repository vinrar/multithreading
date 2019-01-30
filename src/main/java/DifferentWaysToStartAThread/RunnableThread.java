package DifferentWaysToStartAThread;

class Runner2 implements Runnable{
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("HiLo: " + i + " ThreadId: " + Thread.currentThread().getId());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class RunnableThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner2());
        t1.start();

        Thread t2 = new Thread(new Runner2());
        t2.start();
    }
}