import javax.swing.JFrame;
import java.awt.Graphics;

public class base extends JFrame {

    public base() {
        setTitle("Geometric Object");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw rectangle
        g.drawRect(100, 80, 430, 260);
    }

    public static void main(String[] args) {
        new base();
    }
}