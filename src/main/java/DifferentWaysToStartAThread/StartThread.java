package DifferentWaysToStartAThread;

class Runner extends Thread {
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("HiLo: " + i);
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class StartThread {
    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.start();

        Runner runner2 = new Runner();
        runner2.start();
    }
}