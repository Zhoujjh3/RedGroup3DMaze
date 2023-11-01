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
        
        changeView = new JButton(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("MAP Button.png")).getImage().
				getScaledInstance(80, 24, 
						java.awt.Image.SCALE_SMOOTH)));
        changeView.setOpaque(false);
        changeView.setContentAreaFilled(false);
        changeView.setBorderPainted(false);
        panel.setLayout(null);
        changeView.setBounds(820, 5, 100, 30);
        changeView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.changeView();
                changeView.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
        				getResource(header.getView()+" Button.png")).getImage().
        				getScaledInstance(80, 24, 
        						java.awt.Image.SCALE_SMOOTH)));
            }
        });
        frame.setContentPane(panel);
        panel.add(changeView);
        changeView.setOpaque(false);
        changeView.setContentAreaFilled(false);
        changeView.setBorderPainted(false);

        frame.setVisible(true);
        frame.setResizable(true);
		frame.setLocationRelativeTo(null);
    }

    class Panel extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            header.display(g ,this.getSize());
            changeView.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
    				getResource("MAP Button.png")).getImage().
    				getScaledInstance((int)(80 * frame.getSize().width/1000), (int)(24* frame.getSize().height/750), 
    						java.awt.Image.SCALE_SMOOTH)));
            changeView.setBounds((int)(820 * frame.getSize().width/1000), (int)(5* frame.getSize().height/750), (int)(100 * frame.getSize().width/1000), (int)(30 * frame.getSize().height/750));

        }
    }
    public static void main(String[] args){
        new HeaderTester();
    }
}