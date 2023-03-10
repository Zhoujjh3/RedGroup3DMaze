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
    public HeaderTester() {
        header = new Header(maze, player);
        frame = new JFrame("header tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,750);
        panel = new Panel();
        changeView = new JButton(header.getView());
        panel.setLayout(null);
        changeView.setBounds(740, 5, 100, 30);
       // changeView.setBounds((int)(740 * frame.getSize().width/1000), (int)(5* frame.getSize().height/750), (int)(100 * frame.getSize().width/1000), (int)(30 * frame.getSize().height/750));
        changeView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.changeView();
                changeView.setText(header.getView());
            }
        });
        frame.setContentPane(panel);
        panel.add(changeView);

        /* Size and then display the frame. */

        frame.setVisible(true);
        frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		//comment
    }

    class Panel extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            header.display(g ,this.getSize());
            changeView.setBounds((int)(740 * frame.getSize().width/1000), (int)(5* frame.getSize().height/750), (int)(100 * frame.getSize().width/1000), (int)(30 * frame.getSize().height/750));

        }
    }
    public static void main(String[] args){
        new HeaderTester();
    }
}