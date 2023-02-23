import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class HeaderTester {
    JFrame frame;
    JPanel panel;
    Header header;
    Maze maze = new Maze(1);
    ManualTestMaze maze1 = new ManualTestMaze();
    PlayerData player = new PlayerData(1);
    MazeMap map = new MazeMap(maze1, player);
    JButton changeView;
    public HeaderTester() {
        header = new Header(maze, player);
        frame = new JFrame("game test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel();
        changeView = new JButton(header.getView());
        panel.setLayout(null);
        changeView.setBounds(740
                , 5,
                100,
                30 );

        changeView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.changeView();
                changeView.setText(header.getView());
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
        }
    }
    public static void main(String[] args){
        new HeaderTester();
    }
}