import java.util.Queue;

public class TestLift implements Runnable{
    private final Lift firstLift;
    private final Lift secondLift;
    private final Queue<Call> calls;
    public TestLift(int countLevels, Queue<Call> calls){
        firstLift = new Lift("first", Direction.BREAK, 0, -1, countLevels, calls);
        secondLift = new Lift("second", Direction.BREAK, 0, -1, countLevels, calls);
        this.calls = calls;
    }
    @Override
    public void run(){
        Thread firstLiftThread = new Thread(firstLift);
        Thread secondLiftThread = new Thread(secondLift);
        boolean fuel = false;
        try{
            while(!fuel) {
                if (calls.isEmpty()) {
                    Thread.sleep(500);
                } else if (firstLift.getStatus() == Direction.BREAK || secondLift.getStatus() == Direction.BREAK) {
                    Thread.sleep(1500);
                    Call call = calls.poll();
                    if (call != null) {
                        if (firstLift.getStatus() == Direction.BREAK && Math.abs(firstLift.getCurrentLevel() - call.getFinalLevel()) <= Math.abs(firstLift.getCurrentLevel() - call.getFinalLevel()) || secondLift.getStatus() != Direction.BREAK) {
                            firstLift.setFinalLevel(call.getFinalLevel());
                            firstLift.setStatus(call.getDirection());
                            firstLiftThread = new Thread(firstLift);
                            firstLiftThread.start();
                        } else {
                            secondLift.setFinalLevel(call.getFinalLevel());
                            secondLift.setStatus(call.getDirection());
                            secondLiftThread = new Thread(secondLift);
                            secondLiftThread.start();
                        }
                    }
                }
            }
            firstLiftThread.join();
            secondLiftThread.join();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
