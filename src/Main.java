import java.util.concurrent.Semaphore;
public class Main {
    public static Semaphore mutex = new Semaphore(1);
    public static Semaphore sem_bus = new Semaphore(0);
    public static Semaphore sem_boarded = new Semaphore(0);
    public static int waiting = 0;

    public static void main(String[] args) {
        BusGenerator busGen = new BusGenerator(20, 10);
        RiderGenerator riderGen = new RiderGenerator(1);

        Thread t1 = new Thread(riderGen);
        Thread t2 = new Thread(busGen);

        t1.start();
        t2.start();
    }
}