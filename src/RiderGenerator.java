import java.util.Random;
public class RiderGenerator implements Runnable{
    private static double lambda;
    private static Random rand = new Random();

    public RiderGenerator(double mean) {
        RiderGenerator.lambda = 1 /mean;
    }

    @Override
    public void run() {
        while (true){
            Rider rider = new Rider();
            Thread t = new Thread(rider);
            t.start();
            try {
                Thread.sleep(getDelay());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public long getDelay() {
        return (long) (1000 * Math.log(1-RiderGenerator.rand.nextDouble())/(-RiderGenerator.lambda));
    }
}