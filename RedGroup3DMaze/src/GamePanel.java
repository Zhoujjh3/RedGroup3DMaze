import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	JFrame frame;
    JPanel panel;
    MazeMap map;
    ManualTestMaze manMaze = new ManualTestMaze();
    Maze maze = new Maze(1);
    PlayerData player = new PlayerData(4);
    JButton changeView, levelUp, levelDown;
    Header header;
    int mapLevelIncrement = 0;
    
	public void paintComponent(Graphics g) {
		
	}
	
	public void setChamberView(Graphics g) {
		
	}
	
	public void setMapView(Graphics g) {
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
            levelDown.setVisible(true);
            levelUp.setVisible(true);
        } else {
            mapLevelIncrement = 0;
            levelDown.setVisible(false);
            levelUp.setVisible(false);
        }
        levelDown = new JButton("Level Down");
        levelUp = new JButton("Level Up");
        panel.setLayout(null);
        panel.add(levelUp);
        panel.add(levelDown);
        levelDown.setVisible(false);
        levelUp.setVisible(false);
        
        levelUp.setBounds(300, 715, 100, 30);
        levelDown.setBounds(450, 715, 100, 30);
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
	}
	
	public void setHeader(Graphics g) {
		header.display(g, this.getSize());
		header = new Header(maze, player);
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
        
	}
	
	public void setLeaderboard(Graphics g) {
		
	}
	
	public void setSelectionScreen(Graphics g) {
		
	}
}
