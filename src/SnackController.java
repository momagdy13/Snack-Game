import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SnackController extends Snack {

    private ArrayList<SnackPart> parts;

    public SnackController() {

        this.position = new Coordinates(200, 200);
        this.parts = new ArrayList<>();

        this.parts.add(new SnackPart(position));
        this.parts.add(new SnackPart(parts.get(0).getPosition()));

    }

    public boolean inside_fail(int wight, int high) {
        if (this.position.getX() > wight-1 || this.position.getX() < 0
                || this.position.getY() > high-1 || this.position.getY() < 0)
            return false;
        else return true;

    }

    @Override
    public Coordinates getPosition() {
        return this.position;
    }

    public void snack_left() {
        this.getPosition().next_move_dir(Coordinates.LEFT);
    }

    public void snack_right() {
        this.getPosition().next_move_dir(Coordinates.RIGHT);
    }

    public void snack_up() {
        this.getPosition().next_move_dir(Coordinates.UP);
    }

    public void snack_down() {
        this.getPosition().next_move_dir(Coordinates.DOWN);
    }


    public Graphics snackPaint(Graphics g) {
        g.setColor(Color.red);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.drawOval((int) this.position.getX(), (int) this.position.getY(), Game_env.snack_size, Game_env.snack_size);


        g2d.setStroke(new BasicStroke(4));
        for (SnackPart part : parts) {
            g2d.setColor(part.color);
            g2d.drawOval((int) part.getPosition().getX(), (int) part.getPosition().getY(), Game_env.snack_size, Game_env.snack_size);
        }

        return g2d;
    }

    public void snack_moving() throws CloneNotSupportedException {
        Coordinates temp = (Coordinates) (getPosition().clone());

        if (position.move(Game_env.speed)) {
            for (SnackPart part : parts) {
                part.setPosition(temp);
                temp = part.getOld_position();
            }
        }
    }

    public void grow(int n , Random random) {
        Color color = Color.getHSBColor(random.nextFloat() , .9f, .6f);
        for (int i = 0; i <n ; i++) {
            parts.add(new SnackPart(parts.get(parts.size()-1).getPosition(),color));
        }
    }
    boolean bite_itself(){

        for (int i = 0; i < parts.size()-1; i++) {
          if( parts.get(i).isHit(this.getPosition())){
                return true;
            }
        }
        return false;

    }
}
