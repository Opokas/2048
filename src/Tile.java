
public class Tile {
    private int x;
    private int y;
    private int value;
    boolean merged=false;

    public boolean isMerged() {
        return merged;
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Tile (int x, int y, int value){
        this.x=x;
        this.y=y;
        this.value=value;
    }

    public void addValue(int value){
        this.value+=value;
        this.merged=true;
    }

}
