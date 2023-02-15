public class Room {
	private boolean[] directions;
	private int[] coordinates;
	private boolean marked;
	/*
	 * Coordinates are stored: {X, Y, Z}
	 * Directions are stored: {N, E, S, W, U, D}
	 */
	public Room(int[] coordinates, boolean[] directions) {
		for (int i=0; i<coordinates.length; i++) {
			this.coordinates[i] = coordinates[i];
		}
		for (int i=0; i<directions.length; i++) {
			this.directions[i] = directions[i];
		}
	}
	public boolean getDirection(char direction) {
		if (direction == 'N') {
			return directions[0];
		} else if (direction == 'E') {
			return directions[1];
		} else if (direction == 'S') {
			return directions[2];
		} else if (direction == 'W') {
			return directions[3];
		} else if (direction == 'U') {
			return directions[4];
		} else if (direction == 'D') {
			return directions[5];
		}
		return false;
	}
	public int getCoordinate(char value) {
		if (value == 'X') {
			return coordinates[0];
		} else if (value == 'Y') {
			return coordinates[1];
		} else if (value == 'Z') {
			return coordinates[2];
		}
		return -1;
	}
	public void mark() {
		marked = true;
	}
	public void unMark() {
		marked = false;
	}
	public boolean isMarked() {
		return marked;
	}
}
// Testing