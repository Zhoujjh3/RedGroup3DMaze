import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapTester {
    JFrame frame;
    JPanel panel;
    MazeMap map;
    Maze basicMaze = new Maze(1);
    ManualTestMaze maze = new ManualTestMaze();
    PlayerData player = new PlayerData(4);
    JButton changeView;
    Header header;
    public MapTester() {
        map = new MazeMap(maze, player);
        frame = new JFrame("map tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();
        frame.setContentPane(panel);

        header = new Header(basicMaze, player);
        changeView = new JButton(header.getView());
        panel.setLayout(null);
        panel.add(changeView);
        
        changeView.setBounds(740, 5, 100, 30);
        changeView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.changeView();
                changeView.setText(header.getView());
                panel.repaint();
            }
        });
        frame.setSize(1000,750);
        frame.setVisible(true);
        frame.setResizable(false);
		frame.setLocationRelativeTo(null);
    }
    
    class Panel extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (header.getView().equals("CHAMBER")){
                map.display(g);
            }
            header.display(g);
        }
    }

    public static void main(String[] args){
        new MapTester();
    }
}