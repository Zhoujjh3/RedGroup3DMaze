import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Backup3D.Shapes;
import Backup3D.ShapesPanel;

public class Clicker implements MouseListener, KeyListener {

	Run3DMaze.mazeState state;
	char[] directions = { 'N', 'E', 'S', 'W' };
	Room currentRoom;
	public static int dir;
	public int h;
	public int w;
	double xScale = w / 1000.0;
	double yScale = h / 700.0;

	@Override
	public void mouseClicked(MouseEvent e) {
		if (GamePanel.timeCounter > 40) {
			state = Run3DMaze.state;

			switch (state) {
			case WELCOMESCREEN:
				// do nothing ig
				break;
			case CHAMBERVIEW:
				Room currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'),
						Run3DMaze.player.getCoordinate('X'), Run3DMaze.player.getCoordinate('Y'));
				// System.out.println("pressed");
				// System.out.println("dir: "+ dir);
				// System.out.println("x" + e.getX());
				// System.out.println("y" + e.getY());
				h = Run3DMaze.height;
				w = Run3DMaze.width;
				xScale = w / 1000.0;
				yScale = h / 700.0;
				// 120, 450, 150, 90
				if (e.getX() >= (int) (120 * xScale) && e.getX() <= (int) (270 * xScale)
						&& e.getY() >= (int) (450 * yScale) && e.getY() <= (int) (540 * yScale)) {
					GamePanel.timeCounter = 0;
					dir = 0;
					for (Shapes3D i : currentRoom.ceilingAndFloor) {
						i.setDir(0);
					}
					for (Shapes3D i : currentRoom.walls) {
						i.setDir(0);
					}
					for (Shapes3D i : currentRoom.doors) {
						i.setDir(0);
					}
					Run3DMaze.player.setDirection(getLeftDirection(Run3DMaze.player.getDirection()));
					currentRoom.printDoors();
					// System.out.println(Run3DMaze.player.getDirection());
					GamePanel.timeCounter = 0;
					Run3DMaze.clicked = true;
					// 730, 450, 150, 90,
					// L
				} else if (e.getX() >= (int) (730 * xScale) && e.getX() <= (int) (880 * xScale)
						&& e.getY() >= (int) (450 * yScale) && e.getY() <= (int) (540 * yScale)) {
					GamePanel.timeCounter = 0;
					dir = 1;
					for (Shapes3D i : currentRoom.ceilingAndFloor) {
						i.setDir(1);
					}
					for (Shapes3D i : currentRoom.walls) {
						i.setDir(1);
					}
					for (Shapes3D i : currentRoom.doors) {
						i.setDir(1);
					}
					Run3DMaze.player.setDirection(getRightDirection(Run3DMaze.player.getDirection()));
					// System.out.println(Run3DMaze.player.getDirection());
					GamePanel.timeCounter = 0;
					Run3DMaze.clicked = true;
					// 450, 515, 100, 60
					// R
				} else if (e.getX() >= (int) (450 * xScale) && e.getX() <= (int) (550 * xScale)
						&& e.getY() >= (int) (430 * yScale) && e.getY() <= (int) (490 * yScale)
						&& currentRoom.getDirection(Run3DMaze.player.getDirection())) {
					GamePanel.timeCounter = 0;
					for (Shapes3D i : currentRoom.ceilingAndFloor) {
						i.setDir(2);
					}
					for (Shapes3D i : currentRoom.walls) {
						i.setDir(2);
					}
					for (Shapes3D i : currentRoom.doors) {
						i.setDir(2);
					}
					Run3DMaze.nextRoom = true;
					// System.out.println(Run3DMaze.player.getDirection());
					// Run3DMaze.player.movePlayer(Run3DMaze.player.getDirection());
					GamePanel.timeCounter = 0;
					Run3DMaze.clicked = true;
					// F

				} else if (e.getX() >= (int) (465 * xScale) && e.getX() <= (int) (535 * xScale)
						&& e.getY() >= (int) (130 * yScale) && e.getY() <= (int) (230 * yScale)
						&& currentRoom.getDirection('U')) {
					GamePanel.timeCounter = 0;
					for (Shapes3D i : currentRoom.ceilingAndFloor) {
						i.setDir(3);
					}
					for (Shapes3D i : currentRoom.walls) {
						i.setDir(3);
					}
					for (Shapes3D i : currentRoom.doors) {
						i.setDir(3);
					}
					Run3DMaze.nextRoom = true;
					Run3DMaze.up = true;
					// System.out.println(Run3DMaze.player.getDirection());
					GamePanel.timeCounter = 0;
					Run3DMaze.clicked = true;
					// U

				} else if (e.getX() >= (465 * xScale) && e.getX() < (int) (535 * xScale)
						&& e.getY() > (int) (530 * yScale) && e.getY() < (int) (630 * yScale)
						&& currentRoom.getDirection('D')) {
					GamePanel.timeCounter = 0;
					for (Shapes3D i : currentRoom.ceilingAndFloor) {
						i.setDir(4);
					}
					for (Shapes3D i : currentRoom.walls) {
						i.setDir(4);
					}
					for (Shapes3D i : currentRoom.doors) {
						i.setDir(4);
					}
					Run3DMaze.nextRoom = true;
					Run3DMaze.down = true;
					// System.out.println(Run3DMaze.player.getDirection());
					GamePanel.timeCounter = 0;
					Run3DMaze.clicked = true;
					// D
				}

				// check coordinates of click
				// call move direction method of maze class
				// trigger animation by setting timer counter back to 0
				break;
			case MAPVIEW:

				break;
			case LEADERBOARD:

				break;
			}
		}

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public char getLeftDirection(char direction) {
		int index = 0;
		if (direction == 'N') {
			index = 0;
		} else if (direction == 'E') {
			index = 1;
		} else if (direction == 'S') {
			index = 2;
		} else {
			index = 3;
		}

		if (index > 0) {
			return directions[index - 1];
		} else {
			return directions[3];
		}
	}

	public char getRightDirection(char direction) {
		int index = 0;
		if (direction == 'N') {
			index = 0;
		} else if (direction == 'E') {
			index = 1;
		} else if (direction == 'S') {
			index = 2;
		} else {
			index = 3;
		}

		if (index < 3) {
			return directions[index + 1];
		} else {
			return directions[0];
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		state = Run3DMaze.state;
		if (GamePanel.timeCounter > 40) {
			Room currentRoom = Run3DMaze.maze.getRoom(Run3DMaze.player.getCoordinate('Z'),
					Run3DMaze.player.getCoordinate('X'), Run3DMaze.player.getCoordinate('Y'));
			// System.out.println("pressed");
			// System.out.println("dir: "+ dir);
			// System.out.println("x" + e.getX());
			// System.out.println("y" + e.getY());
			h = Run3DMaze.height;
			w = Run3DMaze.width;
			xScale = w / 1000.0;
			yScale = h / 700.0;

			System.out.println(e.getKeyCode());
			int code = e.getKeyCode();
			// TODO Auto-generated method stub
			switch (code) {
			case 37:
			case 65:
				if (state == Run3DMaze.mazeState.CHAMBERVIEW) {
					GamePanel.timeCounter = 0;
					dir = 0;
					for (Shapes3D i : currentRoom.ceilingAndFloor) {
						i.setDir(0);
					}
					for (Shapes3D i : currentRoom.walls) {
						i.setDir(0);
					}
					for (Shapes3D i : currentRoom.doors) {
						i.setDir(0);
					}
					Run3DMaze.player.setDirection(getLeftDirection(Run3DMaze.player.getDirection()));
					currentRoom.printDoors();
					// System.out.println(Run3DMaze.player.getDirection());
					GamePanel.timeCounter = 0;
					Run3DMaze.clicked = true;
				}
				break;
			case 39:
			case 68:
				if (state == Run3DMaze.mazeState.CHAMBERVIEW) {
					if (state == Run3DMaze.mazeState.CHAMBERVIEW) {
						GamePanel.timeCounter = 0;
						dir = 1;
						for (Shapes3D i : currentRoom.ceilingAndFloor) {
							i.setDir(1);
						}
						for (Shapes3D i : currentRoom.walls) {
							i.setDir(1);
						}
						for (Shapes3D i : currentRoom.doors) {
							i.setDir(1);
						}
						Run3DMaze.player.setDirection(getRightDirection(Run3DMaze.player.getDirection()));
						// System.out.println(Run3DMaze.player.getDirection());
						GamePanel.timeCounter = 0;
						Run3DMaze.clicked = true;
						// 450, 515, 100, 60
						// R
					}
				}
				break;
			case 32:
				if (state == Run3DMaze.mazeState.CHAMBERVIEW) {
					if (currentRoom.getDirection(Run3DMaze.player.getDirection())) {
						GamePanel.timeCounter = 0;
						for (Shapes3D i : currentRoom.ceilingAndFloor) {
							i.setDir(2);
						}
						for (Shapes3D i : currentRoom.walls) {
							i.setDir(2);
						}
						for (Shapes3D i : currentRoom.doors) {
							i.setDir(2);
						}
						Run3DMaze.nextRoom = true;
						// System.out.println(Run3DMaze.player.getDirection());
						// Run3DMaze.player.movePlayer(Run3DMaze.player.getDirection());
						GamePanel.timeCounter = 0;
						Run3DMaze.clicked = true;
						// F
					}
				}
				break;
			case 38:
			case 87:
				if (currentRoom.getDirection('U')) {
					GamePanel.timeCounter = 0;
					for (Shapes3D i : currentRoom.ceilingAndFloor) {
						i.setDir(3);
					}
					for (Shapes3D i : currentRoom.walls) {
						i.setDir(3);
					}
					for (Shapes3D i : currentRoom.doors) {
						i.setDir(3);
					}
					Run3DMaze.nextRoom = true;
					Run3DMaze.up = true;
					// System.out.println(Run3DMaze.player.getDirection());
					GamePanel.timeCounter = 0;
					Run3DMaze.clicked = true;
					// U
				}
				break;
			case 40:
			case 83:
				if (state == Run3DMaze.mazeState.CHAMBERVIEW) {
					if (currentRoom.getDirection('D')) {
						GamePanel.timeCounter = 0;
						for (Shapes3D i : currentRoom.ceilingAndFloor) {
							i.setDir(4);
						}
						for (Shapes3D i : currentRoom.walls) {
							i.setDir(4);
						}
						for (Shapes3D i : currentRoom.doors) {
							i.setDir(4);
						}
						Run3DMaze.nextRoom = true;
						Run3DMaze.down = true;
						// System.out.println(Run3DMaze.player.getDirection());
						GamePanel.timeCounter = 0;
						Run3DMaze.clicked = true;
						// D
					}
				}
				break;
			case 77:
				if (state == Run3DMaze.mazeState.CHAMBERVIEW) {
					Run3DMaze.runMapView();
					Run3DMaze.changeView.setText(Run3DMaze.header.getView());
				}
				break;
			case 67:
				if (state == Run3DMaze.mazeState.MAPVIEW) {
					Run3DMaze.runChamberView();
					Run3DMaze.changeView.setText(Run3DMaze.header.getView());
				}
				break;
			}
		}
	}
}
