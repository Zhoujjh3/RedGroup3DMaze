import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;
public class Run3DMaze {
	//Running the Program
	
	public static JFrame screen;
	private static GamePanel gamePanel;
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
	
	public static boolean clicked;
	public static int width = 1000;
	public static int height = 700;
	
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
		selectionScreen = new Selection(screen, gamePanel);
		leaderboard = new Leaderboard();
	}
	
	//Timer for 3D animations
	int counter = 0;
	ActionListener rotate = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(state == mazeState.CHAMBERVIEW) {				
				selectionScreen.hide(screen, gamePanel); 
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
				GamePanel.timeCounter++;
				
				//updates 3d states after animation is finished
				if(GamePanel.timeCounter >= 40 && clicked) {
					
					if(nextRoom) {
						if(down) {
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
						currentRoom.populateTrapDoorsAndHatch();
						currentRoom.resetWalls();
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
				selectionScreen.display();
				if (selectionScreen.checkSignal()) {
					difficulty = selectionScreen.difficulty;
					selectionScreen.resetSignal();
					runMaze();
				}
			} else if (state == mazeState.LEADERBOARD) {
				if (showLeaderboard) {
					leaderboard.display(screen, gamePanel);
					showLeaderboard = false;
				}
				
				if (leaderboard.checkSignal()) {
					leaderboard.resetSignal();
					play3DMaze();
				}
			}
			gamePanel.repaint();
		}
	};
	Timer ShapesTimer = new Timer(5, rotate);
	
	public void play3DMaze() { 
		// essentially a runWelcomeScreen
		screen.setContentPane(gamePanel);
		screen.pack();
		screen.setVisible(true);
		screen.setResizable(true);
		screen.setLocationRelativeTo(null);
		state = mazeState.WELCOMESCREEN;
		gamePanel = new GamePanel(1000, 700);
		gamePanel.addMouseListener(new Clicker());
		gamePanel.addKeyListener(new Clicker());
		screen.pack();
		screen.setVisible(true);
		screen.setResizable(true);
		screen.setLocationRelativeTo(null);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		state = mazeState.WELCOMESCREEN;
		selectionScreen = new Selection(screen, gamePanel);
		screen.setContentPane(gamePanel);
		screen.revalidate();
		screen.repaint();
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
		currentRoom.populateTrapDoorsAndHatch();
		
		map = new MazeMap(maze, player);
		header = new Header(maze, player);
		changeView = new JButton(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("MAP Button.png")).getImage().
				getScaledInstance(80, 24, java.awt.Image.SCALE_SMOOTH)));
		changeView.setOpaque(false);
        changeView.setContentAreaFilled(false);
        changeView.setBorderPainted(false);
		levelDown = new JButton(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("levelup.png")).getImage().
				getScaledInstance(100, 30, java.awt.Image.SCALE_SMOOTH)));
		levelDown.setOpaque(false);
		levelDown.setContentAreaFilled(false);
        levelDown.setBorderPainted(false);
		levelUp = new JButton(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("leveldown.png")).getImage().
				getScaledInstance(100, 30, java.awt.Image.SCALE_SMOOTH)));
		levelUp.setOpaque(false);
		levelUp.setContentAreaFilled(false);
		levelUp.setBorderPainted(false);
		gamePanel.setLayout(null);
		gamePanel.add(changeView);
		gamePanel.add(levelUp);
		gamePanel.add(levelDown);
		
		levelDown.setVisible(false);
		levelUp.setVisible(false);
		changeView.setBounds(820, 5, 100, 30);
		levelUp.setBounds(300, 712, 100, 30);
		levelDown.setBounds(450, 712, 100, 30);
		
		changeView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				header.changeView();
				changeView.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
	    				getResource(header.getButtonView()+" Button.png")).getImage().
	    				getScaledInstance((int)(80 * screen.getSize().width/1000), (int)(24* screen.getSize().height/750), 
	    						java.awt.Image.SCALE_SMOOTH)));
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
	
	public static void runChamberView() {
		
		if (header.getView().equals("CHAMBER")) {
			header.changeView();
		}
		state = mazeState.CHAMBERVIEW;
	}
	
	public static void runMapView() {
		
		if (header.getView().equals("MAP")) {
			header.changeView();
		}
		gamePanel.requestFocusInWindow();
		state = mazeState.MAPVIEW;
	}
	
	public void runLeaderboard() {
		leaderboard.getScore((double)(Math.round(1000.0 * ((double)maze.getMinMoves() / ((double)player.getMoves()-1))))/1000.0);
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

