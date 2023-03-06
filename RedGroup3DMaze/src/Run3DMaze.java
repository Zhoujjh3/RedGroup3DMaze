import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Run3DMaze {
	
	JFrame screen;
	JPanel gamePanel;
	Selection selectionScreen;
	PlayerData player;
	Maze maze;
	MazeMap map;
	Leaderboard leaderboard;
	mazeState state;
	public enum mazeState{
		WELCOMESCREEN,
		CHAMBERVIEW,
		MAPVIEW,
		LEADERBOARD
	}
	
	public Run3DMaze() {
		
	}
	
	public void runWelcomeScreen() {
		screen = new JFrame();
		gamePanel = new GamePanel();
		gamePanel.setPreferredSize(new Dimension(1000, 750));
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		state = mazeState.WELCOMESCREEN;
		selectionScreen = new Selection();
		// leaderboard = new Leaderboard();
		
		screen.setContentPane(gamePanel);
		screen.pack();
	    screen.setVisible(true);
	    screen.setResizable(false);
		screen.setLocationRelativeTo(null);
		//comment for max
	}
	
	public void runMaze() {
		// initialize maze, map, and playerData
		
		
		// runChamberView();
	}
	
	public void runChamberView() {
		state = mazeState.CHAMBERVIEW;
		
		
	}
	
	public void runMapView() {
		state = mazeState.MAPVIEW;
		
		
	}
	
	public void runLeaderboard() {
		state = mazeState.LEADERBOARD;
		
		
	}
	
	public static void main(String[]args) {
		new Run3DMaze().runWelcomeScreen();
	}
}