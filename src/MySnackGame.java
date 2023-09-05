import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MySnackGame extends JFrame {
    MySnackGame() {
        this.creatGame();
    }


    private JFrame jf;
    private Game_env gev;
    private JLabel background;
    private void creatGame() {
        jf = new JFrame("Snack Game");
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        jf.setResizable(false);
        jf.setSize(800, 600);
        jf.setLocationRelativeTo(null);
        gev = new Game_env(590, 390);

        jf.add(gev);
        jf.addKeyListener(gev);

        jf.setVisible(true);


    }

}
