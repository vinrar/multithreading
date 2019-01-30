package DifferentWaysToStartAThread;

public class InterfaceInjection {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    System.out.println("HiLo: " + i + " ThreadId: " + Thread.currentThread().getId());
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        t1.start();
    }
}
