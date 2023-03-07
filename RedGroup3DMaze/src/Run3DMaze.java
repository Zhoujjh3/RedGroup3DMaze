import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Run3DMaze {
	
	private JFrame screen;
	private JPanel gamePanel;
	private Selection selectionScreen;
	private PlayerData player;
	private Maze maze;
	private MazeMap map;
	private Header header;
	private Leaderboard leaderboard;
	private mazeState state;
	private int difficulty;
	public enum mazeState{
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
		
		state = mazeState.WELCOMESCREEN;
		selectionScreen = new Selection();
		leaderboard = new Leaderboard();
	}
	
	public void play3DMaze() { // essentially a runWelcomeScreen
		
		screen.setContentPane(gamePanel);
		screen.pack();
	    screen.setVisible(true);
	    screen.setResizable(false);
		screen.setLocationRelativeTo(null);
		
		selectionScreen.display();
	}
	
	public void runMaze() {
		// initialize maze, map, header, and playerData
		maze = new Maze(difficulty);
		player = new PlayerData(maze.getMazeSize());
		map = new MazeMap(maze, player);
		header = new Header(maze, player);
		
		
		runChamberView();
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
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				//new Run3DMaze().play3DMaze();
			}
		});
	}
}