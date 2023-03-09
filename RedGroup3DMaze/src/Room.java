import java.awt.*;
import java.util.ArrayList;

public class Room {
	private boolean[] directions = new boolean[6];
	private int[] coordinates = new int[3];
	private boolean visited;
	private Color color;
	private int[] rgbValues = new int[3];
	
	 public Shapes3D[] walls = {
	    		new Wall3D(0, this),
	    		new Wall3D(1, this),
	    		new Wall3D(2, this),
	    		new Wall3D(3, this)
	 };
	 
	 public ArrayList<Shapes3D> doors = new ArrayList<Shapes3D>();
	 public ArrayList<Shapes3D> ceilingAndFloor = new ArrayList<Shapes3D>();
	 char direction; 
	
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
		
		for(int i=0; i<3; i++)
			rgbValues[i] = 128+(int)(Math.random()*103);
		color = new Color(rgbValues[0], rgbValues[1], rgbValues[2]);
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
	
	//called after the end of each animation sequence
	public void populateDoors() {
		direction = Run3DMaze.player.getDirection();
		if(direction == 'N') {
			if(getDirection('W'))
				doors.add(new Door3D(0));
			if(getDirection('N'))
				doors.add(new Door3D(1));
			if(getDirection('E'))
				doors.add(new Door3D(2));
			if(getDirection('S'))
				doors.add(new Door3D(3));
		} else if(direction == 'E') {
			if(getDirection('N'))
				doors.add(new Door3D(0));
			if(getDirection('E'))
				doors.add(new Door3D(1));
			if(getDirection('S'))
				doors.add(new Door3D(2));
			if(getDirection('W'))
				doors.add(new Door3D(3));
		} else if(direction == 'S') {
			if(getDirection('E'))
				doors.add(new Door3D(0));
			if(getDirection('S'))
				doors.add(new Door3D(1));
			if(getDirection('W'))
				doors.add(new Door3D(2));
			if(getDirection('N'))
				doors.add(new Door3D(3));
		} else if(direction == 'W') {
			if(getDirection('S'))
				doors.add(new Door3D(0));
			if(getDirection('W'))
				doors.add(new Door3D(1));
			if(getDirection('N'))
				doors.add(new Door3D(2));
			if(getDirection('E'))
				doors.add(new Door3D(3));
		}
	}
	
	public Color getColor() {
		return color;
	}
	
	public int[] getRGBValues() {
		return rgbValues;
	}
}