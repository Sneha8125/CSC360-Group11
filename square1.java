import javax.swing.JFrame;
import java.awt.Graphics;

public class square1 extends JFrame {

    public square1() {
        setTitle("Square");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw square
        g.drawRect(200, 150, 100, 100);
    }

    public static void main(String[] args) {
        new square1();
    }
}
