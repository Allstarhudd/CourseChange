
public class TestThreads {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 20; i += 2) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Odd-1-20");

        Thread t2 = new Thread(() -> {
            for (int i = 20; i <= 40; i += 2) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(120);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Even-20-40");

        Thread t3 = new Thread(() -> {
            for (char c = 'a'; c < 'a' + 10; c++) {
                System.out.println(Thread.currentThread().getName() + ": " + c);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Letters-a-j");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("All threads finished.");
    }
}
