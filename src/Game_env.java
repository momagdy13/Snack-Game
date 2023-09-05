import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class Game_env extends JPanel implements Runnable, KeyListener {

    private SnackController snackC;
    private Thread thread;
    private Food prey;
    private boolean flag, start;
    private Random random;
    private int current_value = 0;
    private BestScore best_Score;
    public static int snack_size = 10;
    public static int speed = 10;
    public static int snack_value = 10;

    void setting() {
        prey = null;
        snackC = null;
        this.snackC = new SnackController();
        this.prey = new Food(this.getSize().width, this.getSize().height, 25);
        this.thread = new Thread(this);
        best_Score = BestScore.VALUE;
        this.random = new Random();
        this.flag = true;
        this.start = true;


    }

    public Game_env(int width, int height) {
        super.setSize(width, height);
        setting();



    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        snackC.snackPaint(g);
        prey.paint_Food(g);
        g.setColor(Color.black);
        g.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        g.drawString("My Score: " +current_value,15,15);
        g.drawString("Best Score: " +best_Score.bestScore(),330,15);
        if (prey.isFound(snackC.getPosition())) {
            snackC.grow(3,random);
            current_value+=3;
        }
        if (start) {
            g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
            g.drawString("Please Use Arrows To Start The Game", (int) (getWidth() / 4.4), getHeight() / 2);
            if (snackC.getPosition().check_direction()) {
                start = !start;

            }
        }

        if (!flag) {
            g.setColor(Color.red);
            g.setFont(new Font(Font.SERIF, Font.BOLD, 40));
            g.drawString("Game Over", (int) (getWidth() / 2.79), getHeight() / 2);
            if (current_value > best_Score.bestScore())
                best_Score.writeScore(current_value);
            current_value = 0;

        }

        try {
            snackC.snack_moving();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snackC.snack_up();
                break;
            case KeyEvent.VK_DOWN:
                snackC.snack_down();
                break;
            case KeyEvent.VK_RIGHT:
                snackC.snack_right();
                break;
            case KeyEvent.VK_LEFT:
                snackC.snack_left();
                break;
            default:
                break;

        }

    }

    @Override
    public void run() {

        while (flag) {
            try {
                repaint();
                if (!snackC.inside_fail(getWidth(), getHeight()) || snackC.bite_itself()) {
                    flag = false;

                    repaint();
                    Thread.sleep(2000);
                    setting();

                }
                Thread.sleep(70);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

