import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class HeaderTester {
    JFrame frame;
    JPanel panel;
    Header header;
    Maze maze = new Maze();
    PlayerData player = new PlayerData(maze);
    public Main() {
        header = new Header(maze, player);
        frame = new JFrame("game test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();
        frame.setContentPane(panel);

        /* Size and then display the frame. */
        frame.setSize(500,500);
        frame.setVisible(true);
    }

    class Panel extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            header.display(g);
            g.dispose();
        }
    }
    public static void main(String[] args){
        new Main();
    }
}