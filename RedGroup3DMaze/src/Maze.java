public class Maze {
	// Room at coordinates (2, 4, 1) = activeMaze[2][4][1]
	private Room[][][] activeMaze;
	private int difficulty;
	private int minMoves;
	/*public Maze(int difficulty) {
		this.difficulty = difficulty;
		this.minMoves = populateMaze(difficulty);
	}*/
	public Maze(char[][][] providedBaseMaze) {
		setActiveMaze(providedBaseMaze);
	}
	/*public int populateMaze(int difficulty) {
		
	}*/
	/*public Room[][][] getActiveMaze() {
		Room[][][] returnedMaze = new Room[activeMaze.length][activeMaze[0].length][activeMaze[0][0].length];
		for (int i=0; i<activeMaze.length; i++) {
			for (int j=0; j<activeMaze[0].length; j++) {
				for (int k=0; k<activeMaze[0][0].length; k++) {
					Room room = new Room({)
				}
			}
		}
	}*/
	public int getDifficulty() {
		return difficulty;
	}
	public int getMinMoves() {
		return minMoves;
	}
	private void setActiveMaze(char[][][] baseMaze) { // Untested
		activeMaze = new Room[(baseMaze.length-1)/2][(baseMaze[0].length-1)/2][baseMaze[0][0].length];
		int[] coordinates = new int[3];
		boolean[] directions = {false, false, false, false, false, false};
		for (int z=0; z<activeMaze[0][0].length; z++) {
			for (int y=0; y<activeMaze[0].length; y++) {
				for (int x=0; x<activeMaze.length; x++) {
					int indexX = (x*2)+1;
					int indexY = (y*2)+1;
					if (baseMaze[indexX][indexY-1][z] == 'T') { // Checking north
						directions[0] = true;
					}
					if (baseMaze[indexX+1][indexY][z] == 'T') { // Checking east
						directions[1] = true;
					}
					if (baseMaze[indexX][indexY+1][z] == 'T') { // Checking south
						directions[2] = true;
					}
					if (baseMaze[indexX-1][indexY][z] == 'T') { // Checking west
						directions[3] = true;
					}
					if (baseMaze[indexX][indexY][z] == 'B') { // Checking up and down
						directions[4] = true;
						directions[5] = true;
					} else if (baseMaze[indexX][indexY][z] == 'U') { // Checking up
						directions[4] = true;
					} else if (baseMaze[indexX][indexY][z] == 'D') { // Checking down
						directions[5] = true;
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		// Testing
		
	}
}