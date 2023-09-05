import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Food {
    private Coordinates pos;
    private int wight_f;
    private int height_f;
    private int size;
    private Random ran;

    public Food(int wight_f, int height_f, int size) {
        this.wight_f = wight_f;
        this.height_f = height_f;
        this.size = size;
        this.ran = new Random();
        this.catchMe(ran);
    }

    private void catchMe(Random setting) {
        this.pos = new Coordinates(setting.nextInt(this.wight_f), setting.nextInt(height_f));


    }

    public void setSize(int v) {
        this.size = v;
    }

    public Graphics paint_Food(Graphics g) {

        try {
            File file = new File("C:\\Users\\DELL\\IdeaProjects\\MySnackGame\\src\\Imagess\\clipart938124.png");
            Image img = ImageIO.read(file);
            g.drawImage(img , (int)pos.getX()-1,(int)pos.getY()-1,size,size,null);
        } catch (IOException e) {

            Logger.getLogger(Food.class.getName()).log(Level.SEVERE, null, e);

        }
        return g;
    }

    public boolean isFound(Coordinates s) {
        if (pos.normalCol().collision(s, 15)) {
            catchMe(ran);
            return true;
        } ; return false;

    }
}
