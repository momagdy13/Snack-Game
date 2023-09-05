import java.awt.*;

public class SnackPart extends Snack {

    private Coordinates old_position;

    public SnackPart(Coordinates position , Color color) {
        this.position = new Coordinates(position.getX() ,position.getY() + Game_env.snack_value);
        this.old_position = null;
        this.color = color;
    }
    public SnackPart(Coordinates position ) {
        this.position = new Coordinates(position.getX() ,position.getY() + Game_env.snack_value);
        this.old_position = null;
        this.color = Color.BLACK;

    }
    @Override
    public Coordinates getPosition() {
        return this.position;
    }
    public Coordinates getOld_position() {
        return this.old_position;
    }

    public void setPosition(Coordinates point) {
        this.old_position = this.position;
        this.position = point;
    }


    public  boolean isHit(Coordinates p){
        return this.getPosition().collision(p , 10);
    }



}
