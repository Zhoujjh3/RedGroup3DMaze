import javax.swing.*;
import java.awt.*;

public class MapTester {
    JFrame frame;
    JPanel panel;
    Header header;
    Maze maze = new Maze();
    PlayerData player = new PlayerData(maze);
    public MapTester() {
        MazeMap map = new MazeMap(maze, player);
        frame = new JFrame("map tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        frame.setContentPane(panel);

        /* Size and then display the frame. */
        frame.setSize(1000,750);
        frame.setVisible(true);
        frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
    }

    public static void main(String[] args){
        new MapTester();
    }
}