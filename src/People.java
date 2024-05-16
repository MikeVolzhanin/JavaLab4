import java.util.Queue;
import java.util.Random;

public class People implements Runnable{
    private final Queue<Call> calls;
    private final int countLevels;
    private final long period;
    public People(Queue<Call> requests, int countLevels, long period){
        this.calls = requests;
        this.countLevels = countLevels;
        this.period = period;
    }
    @Override
    public void run(){
        Random random = new Random();
        boolean fuel = false;
        while(!fuel){
            int level = random.nextInt(countLevels);
            Direction direction = random.nextBoolean()? Direction.DOWN : Direction.UP;
            Call call = new Call(direction, level);
            calls.add(call);
            System.out.printf("The person will go %s at the %d level\n", direction, level);
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}