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
	private GamePanel gamePanel;
	private Selection selectionScreen;
	private MazeMap map;
	private Header header;
	private Leaderboard leaderboard;
	public static int difficulty;
	
	public static Maze maze;
	public static PlayerData player;
	
	public static mazeState state;
	private JButton changeView, levelUp, levelDown;
	private int mapLevelIncrement = 0;
	
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
		play3DMaze();
		ShapesTimer.start();
	}
	
	//Timer for 3D animations
	int counter = 0;
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
				//gamePanel.repaint();		//should be run no matter what state
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
			} else if (state == mazeState.WELCOMESCREEN) {
				//System.out.println("WELCOME");
				if(counter == 0) {	//so it doesn't draw infinite selection screens
					selectionScreen.display(screen, gamePanel);
					System.out.println("LOL");
				}
				
				//this doesn't seem to work because checksignal only returns true
				//after you click a button, which at that point the state switches to 
				//chamber view
//				if (selectionScreen.checkSignal()) {
//					difficulty = selectionScreen.difficulty;
//					selectionScreen.difficulty = -1;
//					selectionScreen.resetSignal();
//					runMaze();
//					System.out.println(difficulty);
//					System.out.println(state);
//				}
			} else if (state == mazeState.MAPVIEW) {
				
			} else if (state == mazeState.LEADERBOARD) {
				leaderboard.display();
				
				if (leaderboard.checkSignal()) {
					leaderboard.resetSignal();
					play3DMaze();
				}
			}
			//switch rooms when timerCounter = 200, maybe
			gamePanel.repaint();
			counter++;
		}
	};
	Timer ShapesTimer = new Timer(5, rotate);
	
	public void play3DMaze() { // essentially a runWelcomeScreen
		//selectionScreen.display(screen, gamePanel);
		screen.setContentPane(gamePanel);
		screen.pack();
		screen.setVisible(true);
		screen.setResizable(false);
		screen.setLocationRelativeTo(null);
		state = mazeState.WELCOMESCREEN;
	}
	
	public static void runMaze() {
		// initialize maze, map, header, and playerData
		maze = new Maze(difficulty);
		player = new PlayerData(maze.getMazeSize());
		
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
		
		//runChamberView();
	}
	
	public void runChamberView() {
		
		if (header.getView().equals("MAP")) {
			header.changeView();
		}
		state = mazeState.CHAMBERVIEW;
	}
	
	public void runMapView() {
		
		if (header.getView().equals("CHAMBER")) {
			header.changeView();
		}
		state = mazeState.MAPVIEW;
	}
	
	public void runLeaderboard() {
		leaderboard.getScore((double)maze.getMinMoves() / (double)player.getMoves());
		
		state = mazeState.LEADERBOARD;
	}
	
	public static void main(String[]args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				new Run3DMaze();
				//new Run3DMaze().play3DMaze();
			}
		});
	}
}

