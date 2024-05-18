import java.util.Objects;

public class Call {
    private final Direction direction;
    private final int finalLevel;
    public Call(Direction direction, int finalLevel){
        this.direction = direction;
        this.finalLevel = finalLevel;
    }
    public Direction getDirection() {
        return direction;
    }
    public int getFinalLevel() {
        return finalLevel;
    }
}
