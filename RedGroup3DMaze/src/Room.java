import java.awt.*;
import java.util.ArrayList;

public class Room {
	private boolean[] directions = new boolean[6];
	private int[] coordinates = new int[3];
	private boolean visited;
	private Color color, colorDark;
	private GradientPaint gradient;
	private int[] rgbValues = new int[3];
	private char artDirection;
	private Color finalDoorColor = Color.green;
	
	 public Shapes3D[] walls = {
	    		new Wall3D(0, this),
	    		new Wall3D(1, this),
	    		new Wall3D(2, this),
	    		new Wall3D(3, this)
	 };
	 
	 public ArrayList<Shapes3D> doors = new ArrayList<Shapes3D>();
	 public ArrayList<Shapes3D> ceilingAndFloor = new ArrayList<Shapes3D>();
	
	/*
	 * Coordinates are stored: {Z, X, Y}
	 * Directions are stored: {N, E, S, W, U, D}
	 */
	public Room(int[] coordinates, boolean[] directions) {
		for (int i=0; i<coordinates.length; i++) {
			this.coordinates[i] = coordinates[i];
		}
		for (int i=0; i<directions.length; i++) {
			this.directions[i] = directions[i];
		}
		if((coordinates[1] == 0 && coordinates[2] == 0)&&coordinates[0] == 0) {
			visited = true;
		} else {
			visited = false;
		}
		int counter = 0;
		for(int i = 0; i<directions.length-2;i++) {
			if(!directions[i]) {
				artDirection = correspondingDir(i);
				counter++;
			}
		}
		if(counter <1)
			artDirection = 'Z';	
		
		for(int i=0; i<3; i++)
			rgbValues[i] = 128+(int)(Math.random()*103);
		
		color = new Color(rgbValues[0], rgbValues[1], rgbValues[2]);
		colorDark = new Color(rgbValues[0]-30, rgbValues[1]-30, rgbValues[2]-30);
		
		//gradient = new GradientPaint(100, 100, color, 100, 750, colorDark);
	}	
	
	public boolean getDirection(char direction) {
		switch(direction) {
		case 'N':
			return directions[0];
		case 'E':
			return directions[1];
		case 'S':
			return directions[2];
		case 'W':
			return directions[3];
		case 'U':
			return directions[4];
		case 'D':
			return directions[5];
		default:
			return directions[0];
		}
	}
	
	public int getCoordinate(char value) {
		if (value == 'Z') {
			return coordinates[0];
		} else if (value == 'X') {
			return coordinates[1];
		} else if (value == 'Y') {
			return coordinates[2];
		}
		return -1;
	}
	public boolean hasVisited() {
		boolean x = visited;
		return x;
	}
	public void setVisited(boolean newVal) {
		visited = newVal;
	}
	
	public void populateTrapDoorsAndHatch() {
		if(getDirection('D')) {
			ceilingAndFloor.add(new Trapdoor3D(0));
		}
		if(getDirection('U')) {
			ceilingAndFloor.add(new Hatch3D(0));
		}
	}
	
	//called after the end of each animation sequence
	public void resetDoors() {
		doors = new ArrayList<Shapes3D>();
	}
	public void resetCeilingandFloor() {
		ceilingAndFloor = new ArrayList<Shapes3D>();
	}
	
	public void resetWalls() {
		Shapes3D[] temp = {
	    		new Wall3D(0, this),
	    		new Wall3D(1, this),
	    		new Wall3D(2, this),
	    		new Wall3D(3, this)
		};
		walls = temp;
	}
	
	public void populateDoors() {
		char direction = Run3DMaze.player.getDirection();
		int randomNumber = (int) ((Math.random() * 5) + 1);
		if(direction == 'N') {
			if(getDirection('W')) {
				doors.add(new Door3D(0));
			} else if(getArtWall() == 'W') {
				doors.add(randomArt(randomNumber, 0));
			}
			if(getDirection('N')) {
				doors.add(new Door3D(1));
			} else if(getArtWall() == 'N') {
				doors.add(randomArt(randomNumber, 1));
			}
			if(getDirection('E')) {
				if(getCoordinate('X') == 4 && getCoordinate('Y') == 4 
						&& getCoordinate('Z') == 4 && Run3DMaze.maze.getMazeSize() == 5) {
					doors.add(new Door3D(2, finalDoorColor));
				} else if(getCoordinate('X') == 3 && getCoordinate('Y') == 3
						&& getCoordinate('Z') == 3 && Run3DMaze.maze.getMazeSize() == 4) {
					doors.add(new Door3D(2, finalDoorColor));
				} else {
					doors.add(new Door3D(2));
				}
			} else if(getArtWall() == 'E') {
				doors.add(randomArt(randomNumber, 2));
			}
			if(getDirection('S')) {
				doors.add(new Door3D(3));
			} else if(getArtWall() == 'S') {
				doors.add(randomArt(randomNumber, 3));
			}
		} else if(direction == 'E') {
			if(getDirection('N')) {
				doors.add(new Door3D(0));
			} else if(getArtWall() == 'N') {
				doors.add(randomArt(randomNumber, 0));
			}
			if(getDirection('E')) {
				if(getCoordinate('X') == 4 && getCoordinate('Y') == 4 
						&& getCoordinate('Z') == 4 && Run3DMaze.maze.getMazeSize() == 5) {
					doors.add(new Door3D(1, finalDoorColor));
				} else if(getCoordinate('X') == 3 && getCoordinate('Y') == 3
						&& getCoordinate('Z') == 3 && Run3DMaze.maze.getMazeSize() == 4) {
					doors.add(new Door3D(1, finalDoorColor));
				} else {
					doors.add(new Door3D(1));
				}
			} else if(getArtWall() == 'E') {
				doors.add(randomArt(randomNumber, 1));
			}
			if(getDirection('S')) {
				doors.add(new Door3D(2));
			} else if(getArtWall() == 'S') {
				doors.add(randomArt(randomNumber, 2));
			}
			if(getDirection('W')) {
				doors.add(new Door3D(3));
			} else if(getArtWall() == 'W') {
				doors.add(randomArt(randomNumber, 3));
			}
		} else if(direction == 'S') {
			if(getDirection('E')) {
				if(getCoordinate('X') == 4 && getCoordinate('Y') == 4 
						&& getCoordinate('Z') == 4 && Run3DMaze.maze.getMazeSize() == 5) {
					doors.add(new Door3D(0, finalDoorColor));
				} else if(getCoordinate('X') == 3 && getCoordinate('Y') == 3
						&& getCoordinate('Z') == 3 && Run3DMaze.maze.getMazeSize() == 4) {
					doors.add(new Door3D(0, finalDoorColor));
				} else {
					doors.add(new Door3D(0));
				}
			} else if(getArtWall() == 'E') {
				doors.add(randomArt(randomNumber, 0));
			}
			if(getDirection('S')) {
				doors.add(new Door3D(1));
			} else if(getArtWall() == 'S') {
				doors.add(randomArt(randomNumber, 1));
			}
			if(getDirection('W')) {
				doors.add(new Door3D(2));
			} else if(getArtWall() == 'W') {
				doors.add(randomArt(randomNumber, 2));
			}
			if(getDirection('N')) {
				doors.add(new Door3D(3));
			} else if(getArtWall() == 'N') {
				doors.add(randomArt(randomNumber, 3));
			}
		} else if(direction == 'W') {
			if(getDirection('S')) {
				doors.add(new Door3D(0));
			} else if(getArtWall() == 'S') {
				doors.add(randomArt(randomNumber, 0));
			}
			if(getDirection('W')) {
				doors.add(new Door3D(1));
			} else if(getArtWall() == 'W') {
				doors.add(randomArt(randomNumber, 1));
			}
			if(getDirection('N')) {
				doors.add(new Door3D(2));
			} else if(getArtWall() == 'N') {
				doors.add(randomArt(randomNumber, 2));
			}
			if(getDirection('E')) {
				if(getCoordinate('X') == 4 && getCoordinate('Y') == 4 
						&& getCoordinate('Z') == 4 && Run3DMaze.maze.getMazeSize() == 5) {
					doors.add(new Door3D(3, finalDoorColor));
				} else if(getCoordinate('X') == 3 && getCoordinate('Y') == 3
						&& getCoordinate('Z') == 3 && Run3DMaze.maze.getMazeSize() == 4) {
					doors.add(new Door3D(3, finalDoorColor));
				} else {
					doors.add(new Door3D(3));
				}
			} else if(getArtWall() == 'E') {
				doors.add(randomArt(randomNumber, 3));
			}
		}
	}
	
	public Art3D randomArt(int artType, int state) {
		if(artType == 1) {
			return new ArtV1(state);
		} else if(artType == 2) {
			return new ArtV2(state);
		} else if(artType == 3) {
			return new ArtV3(state);
		} else if(artType == 4) {
			return new ArtV4(state);
		} else if(artType == 5) {
			return new ArtV5(state);
		} else {
			return new ArtV1(state);
		}
	}

	public Color getColor() {
		return color;
	}
	
	public GradientPaint getGradient(int startX, int startY, int endX, int endY) {
		gradient = new GradientPaint(startX, startY, colorDark, endX, endY, colorDark);
		return gradient;
	}
	
	public char getArtWall() {
		return artDirection;
	}
	
	public int[] getRGBValues() {
		return rgbValues;
	}
	
	private char correspondingDir(int i) {
		char[] dirs = {'N', 'E', 'S', 'W'};
		return dirs[i];
	}
	
}
