import java.util.ArrayList;
public class Maze {
	// Room at coordinates (2, 4, 1) = activeMaze[2][4][1]
	
	private Room[][][] activeMaze;
	
	private int difficulty;
	
	private int minMoves;
	
//	public Maze(int difficulty) {
//		this.difficulty = difficulty;
//		this.minMoves = populateMaze(difficulty);
//	}
	
	/*
	 * BaseMaze key: 
	 * Flat room: 'Z'
	 * Room with hatch: 'U'
	 * Room with trapdoor: 'D'
	 * Room with hatch and trapdoor: 'B'
	 * Door: 'T'
	 * Wall: 'F'
	 * Absolute wall: 'A'
	 */
	
	public Maze(char[][][] providedBaseMaze) {
		setActiveMaze(providedBaseMaze);
	}
	
	public Maze() {
		
	}
	
	private int populateMaze(int difficulty) {
		char[][][] baseMaze;
		ArrayList<int[]> walls = new ArrayList<int[]>();
		if (difficulty == 3) {
			baseMaze = new char[11][11][5];
		} else {
			baseMaze = new char[9][9][4];
		}
		setBaseMazeAndWalls(baseMaze, walls);
		
		
		
		
		return -1;
	}
	
	private void setBaseMazeAndWalls(char[][][] baseMaze, ArrayList<int[]> walls) {
		for (int x=0; x<baseMaze.length; x++) {
			for (int y=0; y<baseMaze[0].length; y++) {
				for (int z=0; z<baseMaze[0][0].length; z++) {
					if (x%2 == 1 && y%2 == 1) {
						baseMaze[x][y][z] = 'Z';
					} else if (x > 0 && x < baseMaze.length-1 && y > 0 && y < baseMaze[0].length-1 && (x%2 == 1 || y%2 == 1)) { 
						baseMaze[x][y][z] = 'F';
						walls.add(getCoords(x, y, z));
					} else {
						baseMaze[x][y][z] = 'A';
					}
				}
			}
		}
	}
	
	private int pathFind(char[][][] baseMaze, int[] startCoords, int[] endCoords) {
		return -1;
	}
	
	private void fillBaseMaze(char[][][] baseMaze, ArrayList<int[]> walls) {
		
		for (int i=0; i<walls.size(); i++) {
			int[] wallCoords = walls.get(i);
			int[] startCoords, endCoords;
			if (baseMaze[wallCoords[0]-1][wallCoords[1]][wallCoords[2]] == 'A' && baseMaze[wallCoords[0]+1][wallCoords[1]][wallCoords[2]] == 'A') {
				
			}
		}
		
	}
	
	
	
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
	
	public Room getRoom(int x, int y, int z) {
		Room room = new Room(getCoords(x, y, z), new boolean[] {
				activeMaze[x][y][z].getDirection('N'),
				activeMaze[x][y][z].getDirection('E'),
				activeMaze[x][y][z].getDirection('S'),
				activeMaze[x][y][z].getDirection('W'),
				activeMaze[x][y][z].getDirection('U'),
				activeMaze[x][y][z].getDirection('D'),
		});
		return room;
	}
	
	
	public int getDifficulty() {
		return difficulty;
	}
	
	
	public int getMinMoves() {
		return minMoves;
	}
	
	private void setActiveMaze(char[][][] baseMaze) { // Untested
//		activeMaze = new Room[(baseMaze.length-1)/2][(baseMaze[0].length-1)/2][baseMaze[0][0].length];
//		int[] coordinates = new int[3];
//		boolean[] directions = {false, false, false, false, false, false};
//		for (int z=0; z<activeMaze[0][0].length; z++) {
//			for (int y=0; y<activeMaze[0].length; y++) {
//				for (int x=0; x<activeMaze.length; x++) {
//					int indexX = (x*2)+1;
//					int indexY = (y*2)+1;
//					coordinates[0] = x;
//					coordinates[1] = y;
//					coordinates[2] = z;
//					if (baseMaze[indexX][indexY-1][z] == 'T') { // Checking north
//						directions[0] = true;
//					}
//					if (baseMaze[indexX+1][indexY][z] == 'T') { // Checking east
//						directions[1] = true;
//					}
//					if (baseMaze[indexX][indexY+1][z] == 'T') { // Checking south
//						directions[2] = true;
//					}
//					if (baseMaze[indexX-1][indexY][z] == 'T') { // Checking west
//						directions[3] = true;
//					}
//					if (baseMaze[indexX][indexY][z] == 'B') { // Checking up and down
//						directions[4] = true;
//						directions[5] = true;
//					} else if (baseMaze[indexX][indexY][z] == 'U') { // Checking up
//						directions[4] = true;
//					} else if (baseMaze[indexX][indexY][z] == 'D') { // Checking down
//						directions[5] = true;
//					}
//					activeMaze[x][y][z] = new Room(coordinates, directions);
//				}
//			}
//		}
		/*int roomCount = getMazeSize();
		activeMaze = new Room[roomCount][roomCount][roomCount];
		int counterx = 0;
		int countery = 0;
		int counterz = 0;
		for(int i = 0; i<baseMaze.length; i++) {
			for(int j = 0; j<baseMaze[0].length; j++) {
				for(int k = 0; k < baseMaze[0][0].length; k++) {
					if(baseMaze[i][j][k] != 'A' && (baseMaze[i][j][k] != ('T') && baseMaze[i][j][k] != 'F')) {
						System.out.println(baseMaze[i][j][k]);
						activeMaze[counterx][countery][counterz] = new Room(getCoords(i, j, k), getRoomDirections(baseMaze, i, j, k));
					} if(counterx < roomCount)
						counterx++;
					else if(counterx >= roomCount) {
						counterx=0;
						if(countery < roomCount)
							countery++;
						else if (countery >= roomCount) {
							countery=0;
							counterz++;
						}
					}
				}
			}
		}*/
		activeMaze = new Room[(baseMaze.length-1)/2][(baseMaze[0].length-1)/2][baseMaze[0][0].length];
		for(int z = 0; z<activeMaze[0][0].length; z++) {
			for(int y = 0; y<activeMaze[0].length; y++) {
				for(int x = 0; x < activeMaze.length; x++) {
					int baseX = (x*2)+1;
					int baseY = (y*2)+1;
					if(baseMaze[baseX][baseY][z] != 'A' && (baseMaze[baseX][baseY][z] != ('T') && baseMaze[baseX][baseY][z] != 'F')) {
						activeMaze[x][y][z] = new Room(getCoords(x, y, z), getRoomDirections(baseMaze, baseX, baseY, z));
					}
				}
			}
		}
	}
	
	public int getMazeSize() {
		switch (difficulty) {
		case 1: 
			return 4;
		case 2:
			return 4;
		case 3:
			return 5;
		default:return 4;
		}
	}
	
	public boolean[] getRoomDirections(char baseMaze[][][], int x, int y, int z){

		boolean[] result = {false, false, false, false, false, false};


		switch (baseMaze[x][y][z]) {
		case 'U':
			result[4] = true;
		case 'D':
			result[5] = true;
		case 'B':
			result[4] = true;
			result[5] = true;
		}
		if(baseMaze[x-1][y][z] == 'T') 
			result[3] = true;
		if(baseMaze[x+1][y][z] == 'T')
			result[1] = true;
		if(baseMaze[x][y-1][z] == 'T')
			result[0] = true;
		if(baseMaze[x][y+1][z] == 'T')
			result[2] = true;
		//n e s w u d
		return result;
	}
	
	private int[] getCoords(int x, int y, int z) {
		int[] result = {x, y, z};
		return result;
	}
	
	public static void main(String[] args) {
		// Basic testing
		Maze maze;
		char[][][] test = // 3x3x3 testing for now
			{
					{
						{'A', 'A', 'A', 'A', 'A', 'A', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'A', 'A', 'A', 'A', 'A', 'A'}
					},
					{
						{'A', 'A', 'A', 'A', 'A', 'A', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'A', 'A', 'A', 'A', 'A', 'A'}
					},
					{
						{'A', 'A', 'A', 'A', 'A', 'A', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'A', 'A', 'A', 'A', 'A', 'A'}
					}
			};
		maze = new Maze(test);
		
		
		maze.setActiveMaze(test);
	}
}
