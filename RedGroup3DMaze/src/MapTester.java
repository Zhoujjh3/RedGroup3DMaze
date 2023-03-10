import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapTester {
    JFrame frame;
    JPanel panel;
    MazeMap map;
    ManualTestMaze manMaze = new ManualTestMaze();
    Maze maze = new Maze(1);
    PlayerData player = new PlayerData(4);
    JButton changeView, levelUp, levelDown;
    Header header;
    int mapLevelIncrement = 0;
    private double wScale;
	private double hScale;
    public MapTester() {
        map = new MazeMap(maze, player);
        frame = new JFrame("map tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();
        panel.setPreferredSize(new Dimension(1000, 750));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setContentPane(panel);

        header = new Header(maze, player);
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
                if (player.getCoordinate('Z') + mapLevelIncrement != 0) {
                    mapLevelIncrement--;
                    panel.repaint();
                }
            }
        });
        levelUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (player.getCoordinate('Z') + mapLevelIncrement != maze.getMazeSize() - 1) {
                    mapLevelIncrement++;
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
            if (player.getCoordinate('Z') + mapLevelIncrement == maze.getMazeSize() - 1) {
                levelUp.setBackground(Color.GRAY);
            } else {
                levelUp.setBackground(Color.WHITE);
            }
            if (player.getCoordinate('Z') + mapLevelIncrement == 0) {
                levelDown.setBackground(Color.GRAY);
            } else {
                levelDown.setBackground(Color.WHITE);
            }
            if (header.getView().equals("CHAMBER")){
                map.display(g, player.getCoordinate('Z') + mapLevelIncrement, this.getSize());
                wScale = (double)getSize().width/(double)1000;
                hScale = (double)getSize().height/(double)750;
                changeView.setBounds((int)(740*wScale), (int)(5*hScale), (int)(100*wScale), (int)(30*hScale));
                levelDown.setBounds((int)(300*wScale), (int)(715*hScale), (int)(100*wScale), (int)(30*hScale));
                levelUp.setBounds((int)(450*wScale), (int)(715*hScale), (int)(100*wScale), (int)(30*hScale));
                levelDown.setVisible(true);
                levelUp.setVisible(true);
            } else {
                mapLevelIncrement = 0;
                levelDown.setVisible(false);
                levelUp.setVisible(false);
            }
            header.display(g, this.getSize());
        }
    }

    public static void main(String[] args){
        new MapTester();
    }
}