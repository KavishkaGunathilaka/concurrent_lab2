public class Bus implements Runnable{
    private static int nextBusId = 0;
    private int busId;
    private int capacity;

    public Bus(int capacity) {
        this.busId = nextBusId;
        Bus.nextBusId++;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        try {
            Main.mutex.acquire();
            System.out.println("Bus " + this.busId + " arrived");
            int n = Math.min(Main.waiting, this.capacity);
            for (int i = 0; i < n; i++) {
                Main.sem_bus.release();
                Main.sem_boarded.acquire();
            }
            Main.waiting = Math.max( Main.waiting-50, 0);
            Main.mutex.release();
            this.depart();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void depart() {
        System.out.println("Bus " + busId +" departed");
    }
}
