import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Lift implements Runnable{
    private final String name;
    private Direction status;
    private int currentLevel;
    private int finalLevel;
    private final int countLevels;
    private final Queue<Call> calls;
    public Lift(String name, Direction status, int currentLevel, int finalLevel, int countLevels, Queue<Call> calls) {
        this.name = name;
        this.status = status;
        this.currentLevel = currentLevel;
        this.finalLevel = finalLevel;
        this.countLevels = countLevels;
        this.calls = calls;
    }

    public Direction getStatus() {
        return status;
    }
    public void setStatus(Direction status) {
        this.status = status;
    }
    public int getCurrentLevel() {
        return currentLevel;
    }
    public void setFinalLevel(int finalLevel) {
        this.finalLevel = finalLevel;
    }
    @Override
    public void run(){
        System.out.printf("%s lift received a call. It's moving %s to the %d level\n", name, status, finalLevel);
        moveTo();
        System.out.printf("%s lift reached the %d level and took a person. It's moving %s\n", name, finalLevel, status);
        finalLevel = status == Direction.UP ? countLevels : 0;
        moveTo();
        System.out.printf("%s lift has ended its work\n", name);
        this.finalLevel = -1;
        this.status = Direction.BREAK;
    }
    public void moveTo(){
        try{
            List<Integer> curCalls = new ArrayList<>();
            int path = currentLevel < finalLevel ? 1 : -1;
            while(currentLevel != finalLevel) {
                int capacity = curCalls.size();
                if (path == 1) {
                    for (Call c : calls) {
                        if ((c.getDirection() == status || finalLevel == countLevels && c.getDirection() == Direction.UP) && c.getFinalLevel() >= currentLevel) {
                            curCalls.add(c.getFinalLevel());
                        }
                    }
                } else {
                    for (Call c : calls) {
                        if ((c.getDirection() == status || finalLevel == 0 && c.getDirection() == Direction.DOWN) && c.getFinalLevel() <= currentLevel) {
                            curCalls.add(c.getFinalLevel());
                        }
                    }
                }
                for (int i = capacity; i < curCalls.size(); i++){
                    Call curCall = new Call(status, curCalls.get(i));
                    calls.remove(curCall);
                }
                while(curCalls.contains(currentLevel)){
                    System.out.printf("%s lift took a person at the %d level\n", name, currentLevel);
                    curCalls.remove(Integer.valueOf(currentLevel));
                }
                currentLevel += path;
                System.out.printf("%s lift is moving %s to the %d level\n", name, status, currentLevel);
                Thread.sleep(1000);
            }
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
