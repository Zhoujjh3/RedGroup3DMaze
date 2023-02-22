import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class HeaderTester {
    JFrame frame;
    JPanel panel;
    Header header;
    Maze maze = new Maze();
    PlayerData player = new PlayerData(maze);
    MazeMap map = new MazeMap(maze, player);
    JButton changeView;
    public HeaderTester() {
        header = new Header(maze, player);
        frame = new JFrame("game test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();
        changeView = new JButton("MAP");
        changeView.setLayout(null);
        changeView.setLocation(840,7);
        changeView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.changeView();
                panel.repaint();
            }
        });
        frame.setContentPane(panel);
        panel.add(changeView);

        /* Size and then display the frame. */
        frame.setSize(1000,750);
        frame.setVisible(true);
        frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//comment
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