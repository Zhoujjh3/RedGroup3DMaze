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
    JButton changeView, levelUp, levelDown;
    Header header;
    int levelIncrement = 0;
    public MapTester() {
        map = new MazeMap(maze, player);
        frame = new JFrame("map tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();
        panel.setPreferredSize(new Dimension(1000, 750));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setContentPane(panel);

        header = new Header(basicMaze, player);
        changeView = new JButton(header.getView());
        levelDown = new JButton("Level Down");
        levelUp = new JButton("Level Up");
        panel.setLayout(null);
        panel.add(changeView);
        panel.add(levelUp);
        panel.add(levelDown);
        levelDown.setVisible(false);
        levelUp.setVisible(false);
        
        changeView.setBounds(740, 5, 100, 30);
        levelUp.setBounds(300, 715, 100, 30);
        levelDown.setBounds(450, 715, 100, 30);
        changeView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.changeView();
                changeView.setText(header.getView());
                panel.repaint();
            }
        });
        levelDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (player.getCoordinate('Z') + levelIncrement != 0) {
                    levelIncrement--;
                    panel.repaint();
                }
            }
        });
        levelUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (player.getCoordinate('Z') + levelIncrement != 3) {
                    levelIncrement++;
                    panel.repaint();
                }
            }
        });
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
		frame.setLocationRelativeTo(null);
    }
    //rip
    class Panel extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (player.getCoordinate('Z') + levelIncrement == 3) {
                levelUp.setBackground(Color.GRAY);
            } else {
                levelUp.setBackground(Color.WHITE);
            }
            if (player.getCoordinate('Z') + levelIncrement == 0) {
                levelDown.setBackground(Color.GRAY);
            } else {
                levelDown.setBackground(Color.WHITE);
            }
            if (header.getView().equals("CHAMBER")){
                map.display(g, player.getCoordinate('Z') + levelIncrement, this.getSize());
                levelDown.setVisible(true);
                levelUp.setVisible(true);
            } else {
                levelIncrement = 0;
                levelDown.setVisible(false);
                levelUp.setVisible(false);
            }
            header.display(g, 1);
        }
    }

    public static void main(String[] args){
        new MapTester();
    }
}