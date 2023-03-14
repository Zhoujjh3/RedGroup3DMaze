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
	public static MazeMap map;
	private Leaderboard leaderboard;
	public static int difficulty;
	public static boolean nextRoom = false, down = false, up = false;
	
	public static Header header;
	public static Maze maze;
	public static PlayerData player;
	
	public static mazeState state;
	public static JButton changeView, levelUp, levelDown;
	public static int mapLevelIncrement = 0;
	
	//3d stuff
	public static boolean clicked;
	public static int width = 1000;
	public static int height = 700;
	
	private boolean showWelcomeScreen = false;
	private boolean showLeaderboard = false;
	
	public enum mazeState{
		WELCOMESCREEN,
		CHAMBERVIEW,
		MAPVIEW,
		LEADERBOARD
	}
	
	public Run3DMaze() {
		screen = new JFrame();
		gamePanel = new GamePanel(width, height);
		gamePanel.addMouseListener(new Clicker());
		gamePanel.addKeyListener(new Clicker());
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		state = mazeState.WELCOMESCREEN;
		selectionScreen = new Selection();
		leaderboard = new Leaderboard();
	}
	
	//Timer for 3D animations
	int counter = 0;
	ActionListener rotate = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(state == mazeState.CHAMBERVIEW) {
				Room currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'), 
						Run3DMaze.player.getCoordinate('X'), 
						Run3DMaze.player.getCoordinate('Y'));
				
				if(width != gamePanel.getWidth() || height != gamePanel.getHeight()) {
					GamePanel.timeCounter = 0;
				}
				width = gamePanel.getWidth();
				height = gamePanel.getHeight();
				for(Shapes3D shape : currentRoom.ceilingAndFloor) {
					shape.update();
				}
				for(Shapes3D shape : currentRoom.walls) {
					shape.update();
				}
				for(Shapes3D shape : currentRoom.doors) {
					shape.update();
				}
				//gamePanel.repaint();		//should be run no matter what state
				GamePanel.timeCounter++;
				
				//updates 3d states after animation is finished
				if(GamePanel.timeCounter >= 200 && clicked) {
					if(nextRoom) {
						//handles moving the player in the backend
						if(down) {
							System.out.println("moving down");
							Run3DMaze.player.movePlayer('D');
						} else if(up) {
							Run3DMaze.player.movePlayer('U');
						} else {
							boolean endGame = false;
							if (player.getCoordinate('X') == maze.getMazeSize()-1 && player.getCoordinate('Y') == maze.getMazeSize()-1 && player.getCoordinate('Z') == maze.getMazeSize()-1 && player.getDirection() == 'E') {
								endGame = true;
							}
							Run3DMaze.player.movePlayer(Run3DMaze.player.getDirection());
							if (endGame) {
								runLeaderboard();
							}
						}
						currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'), 
								Run3DMaze.player.getCoordinate('X'), 
								Run3DMaze.player.getCoordinate('Y'));
						currentRoom.resetDoors();	
						currentRoom.populateDoors();
						currentRoom.setVisited(true);
						currentRoom.printDoors();
						currentRoom.populateTrapDoorsAndHatch();
						nextRoom = false;
						down = false;
						up = false;
						clicked = false;
					} else {
						for(Shapes3D i : currentRoom.walls) {
							if(Clicker.dir == 0) {
								i.setState(i.getState() + 1);
								i.setState(i.getState() % 4);
								clicked = false;
							} else if (Clicker.dir == 1) {
								i.setState(i.getState() - 1);
								if(i.getState() == -1) {
									i.setState(3);
								}
								clicked = false;
							}
						}
						for(Shapes3D i : currentRoom.doors) {
							if(Clicker.dir == 0) {
								i.setState(i.getState() + 1);
								i.setState(i.getState() % 4);
								clicked = false;
							} else if (Clicker.dir == 1) {
								i.setState(i.getState() - 1);
								if(i.getState() == -1) {
									i.setState(3);
								}
								clicked = false;
							}
						}
					}
				} 
			} else if (state == mazeState.WELCOMESCREEN) {
				//System.out.println("WELCOME");
				if(showWelcomeScreen) {	//so it doesn't draw infinite selection screens
					selectionScreen.display(screen, gamePanel);
					showWelcomeScreen = false;
					//System.out.println("LOL");
				}
				
				//this doesn't seem to work because checksignal only returns true
				//after you click a button, which at that point the state switches to 
				//chamber view
				if (selectionScreen.checkSignal()) {
					difficulty = selectionScreen.difficulty;
					selectionScreen.resetSignal();
					runMaze();
					System.out.println(difficulty);
					System.out.println(state);
				}
			} else if (state == mazeState.MAPVIEW) {
				
			} else if (state == mazeState.LEADERBOARD) {
				if (showLeaderboard) {
					leaderboard.display(screen, gamePanel);
					showLeaderboard = false;
					System.out.println("leaderBord");
				}
				
				if (leaderboard.checkSignal()) {
					leaderboard.resetSignal();
					play3DMaze();
				}
			}
			//switch rooms when timerCounter = 200, maybe
			gamePanel.repaint();
		}
	};
	Timer ShapesTimer = new Timer(5, rotate);
	
	public void play3DMaze() { // essentially a runWelcomeScreen
		//selectionScreen.display(screen, gamePanel);
		screen.setContentPane(gamePanel);
		screen.pack();
		screen.setVisible(true);
		screen.setResizable(true);
		screen.setLocationRelativeTo(null);
		state = mazeState.WELCOMESCREEN;
		showWelcomeScreen = true;
		ShapesTimer.start();
	}
	
	public void runMaze() {
		// initialize maze, map, header, and playerData
		maze = new Maze(difficulty);
		player = new PlayerData(maze.getMazeSize());
		header = new Header(maze, player);
		Room currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'), 
				Run3DMaze.player.getCoordinate('X'), 
				Run3DMaze.player.getCoordinate('Y'));
		currentRoom.populateDoors();
		
		map = new MazeMap(maze, player);
		header = new Header(maze, player);
		changeView = new JButton(header.getView());
		levelDown = new JButton("Level Up");
		levelUp = new JButton("Level Down");
		gamePanel.setLayout(null);
		gamePanel.add(changeView);
		gamePanel.add(levelUp);
		gamePanel.add(levelDown);
		
		levelDown.setVisible(false);
		levelUp.setVisible(false);
		changeView.setBounds(740, 5, 100, 30);
		levelUp.setBounds(300, 665, 100, 30);
		levelDown.setBounds(450, 665, 100, 30);
		
		
		changeView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				header.changeView();
				changeView.setText(header.getView());
				if (state == mazeState.CHAMBERVIEW) {
					runMapView();
				} else if (state == mazeState.MAPVIEW) {
					runChamberView();
					gamePanel.requestFocus();
				}
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
		
		
		runChamberView();
	}
	
	public void runChamberView() {
		
		if (header.getView().equals("CHAMBER")) {
			header.changeView();
		}
		state = mazeState.CHAMBERVIEW;
	}
	
	public void runMapView() {
		
		if (header.getView().equals("MAP")) {
			header.changeView();
		}
		state = mazeState.MAPVIEW;
	}
	
	public void runLeaderboard() {
		leaderboard.getScore((double)maze.getMinMoves() / (double)player.getMoves());
		state = mazeState.LEADERBOARD;
		showLeaderboard = true;
	}
	
	public static void main(String[]args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				new Run3DMaze().play3DMaze();
			}
		});
	}
}

