import java.util.ArrayList;
public class Maze {
	// Room at coordinates (2, 4, 1) = activeMaze[1][2][4]
	//COORDINATES WILL BE AS FOLLOWS: [level][x][y], or (level/z, x, y)
	
	private Room[][][] activeMaze;
	
	private int difficulty = 0;
	
	private int minMoves = 0;
	
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
	
	public Maze(int difficulty) {
		this.difficulty = difficulty;
		// this.minMoves = populateMaze(difficulty);
	}
	
	private int populateMaze(int difficulty) {
		char[][][] baseMaze;
		ArrayList<int[]> walls = new ArrayList<int[]>();
		if (difficulty == 3) {
			baseMaze = new char[5][11][11];
		} else {
			baseMaze = new char[4][9][9];
		}
		do {
			setBaseMazeAndWalls(baseMaze, walls);
			createPath(baseMaze);
			fillBaseMaze(baseMaze, walls);
		} while (!fullMazePathFind(baseMaze));
		return -1;
	}
	
	private void setBaseMazeAndWalls(char[][][] baseMaze, ArrayList<int[]> walls) {
		for (int level=0; level<baseMaze.length; level++) {
			for (int x=0; x<baseMaze[0].length; x++) {
				for (int y=0; y<baseMaze[0][0].length; y++) {
					if (x%2 == 1 && y%2 == 1) {
						baseMaze[level][x][y] = 'Z';
					} else if (x > 0 && x < baseMaze[0].length-1 && y > 0 && y < baseMaze[0][0].length-1 && (x%2 == 1 || y%2 == 1)) { 
						baseMaze[level][x][y] = 'F';
						walls.add(getCoords(level, x, y));
					} else {
						baseMaze[level][x][y] = 'A';
					}
				}
			}
		}
	}
	
	private boolean pathFind(char[][][] baseMaze, int[] startCoords, int[] endCoords) {
		int startz = startCoords[0];
		int startx = startCoords[1];
		int starty = startCoords[2];
		
		int endz = endCoords[0];
		int endx = endCoords[1];
		int endy = endCoords[2];
		
		
		char[][] currentLevel = new char[baseMaze[0].length][baseMaze[0][0].length];
		
		for (int x=0; x<baseMaze[0].length; x++) {
			for (int y=0; y<baseMaze[0][0].length; y++) {
				currentLevel[x][y] = baseMaze[startz][x][y];
			}
		}
		
		for(int x = 0; x<currentLevel.length; x++) {
			for(int y = 0; y<currentLevel[0].length; y++) {
				if(baseMaze[startz][x][y] != 'F' && baseMaze[startz][x][y] != 'A') {
					currentLevel[x][y] = 'P';
				}
			}
		}
		boolean pathsLeft = true;
		currentLevel[startx][starty] = 'R';
		while(pathsLeft) {
			pathsLeft = false;
			for(int x = 1; x<currentLevel.length-1;x++) {
				for(int y = 1; y<currentLevel[0].length-1;y++) {
					char[] dirs = {currentLevel[x+1][y], currentLevel[x-1][y], currentLevel[x][y+1], currentLevel[x][y-1]};
					int counter = 0;
					for(int i = 0; i<4; i++) {
						if(dirs[i] == 'R')
							counter++;
					}
					if(counter != 0 && currentLevel[x][y] == 'P') {
						currentLevel[x][y] = 'R';
						pathsLeft = true;
					}
				}
			}
		}
		
		if(currentLevel[startx][starty] == 'R' && currentLevel[endx][endy] == 'R')
			return true;
		return false;
	}
	
	private boolean pathFind(char[][] baseMazeLevel, int[] startCoords, int[] endCoords) {
		char[][][] x = new char[1][baseMazeLevel.length][baseMazeLevel[0].length];
		x[0] = baseMazeLevel;
		return pathFind(x, startCoords, endCoords);
	}
	
	private char[][][] createPath(char[][][] baseMaze) {
		/*
		 * difficulty = 1: minMoves = 15 - 17
		 * difficulty = 2: minMoves = 18 - 20
		 * difficulty = 3: minMoves = 22 - 24
		 */
		int aimMinMoves;
		if (difficulty == 1) {
			aimMinMoves = 16;
		} else if (difficulty == 2) {
			aimMinMoves = 19;
		} else {
			aimMinMoves = 23;
		}
		int movesPerLevel = (int)(aimMinMoves / baseMaze.length);
		
		
		return baseMaze;
	}
	
	private char[][] createLevelPath(char[][][] baseMaze, int givenMoves){
		char[][] level = new char[baseMaze[0].length][baseMaze[0].length];
		boolean pathWorks = false;
		while(!pathWorks) {
			int startx;
			int starty; 
			int endx;
			int endy;
			if(((int)Math.random()*100000) < 50000) {
				startx = 1;
				starty = (((int)Math.random() * (level.length-1)/2) *2) +1;
			} else {
				starty = 1;
				startx = (((int)Math.random() * (level.length-1)/2) *2) +1;
			}
					
			for(int i = 0; i< givenMoves; i++) {
				
			}
			
			
			int[] startC = {0, startx, starty};
			int[] endC = {0, endx, endy};
			pathWorks = pathFind(level, startC, endC);
		}
		return level;
	}
	
	private void fillBaseMaze(char[][][] baseMaze, ArrayList<int[]> walls) {
		int wallsSize = walls.size();
		for (int i=0; i<wallsSize; i++) {
			int[] wallCoords = walls.remove((int)(Math.random()*walls.size()));
			int[] startCoords = new int[3];
			int[] endCoords = new int[3];
			startCoords[0] = wallCoords[0];
			endCoords[0] = wallCoords[0];
			if (baseMaze[wallCoords[0]][wallCoords[1]-1][wallCoords[2]] == 'A' && baseMaze[wallCoords[0]][wallCoords[1]+1][wallCoords[2]] == 'A') {
				startCoords[1] = wallCoords[1];
				startCoords[2] = wallCoords[2]-1;
				endCoords[1] = wallCoords[1];
				endCoords[2] = wallCoords[2]+1;
			} else {
				startCoords[1] = wallCoords[1]-1;
				startCoords[2] = wallCoords[2];
				endCoords[1] = wallCoords[1]+1;
				endCoords[2] = wallCoords[2];
			}
			if (pathFind(baseMaze, startCoords, endCoords)) {
				baseMaze[wallCoords[0]][wallCoords[1]][wallCoords[2]] = 'T';
			}
		}
		
	}
	
	private boolean fullMazePathFind(char[][][] baseMaze) {
		int[] startCoords = new int[3];
		int[] endCoords = new int[3];
		for (int level=0; level<baseMaze.length; level++) {
			// finding startCoords and endCoords
			// note: WILL THROW ERROR IF LEVEL DOESN'T HAVE BOTH A 'U' AND A 'D'
			startCoords[0] = level;
			endCoords[0] = level;
			for (int x=0; x<baseMaze[0].length; x++) {
				for (int y=0; y<baseMaze[0][0].length; y++) {
					if (baseMaze[level][x][y] == 'U' || baseMaze[level][x][y] == 'B') {
						startCoords[1] = x;
						startCoords[2] = y;
					}
					if (baseMaze[level][x][y] == 'D' || baseMaze[level][x][y] == 'B') {
						endCoords[1] = x;
						endCoords[2] = y;
					}
				}
			}
			if (!pathFind(baseMaze, startCoords, endCoords)) {
				return false;
			}
		}
		return true;
	}
	
	public Room getRoom(int level, int x, int y) {
		Room room = new Room(getCoords(level, x, y), new boolean[] {
				activeMaze[level][x][y].getDirection('N'),
				activeMaze[level][x][y].getDirection('E'),
				activeMaze[level][x][y].getDirection('S'),
				activeMaze[level][x][y].getDirection('W'),
				activeMaze[level][x][y].getDirection('U'),
				activeMaze[level][x][y].getDirection('D'),
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
		activeMaze = new Room[baseMaze.length][(baseMaze[0].length-1)/2][(baseMaze[0][0].length-1)/2];
		for(int level = 0; level<activeMaze.length; level++) {
			for(int x = 0; x<activeMaze[0].length; x++) {
				for(int y = 0; y < activeMaze[0][0].length; y++) {
					int baseX = (x*2)+1;
					int baseY = (y*2)+1;
					if(baseMaze[level][baseX][baseY] != 'A' && (baseMaze[level][baseX][baseY] != ('T') && baseMaze[level][baseX][baseY] != 'F')) {
						activeMaze[level][x][y] = new Room(getCoords(level, x, y), getRoomDirections(baseMaze, level, baseX, baseY));
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
	
	public boolean[] getRoomDirections(char baseMaze[][][], int level, int x, int y){

		boolean[] result = {false, false, false, false, false, false};


		switch (baseMaze[level][x][y]) {
		case 'U':
			result[4] = true;
		case 'D':
			result[5] = true;
		case 'B':
			result[4] = true;
			result[5] = true;
		}
		if(baseMaze[level][x-1][y] == 'T') 
			result[3] = true;
		if(baseMaze[level][x+1][y] == 'T')
			result[1] = true;
		if(baseMaze[level][x][y-1] == 'T')
			result[0] = true;
		if(baseMaze[level][x][y+1] == 'T')
			result[2] = true;
		//n e s w u d
		return result;
	}
	
	private int[] getCoords(int level, int x, int y) {
		int[] result = {level, x, y};
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
		char[][][] baseMaze = new char[4][9][9];
		maze.setBaseMazeAndWalls(baseMaze, new ArrayList<int[]>());
		for (int level=0; level<baseMaze.length; level++) {
			for (int x=0; x<baseMaze[0].length; x++) {
				for (int y=0; y<baseMaze[0][0].length; y++) {
					System.out.print(baseMaze[level][x][y] + " ");
				}
				System.out.println();
			}
			System.out.println("\n");
		}
	}
}
