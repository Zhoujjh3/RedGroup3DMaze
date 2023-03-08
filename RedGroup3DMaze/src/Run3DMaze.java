import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Backup3D.Shapes;
import Backup3D.ShapesClicker;
import Backup3D.ShapesPanel;

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
	JButton changeView, levelUp, levelDown;
	int mapLevelIncrement = 0;
	
	//3d stuff
	public static boolean clicked;
	public static int width = 1000;
	public static int height = 700;
	
	public enum mazeState{
		WELCOMESCREEN,
		CHAMBERVIEW,
		MAPVIEW,
		LEADERBOARD
	}
	
	public Run3DMaze() {
		screen = new JFrame();
		gamePanel = new GamePanel(width, height);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		state = mazeState.WELCOMESCREEN;
		selectionScreen = new Selection();
		leaderboard = new Leaderboard();
		/*
		map = new MazeMap(maze, player);
		header = new Header(maze, player);
		changeView = new JButton(header.getView());
		levelDown = new JButton("Level Down");
		levelUp = new JButton("Level Up");
		gamePanel.setLayout(null);
		gamePanel.add(changeView);
		gamePanel.add(levelUp);
		gamePanel.add(levelDown);
		
		levelDown.setVisible(false);
		levelUp.setVisible(false);
		changeView.setBounds(740, 5, 100, 30);
		levelUp.setBounds(300, 715, 100, 30);
		levelDown.setBounds(450, 715, 100, 30);
		
		
		changeView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				header.changeView();
				changeView.setText(header.getView());
				gamePanel.repaint();
			}
		});
	
		levelDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getCoordinate('Z') + mapLevelIncrement != 0) {
					mapLevelIncrement--;
					gamePanel.repaint();
				}
			}
		});

		levelUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getCoordinate('Z') + mapLevelIncrement != maze.getMazeSize() - 1) {
					mapLevelIncrement++;
					gamePanel.repaint();
				}
			}
		});
		*/
	}
	
	//Timer for 3D animations
	ActionListener rotate = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(state == mazeState.CHAMBERVIEW) {
				if(width != gamePanel.getWidth() || height != gamePanel.getHeight()) {
					ShapesPanel.timeCounter = 0;
				}
				width = gamePanel.getWidth();
				height = gamePanel.getHeight();
				for(Shapes shape : ShapesPanel.ceilingAndFloor) {
					shape.update();
				}
				for(Shapes shape : ShapesPanel.walls) {
					shape.update();
				}
				for(Shapes shape : ShapesPanel.doors) {
					shape.update();
				}
				gamePanel.repaint();
				ShapesPanel.timeCounter++;
				
				//updates 3d states after animation is finished
				if(ShapesPanel.timeCounter >= 200 && clicked) {
					for(Shapes i : ShapesPanel.walls) {
						if(ShapesClicker.dir == 0) {
							i.setState(i.getState() + 1);
							i.setState(i.getState() % 4);
							clicked = false;
						} else if (ShapesClicker.dir == 1) {
							i.setState(i.getState() - 1);
							if(i.getState() == -1) {
								i.setState(3);
							}
							clicked = false;
						}
					}
					for(Shapes i : ShapesPanel.doors) {
						if(ShapesClicker.dir == 0) {
							i.setState(i.getState() + 1);
							i.setState(i.getState() % 4);
							clicked = false;
						} else if (ShapesClicker.dir == 1) {
							i.setState(i.getState() - 1);
							if(i.getState() == -1) {
								i.setState(3);
							}
							clicked = false;
						}
					}
				} 
			}
			//switch rooms when timerCounter = 200, maybe
		}
	};
	Timer ShapesTimer = new Timer(5, rotate);
	
	public void play3DMaze() { // essentially a runWelcomeScreen
		
		screen.setContentPane(gamePanel);
		screen.pack();
		screen.setVisible(true);
		screen.setResizable(false);
		screen.setLocationRelativeTo(null);
	}
	
	public void runMaze() {
		// initialize maze, map, header, and playerData
		
		
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
		new Run3DMaze().play3DMaze();
	}
}

