import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Run3DMaze {
	
	JFrame screen;
	JPanel gamePanel;
	Maze maze;
	enum mazeState{
		WELCOMESCREEN,
		CHAMBERVIEW,
		MAPVIEW,
		LEADERBOARD
	}
	
	public Run3DMaze() {
		screen = new JFrame();
		gamePanel = new GamePanel();
		gamePanel.setPreferredSize(new Dimension(1000, 750));
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setContentPane(gamePanel);
		screen.pack();
	    screen.setVisible(true);
	    screen.setResizable(false);
		screen.setLocationRelativeTo(null);
		//comment for max
	}
	
	public static void main(String[]args) {
		new Run3DMaze();
	}
}
