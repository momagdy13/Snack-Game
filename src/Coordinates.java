public class Coordinates implements Cloneable {

    private float x, y;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    private int nex_dir;

    public float getX() {
        return x;
    }

    public Coordinates normalCol (){
        return new Coordinates(getX()+6 ,getY()+8);
    }

    public float getY() {
        return y;
    }

    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
        this.reset_nextDirection();
    }

    public void next_move_dir(int direction) {
        switch (direction) {
            case LEFT:
                if (nex_dir != RIGHT) {
                    nex_dir = direction;
                }
                break;
            case RIGHT:
                if (nex_dir != LEFT) {
                    nex_dir = direction;
                }
                break;
            case UP:
                if (nex_dir != DOWN) {
                    nex_dir = direction;
                }
                break;
            case DOWN:
                if (nex_dir != UP) {
                    nex_dir = direction;
                }
                break;
        }
    }

    public boolean move(int speed) {
        boolean check = true;
        switch (nex_dir) {

            case UP:
                this.y -= speed;
                break;
            case DOWN:
                this.y += speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case LEFT:
                this.x -= speed;
                break;
            default:
                check = false;


        }

        return check;
    }

    public boolean collision(Coordinates point, float threshold) {
        float x1 = point.getX() - this.x;
        float y1 = point.getY() - this.y;

        float distance = (float) Math.sqrt((x1 * x1) + (y1 * y1));
        if (distance < threshold)
            return true;
        else return false;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    void reset_nextDirection(){
        nex_dir = 10;

    }
    boolean check_direction(){
        return nex_dir <4;
    }
}


