import java.util.Objects;

public class Call {
    private final Direction direction;
    private final int finalLevel;
    public Call(Direction direction, int endFloor){
        this.direction = direction;
        this.finalLevel = endFloor;
    }
    public Direction getDirection() {
        return direction;
    }
    public int getFinalLevel() {
        return finalLevel;
    }
    @Override
    public int hashCode(){
        return Objects.hash(finalLevel, direction);
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Call request = (Call) obj;
        return finalLevel == request.finalLevel && direction == request.direction;
    }
}
