public class Rider implements Runnable{
    private static int nextRiderId = 0;
    private int riderId;

    public Rider() {
        this.riderId = Rider.nextRiderId;
        Rider.nextRiderId++;
    }


    @Override
    public void run() {
        try {
            Main.mutex.acquire();
            System.out.println("Rider " + this.riderId + " arrived");
            Main.waiting++;
            Main.mutex.release();
            Main.sem_bus.acquire();
            this.board();
            Main.sem_boarded.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void board() {
        System.out.println("Rider " + this.riderId + " boarded");
    }
}
