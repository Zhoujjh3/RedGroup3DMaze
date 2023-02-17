import javax.swing.*;
import java.awt.*;
public class HeaderTester {
    JFrame frame;
    JPanel panel;
    Header header;
    Maze maze = new Maze(new char[1][1][1]);
    PlayerData player = new PlayerData(maze);
    public HeaderTester() {
        header = new Header(maze, player);
        frame = new JFrame("game test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();
        frame.setContentPane(panel);

        /* Size and then display the frame. */
        frame.setSize(1000,750);
        frame.setVisible(true);
        frame.setResizable(true);
		frame.setLocationRelativeTo(null);
    }

    class Panel extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            header.display(g);
            //g.dispose();
        }
    }
    public static void main(String[] args){
        new HeaderTester();
    }
}