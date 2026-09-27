import java.awt.Graphics;

import javax.swing.JFrame;

public class circle extends JFrame {

    public circle() {
        setTitle("Circle");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw circle
        g.drawOval(200, 150, 100, 100);
    }

    public static void main(String[] args) {
        new circle();
    }
}