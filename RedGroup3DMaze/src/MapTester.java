import javax.swing.*;
import java.awt.*;
public class MapTester {
    JFrame frame;
    JPanel panel;
    MazeMap map;
    Maze maze = new Maze(4);
    PlayerData player = new PlayerData(4);
    public MapTester() {
        map = new MazeMap(maze, player);
        frame = new JFrame("map tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();
        frame.setContentPane(panel);

        frame.setSize(1000,750);
        frame.setVisible(true);
        frame.setResizable(false);
		frame.setLocationRelativeTo(null);
    }
    
    class Panel extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            map.display(g);
        }
    }

    public static void main(String[] args){
        new MapTester();
    }
}