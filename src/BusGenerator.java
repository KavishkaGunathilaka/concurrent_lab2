import java.util.Random;
public class BusGenerator implements Runnable{
    private static double lambda;
    private static Random rand = new Random();

    public BusGenerator(double mean) {
        BusGenerator.lambda = 1 /mean;
    }

    @Override
    public void run() {
        while (true){
            Bus bus = new Bus();
            Thread t = new Thread(bus);
            t.start();
            try {
                Thread.sleep(getDelay());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public long getDelay() {
        return (long) (1000 * Math.log(1-BusGenerator.rand.nextDouble())/(-BusGenerator.lambda));
    }
}
