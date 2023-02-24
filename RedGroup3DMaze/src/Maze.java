import java.util.ArrayList;
public class Maze {
	// Room at coordinates (2, 4, 1) = activeMaze[1][2][4]
	//COORDINATES WILL BE AS FOLLOWS: [level][x][y], or (level/z, x, y)
	
	private Room[][][] activeMaze;
	
	private int difficulty = 1;
	
	private int minMoves = 0;
	
	/*
	 * BaseMaze key: 
	 * Flat room: 'Z'
	 * Room with hatch: 'U'
	 * Room with trapdoor: 'D'
	 * Room with hatch and trapdoor: 'B'
	 * Door: 'T'
	 * Absolute Door: 'X'
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
		} while (false /* while minMoves does not fit */);
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
	
	public boolean pathFind(char[][][] baseMaze, int[] startCoords, int[] endCoords) {
		int startz = startCoords[0];
		int startx = startCoords[1];
		int starty = startCoords[2];
		
		int endz = endCoords[0];
		int endx = endCoords[1];
		int endy = endCoords[2];
		
		
		char[][][] floodingMaze = new char[baseMaze.length][baseMaze[0].length][baseMaze[0][0].length];
		
		for (int level=0; level<baseMaze.length; level++) {
			for (int x=0; x<baseMaze[0].length; x++) {
				for (int y=0; y<baseMaze[0][0].length; y++) {
					floodingMaze[level][x][y] = baseMaze[level][x][y];
					if(baseMaze[level][x][y] != 'F' && baseMaze[level][x][y] != 'A') {
						floodingMaze[level][x][y] = 'P';
					}
				}
			}
		}
		
		boolean pathsLeft = true;
		floodingMaze[startz][startx][starty] = 'R';
		while(pathsLeft) {
			pathsLeft = false;
			for (int level=0; level<floodingMaze.length; level++) {
				for (int x = 1; x<floodingMaze[0].length-1;x++) {
					for (int y = 1; y<floodingMaze[0][0].length-1;y++) {
						char[] dirs = {floodingMaze[level][x+1][y], floodingMaze[level][x-1][y], floodingMaze[level][x][y+1], floodingMaze[level][x][y-1]};
						int counter = 0;
						for(int i = 0; i<dirs.length; i++) {
							if(dirs[i] == 'R')
								counter++;
						}
						char currentBasePos = baseMaze[level][x][y];
						if (level < floodingMaze.length-1 && (currentBasePos == 'D' || currentBasePos == 'B' || baseMaze[level+1][x][y] == 'U' || baseMaze[level+1][x][y] == 'B') && floodingMaze[level+1][x][y] == 'R') {
							counter++;
						}
						if (level > 0 && (currentBasePos == 'U' || currentBasePos == 'B' || baseMaze[level-1][x][y] == 'D' || baseMaze[level-1][x][y] == 'B') && floodingMaze[level-1][x][y] == 'R') {
							counter++;
						}
						if(counter != 0 && floodingMaze[level][x][y] == 'P') {
							floodingMaze[level][x][y] = 'R';
							pathsLeft = true;
						}
					}
				}
			}
		}
		
		if(floodingMaze[startz][startx][starty] == 'R' && floodingMaze[endz][endx][endy] == 'R')
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
			aimMinMoves = (int)(Math.random()*2) + 5;
		} else if (difficulty == 2) {
			aimMinMoves = (int)(Math.random()*2) + 6;
		} else {
			aimMinMoves = (int)(Math.random()*2) + 7;
		}

		int movesPerLevel = aimMinMoves;

		int[] endC = this.createLevelPath(baseMaze[0], movesPerLevel, 1, 1);
		int endx = endC[0];
		int endy = endC[1];
//		endC = this.createLevelPath(baseMaze[1], movesPerLevel, 1, 1);
		System.out.println(movesPerLevel + " " + endC[1] + " " +endC[2]);
		
		
		
		for(int i = 1; i< baseMaze.length;i++) {
			endC = this.createLevelPath(baseMaze[i], movesPerLevel, endC[1], endC[2]);
//			System.out.println(i);
//			for(int k = 0; k<baseMaze[1].length;k++) {
//				for(int j = 0; j<baseMaze[1].length;j++) {
//					System.out.print(baseMaze[i][k][j] +" ");
//				}
//				System.out.println();
//			}
//			System.out.println("------------------------------");
		}
		return baseMaze;
	}
	
	private int[] createLevelPath(char[][] level, int givenMoves, int startX, int startY){
//		System.out.println("starting: " + startX + " " + startY);	
		char[][] levelCopy = new char[level.length][level.length];

		for(int i = 0; i<level.length;i++) {
			for(int j = 0; j<level.length;j++) {
				levelCopy[i][j] = level[i][j];
			}
		}
		boolean pathWorks = false;
		int endx = startX;
		int endy = startY;	
		int[] endCC = null;
		while(!pathWorks) {
			
			for(int i = 0; i<level.length;i++) {
				for(int j = 0; j<level.length;j++) {
					level[i][j] = levelCopy[i][j];
				}
			}
			
			
			for(int i = 0; i< givenMoves; i++) {
				int[] results = moveInDir(level, endx, endy);
				endx = results[0];
				endy = results[1];
				System.out.println(endx + " " + endy);
			}
			
			
			int[] startC = {0, startX, startY};
			int[] endC = {0, endx, endy};
			endCC = endC;
			pathWorks = pathFind(level, startC, endC);
			
//			for(int i = 0; i<level.length;i++) {
//				for(int j = 0; j<level.length;j++) {
//					System.out.print(level[i][j] +" ");
//				}
//				System.out.println();
//			}
//			System.out.println("------------------------------");
		}
		
		for(int i = 0; i<level.length; i++) {
			for(int j = 0; j<level.length;j++) {
				System.out.print(level[i][j] +" ");
			}
			System.out.println();
		}
		return endCC;
	}
	
	private int[] moveInDir(char[][] level, int endx, int endy) {
		
		
		char dir = generateDirection();
		boolean works = false;
		while(!works) {
//			System.out.println("dir1" + dir);
//			System.out.println("stuff" + endx +" " + endy);
			switch(dir) {
				case 'N':
					if(meetsConditions(endx, endy, level, 'N')) {
						level[endx][endy-1] = 'X';
						endy -= 2;
						works = true;
						break;
					}
				case 'S':
					if(meetsConditions(endx, endy, level, 'S')) {
						level[endx][endy+1] = 'X';
						endy += 2;
						works = true;
						break;
					}
				case 'E':
					if(meetsConditions(endx, endy, level, 'E')) {
						level[endx-1][endy] = 'X';
						endx -= 2;
						works = true;
						break;
					}
				case 'W':
					if(meetsConditions(endx, endy, level, 'W')) {
						level[endx+1][endy] = 'X';
						endx += 2;
						works = true;
						break;
					}else
						break;
			}
//			System.out.println(endx + " " + endy);
			dir = generateDirection();
		}
		
//		for(int i = 0; i<level.length; i++) {
//			for(int j = 0; j<level.length;j++) {
//				System.out.print(level[i][j] +" ");
//			}
//			System.out.println();
//		}
		int[] results = {endx, endy};
		return results;
	}
	
	private char generateDirection() {
		int x = (int)(Math.random()*100);
		if(x<25)
			return 'N';
		if(x<50 && x>=25)
			return 'S';
		if(x>=50 && x<75)
			return 'W';
		if(x >= 75)
			return 'E';
		return 'E';
	}
	
	private boolean meetsConditions(int currentX, int currentY, char level[][], char dir) {
		boolean[] backup = {true, true, true, true};
		int failCounter = 0;
		boolean meets = true;
		switch(dir) {
			case 'N':
				if(currentY > 2) {
					if(level[currentX][currentY-1] == 'X' || nextToStart(currentX, currentY-1, level)) {
						backup[0] = false;
						failCounter++;
						meets = false;
						break;
						}
				} else {
					failCounter++;
					meets = false;
					break;
				}
				break;
			case 'S':
				if(currentY < level[0].length-3) {
					if(level[currentX][currentY+1] == 'X' || nextToStart(currentX, currentY+1, level)) {
						backup[1] = false;
						failCounter++;
						meets = false;
						break;
						}
				} else {
					failCounter++;
//					System.out.println(currentY + "<" + (level[0].length-3));
					meets = false;
					break;
				}
				break;
			case 'W':
				if(currentX < level.length-3) {
					if(level[currentX+1][currentY] == 'X' || nextToStart(currentX+1, currentY, level)) {
						backup[2] = false;
						failCounter++;
						meets = false;
						break;
						}
				} else {
					failCounter++;
					meets = false;
					break;
				}
				break;
			case 'E':
				if(currentX>2) {
					if(level[currentX-1][currentY] == 'X' || nextToStart(currentX-1, currentY, level)){
						backup[3] = false;
						failCounter++;
						meets = false;
						break;
						}
				} else {
					failCounter++;
					meets = false;
					break;
				}
				break;
		}
//		for(int i = 0; i<level.length; i++) {
//			for(int j = 0; j<level.length;j++) {
//				System.out.print(level[i][j] +" ");
//			}
//			System.out.println();
//		}
//		System.out.println("----------------------------");

		return meets;
	}
	
	private boolean nextToStart(int x, int y, char currentLevel[][]) {
		boolean boolVal = false;
		if(x>0) {
			if(currentLevel[x-1][y] == 'V')
				boolVal = true;
		} if (x<currentLevel.length-1) {
			if(currentLevel[x+1][y] == 'V')
				boolVal = true;
		} if(y>0) {
			if(currentLevel[x][y-1] == 'V')
				boolVal = true;
		} if(y<currentLevel.length-1) {
			if(currentLevel[x][y+1] == 'V')
				boolVal = true;
		}
		return boolVal;
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
						{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
					},
					
					
					
					//technically looks like this instead:
//					A A A A A A A A A
//					A V F Z F Z F Z A
//					A F A F A F A F A
//					A Z F Z F Z F Z A
//					A F A F A F A F A
//					A Z F Z F Z F Z A
//					A F A F A F A F A
//					A Z F Z F Z F Z A
//					A A A A A A A A A
//					
//					
					{
						{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
					},
					{
						{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
						{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
						{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
					}
			};
		
		char[][] test2 = {
				{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
				{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
				{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
				{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
				{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
				{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
				{'A', 'F', 'A', 'F', 'A', 'F', 'A', 'F', 'A'},
				{'A', 'Z', 'F', 'Z', 'F', 'Z', 'F', 'Z', 'A'},
				{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
			};
		
		maze = new Maze(test);
//		
//		maze.setActiveMaze(test);
//		char[][][] baseMaze = new char[4][9][9];
//		maze.setBaseMazeAndWalls(baseMaze, new ArrayList<int[]>());
//		for (int level=0; level<baseMaze.length; level++) {
//			for (int x=0; x<baseMaze[0].length; x++) {
//				for (int y=0; y<baseMaze[0][0].length; y++) {
//					System.out.print(baseMaze[level][x][y] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("\n");
//		}
		
//		int[] x = maze.createLevelPath(test[0], 6, 1, 1);
//		maze.createLevelPath(test[1], 6, 1, 1);
//		System.out.println(maze.meetsConditions(1, 4, test[0], 'E'));
//		maze.moveInDir(test[0], 5, 3);
//		int[] x = maze.createLevelPath(test[2], 6, 1, 1);
//		System.out.println("this:" + x[0] + " " + x);
		maze.createPath(test);
	}
}