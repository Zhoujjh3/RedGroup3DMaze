import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class HeaderTester {
    JFrame frame;
    JPanel panel;
    Header header;
    Maze maze = new Maze(1);
    PlayerData player = new PlayerData(1);
    MazeMap map = new MazeMap(maze, player);
    JButton changeView;
    String buttonText;
    public HeaderTester() {
        header = new Header(maze, player);
        frame = new JFrame("game test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonText = header.getView();
        panel = new Panel();
        changeView = new JButton(buttonText);
        panel.setLayout(null);
        changeView.setBounds(740
                , 5,
                80,
                30 );

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
            buttonText = header.getView();
        }
    }
    public static void main(String[] args){
        new HeaderTester();
    }
}