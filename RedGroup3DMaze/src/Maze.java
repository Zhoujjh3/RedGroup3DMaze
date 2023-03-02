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
		this.minMoves = pathFind(providedBaseMaze, getCoords(0, 1, 1), getCoords(providedBaseMaze.length-1, providedBaseMaze[0].length-2, providedBaseMaze[0][0].length-2));
		setActiveMaze(providedBaseMaze);
	}
	
	public Maze(int difficulty) {
		this.difficulty = difficulty;
		this.minMoves = populateMaze();
	}
	
	public Room getRoom(int level, int x, int y) {
		return activeMaze[level][x][y];
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public int getMinMoves() {
		return minMoves;
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
	
	private int populateMaze() {
		char[][][] baseMaze;
		ArrayList<int[]> walls = new ArrayList<int[]>();
		int minMoves;
		if (difficulty == 3) {
			baseMaze = new char[5][11][11];
		} else {
			baseMaze = new char[4][9][9];
		}
		setBaseMazeAndWalls(baseMaze, walls);
		createPath(baseMaze);
		fillBaseMaze(baseMaze, walls);
		minMoves = pathFind(baseMaze, getCoords(0, 1, 1), getCoords(baseMaze.length-1, baseMaze[0].length-2, baseMaze[0][0].length-2));
		setActiveMaze(baseMaze);
		return minMoves;
	}

	
	private void setBaseMazeAndWalls(char[][][] baseMaze, ArrayList<int[]> walls) {
		ArrayList<int[]> floors = new ArrayList<int[]>();
		for (int level=0; level<baseMaze.length; level++) {
			for (int x=0; x<baseMaze[0].length; x++) {
				for (int y=0; y<baseMaze[0][0].length; y++) {
					if (x%2 == 1 && y%2 == 1) {
						baseMaze[level][x][y] = 'Z';
						floors.add(getCoords(level, x, y));
					} else if (x > 0 && x < baseMaze[0].length-1 && y > 0 && y < baseMaze[0][0].length-1 && (x%2 == 1 || y%2 == 1)) { 
						baseMaze[level][x][y] = 'F';
						walls.add(getCoords(level, x, y));
					} else {
						baseMaze[level][x][y] = 'A';
					}
				}
			}
			if (level < baseMaze.length-1) {
				int numOfDs;
				if (difficulty == 1) { // numOfDs should be 3 times the intended num of Ds because each has about a 1/3 chance of working with algorithm
					numOfDs = 3;
				} else { // difficulty 2 and 3 use the same numOfDs
					numOfDs = 6;
				}
				for (int i=0; i<numOfDs; i++) {
					walls.add(floors.remove((int)(Math.random()*floors.size())));
				}
			}
		}
	}
	
	private int pathFind(char[][][] baseMaze, int[] startCoords, int[] endCoords) {
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
					if(baseMaze[level][x][y] != 'F' && baseMaze[level][x][y] != 'A') {
						floodingMaze[level][x][y] = 'P';
					} else {
						floodingMaze[level][x][y] = baseMaze[level][x][y];
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
						ArrayList<int[]> dirs = getDirsAroundCoords(baseMaze, getCoords(level, x, y));
						int counter = 0;
						for(int i = 0; i<dirs.size(); i++) {
							char charAtCoord = floodingMaze[dirs.get(i)[0]][dirs.get(i)[1]][dirs.get(i)[2]];
							if(charAtCoord == 'R')
								counter++;
						}
						
						if(counter != 0 && floodingMaze[level][x][y] == 'P') {
							floodingMaze[level][x][y] = 'R';
							pathsLeft = true;
						}
						if (deadEnd(floodingMaze, getCoords(level, x, y), dirs, startCoords, endCoords)) {
							floodingMaze[level][x][y] = 'A';
							pathsLeft = true;
						}
					}
				}
			}
		}
		
		if(floodingMaze[startz][startx][starty] == 'R' && floodingMaze[endz][endx][endy] == 'R')
			return findRPath(floodingMaze, baseMaze, startCoords, endCoords, startCoords, 50) / 2;
		return -1;
	}
	
	private int pathFind(char[][] baseMazeLevel, int[] startCoords, int[] endCoords) {
		char[][][] x = new char[1][baseMazeLevel.length][baseMazeLevel[0].length];
		x[0] = baseMazeLevel;
		return pathFind(x, startCoords, endCoords);
	}
	
	private boolean deadEnd(char[][][] floodingMaze, int[] coords, ArrayList<int[]> dirs, int[] startCoords, int[] endCoords) {
		char charAtCoord = floodingMaze[coords[0]][coords[1]][coords[2]];
		int surroundingR = 0;
		for (int i=0; i<dirs.size(); i++) {
			char charAtDirCoord = floodingMaze[dirs.get(i)[0]][dirs.get(i)[1]][dirs.get(i)[2]];
			if (charAtDirCoord == 'R') {
				surroundingR += 1;
			}
			if ((charAtCoord != 'P' && charAtCoord != 'R') || surroundingR > 1 || (charAtDirCoord != 'A' && charAtDirCoord != 'F' && charAtDirCoord != 'R')
					|| coordsMatch(coords, startCoords) || coordsMatch(coords, endCoords)) {
				return false;
			}
		}
		return true;
	}
	
	private int findRPath(char[][][] floodingMaze, char[][][] baseMaze, int[] startCoords, int[] endCoords, int[] previousCoords, int maxDepth) {
		// For efficiency, put maxDepth as just over maximum moves * 2
		if (coordsMatch(startCoords, endCoords) || maxDepth < 1) {
			return 0;
		} else {
			ArrayList<int[]> dirs = getDirsAroundCoords(baseMaze, startCoords);
			int minMoves = Integer.MAX_VALUE;
			int dirMoves = minMoves;
			for (int i=0; i<dirs.size(); i++) {
				int[] dirCoords = dirs.get(i);
				if (i < 4) {
					if (floodingMaze[dirCoords[0]][dirCoords[1]][dirCoords[2]] == 'R' && !coordsMatch(dirCoords, previousCoords)) {
						dirMoves = 1 + findRPath(floodingMaze, baseMaze, dirCoords, endCoords, startCoords, maxDepth-1);
					}
				} else {
					if (floodingMaze[dirCoords[0]][dirCoords[1]][dirCoords[2]] == 'R' && canTravelVertically(baseMaze, startCoords, dirCoords) && !coordsMatch(dirCoords, previousCoords)) {
						dirMoves = 2 + findRPath(floodingMaze, baseMaze, dirCoords, endCoords, startCoords, maxDepth-1);
					}
				}
				if (dirMoves < minMoves) {
					minMoves = dirMoves;
				}
			}
			return minMoves;
		}
	}
		
	private char[][][] createPath(char[][][] baseMaze) {
		/*
		 * difficulty = 1: minMoves = 15 - 17
		 * difficulty = 2: minMoves = 18 - 20
		 * difficulty = 3: minMoves = 22 - 24
		 */
		int aimMinMoves;
		if (difficulty == 1) {
			aimMinMoves = (int)(Math.random()*2) + 15 - 3;
		} else if (difficulty == 2) {
			aimMinMoves = (int)(Math.random()*2) + 18 - 3;
			System.out.println(aimMinMoves);
		} else {
			aimMinMoves = (int)(Math.random()*2) + 22 - 4;
			System.out.println(baseMaze.length);
		}
	
		int movesPerLevel = aimMinMoves/baseMaze.length;
		System.out.println(movesPerLevel);
	
		int[] endC = this.createLevelPath(baseMaze[0], movesPerLevel, 1, 1, "first");
		aimMinMoves -= movesPerLevel;
		int endx = endC[0];
		int endy = endC[1];
	//	endC = this.createLevelPath(baseMaze[1], movesPerLevel, 1, 1);
		System.out.println(movesPerLevel + " " + endC[1] + " " +endC[2]);
		
		
		
		for(int i = 1; i< baseMaze.length;i++) {
			if(i < baseMaze.length-1) {
				endC = this.createLevelPath(baseMaze[i], movesPerLevel, endC[1], endC[2], "middle");
				aimMinMoves -= movesPerLevel;
			}
			else
				endC = this.createLevelPath(baseMaze[i], aimMinMoves, endC[1], endC[2], "end");

	//		System.out.println(i);
	//		for(int k = 0; k<baseMaze[1].length;k++) {
	//			for(int j = 0; j<baseMaze[1].length;j++) {
	//				System.out.print(baseMaze[i][k][j] +" ");
	//			}
	//			System.out.println();
	//		}
	//		System.out.println("------------------------------");
		}
		return baseMaze;
	}

	private int[] createLevelPath(char[][] level, int givenMoves, int startX, int startY, String levelType){
	//	System.out.println("starting: " + startX + " " + startY);	
		char[][] levelCopy = new char[level.length][level.length];
		
		ArrayList<Integer> xTrace = new ArrayList<Integer>();
		ArrayList<Integer> yTrace = new ArrayList<Integer>();
	
	
		for(int i = 0; i<level.length;i++) {
			for(int j = 0; j<level.length;j++) {
				levelCopy[i][j] = level[i][j];
			}
		}
		boolean pathWorks = false;
		int endx = startX;
		int endy = startY;
		xTrace.add(endx);
		yTrace.add(endy);
		int[] endCC = null;
		int counter = 0;
		while(!pathWorks) {
			boolean failure = false;
			for(int i = 0; i<level.length;i++) {
				for(int j = 0; j<level.length;j++) {
					level[i][j] = levelCopy[i][j];
				}
			}
			
			
			for(int i = 0; i< givenMoves+(counter/1000); i++) {
				int[] results = moveInDir(level, endx, endy, xTrace, yTrace);
				if(results[0] == level.length-1 && results[1] == level.length-1) {
					break;
				}
				if(results[0] == -1 && results[1] == -1) {
					failure= true;
					break;
				} else {
					endx = results[0];
					endy = results[1];
					xTrace.add(endx);
					yTrace.add(endy);
//					System.out.println(endx + " " + endy);
				}
			}
			if(levelType.equals("end")) {
				level[startX][startY] = 'U';
			} if(levelType.equals("middle")) {
				level[startX][startY] = 'U';
				level[endx][endy] = 'D';
			} if(levelType.equals("first")) {
				level[endx][endy] = 'D';
			}
						
			
			if((endx == level.length-2 && endy == level.length-2) && levelType == "middle")
				failure = true;
			if(!(endx == level.length-2 && endy == level.length-2) && levelType == "end")
				failure = true;

			if(!failure) {
				int[] startC = {0, startX, startY};
				int[] endC = {0, endx, endy};
				endCC = endC;
				pathWorks = pathFind(level, startC, endC) >= 0;
			} else
				pathWorks = false;
//			for(int i = 0; i<level.length;i++) {
//				for(int j = 0; j<level.length;j++) {
//					System.out.print(level[i][j] +" ");
//				}
//				System.out.println();
//			}
//			System.out.println("-----------" + levelType + "-------------------");
////			
			yTrace.clear();
			xTrace.clear();
			counter++;
		}
		
		for(int i = 0; i<level.length; i++) {
			for(int j = 0; j<level.length;j++) {
				System.out.print(level[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println(xTrace.size());
		System.out.println("---------------------");
		
		return endCC;
	}
	
	private int[] moveInDir(char[][] level, int endx, int endy, ArrayList<Integer> xTrace, ArrayList<Integer> yTrace) {
		
		
		char dir = generateDirection();
		boolean works = false;
		int testCounter = 0;
		while(!works && testCounter <10) {
			testCounter++;
			if(testCounter ==9)
				System.out.println("RIGHT HERE RNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
	//		System.out.println("stuff" + endx +" " + endy);
			switch(dir) {
				case 'N':
					if(meetsConditions(endx, endy, level, 'N', xTrace, yTrace)) {
						level[endx][endy-1] = 'T';
						endy -= 2;
						works = true;
						break;
					}
				case 'S':
					if(meetsConditions(endx, endy, level, 'S', xTrace, yTrace)) {
						level[endx][endy+1] = 'T';
						endy += 2;
						works = true;
						break;
					}
				case 'E':
					if(meetsConditions(endx, endy, level, 'E', xTrace, yTrace)) {
						level[endx-1][endy] = 'T';
						endx -= 2;
						works = true;
						break;
					}
				case 'W':
					if(meetsConditions(endx, endy, level, 'W', xTrace, yTrace)) {
						level[endx+1][endy] = 'T';
						endx += 2;
						works = true;
						break;
					}else
						break;
			}
	//		System.out.println(endx + " " + endy);
			dir = generateDirection();
		}
		
//		for(int i = 0; i<level.length; i++) {
//			for(int j = 0; j<level.length;j++) {
//				System.out.print(level[i][j] +" ");
//			}
//			System.out.println();
//		}
//		System.out.println("-------------------");
		int[] results = {0, 0};
		if(testCounter == 9) {
			results[0] = -1;
			results[1] = -1;
		} else {
			results[0] = endx;
			results[1] = endy;
		}
		
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
	
	private boolean meetsConditions(int currentX, int currentY, char level[][], char dir, ArrayList<Integer> xTrace, ArrayList<Integer> yTrace) {
		boolean[] backup = {true, true, true, true};
		int failCounter = 0;
		boolean meets = true;
//		System.out.println("HEREEEEE");
		switch(dir) {
			case 'N':
				if(currentY > 2) {
//					System.out.println(alreadyBeenOn(xTrace, yTrace, currentX, currentY-1));
					if(alreadyBeenOn(xTrace, yTrace, currentX, currentY-2)|| nextToStart(currentX, currentY-1, level)) {
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
//					System.out.println(alreadyBeenOn(xTrace, yTrace, currentX, currentY+1));
					if(alreadyBeenOn(xTrace, yTrace, currentX, currentY+2)|| nextToStart(currentX, currentY+1, level)) {
						backup[1] = false;
						failCounter++;
						meets = false;
						break;
						}
				} else {
					failCounter++;
	//				System.out.println(currentY + "<" + (level[0].length-3));
					meets = false;
					break;
				}
				break;
			case 'W':
				if(currentX < level.length-3) {
//					System.out.println(alreadyBeenOn(xTrace, yTrace, currentX+1, currentY));
					if(alreadyBeenOn(xTrace, yTrace, currentX+2, currentY)|| nextToStart(currentX+1, currentY, level)) {
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
//					System.out.println(alreadyBeenOn(xTrace, yTrace, currentX-1, currentY));
					if(alreadyBeenOn(xTrace, yTrace, currentX-2, currentY)|| nextToStart(currentX-1, currentY, level)){
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

	private boolean alreadyBeenOn(ArrayList<Integer> xTrace, ArrayList<Integer> yTrace, int x, int y) {
		boolean result = false;
//		System.out.println("X:" + x + " Y:" + y );
		for(int i = 0; i<xTrace.size(); i++) {
//			System.out.print(xTrace.get(i) + " " + yTrace.get(i) + " ||||");
			if(xTrace.get(i).equals(x) && yTrace.get(i).equals(y)) {
				result = true;
			}
		}
//		System.out.println();
		return result;
	}
	
	
	private void fillBaseMaze(char[][][] baseMaze, ArrayList<int[]> walls) {
		int wallsSize = walls.size();
		for (int i=0; i<wallsSize; i++) {
			int[] wallCoords = walls.remove((int)(Math.random()*walls.size()));
			int[] startCoords = new int[3];
			int[] endCoords = new int[3];
			char charAtCoord = baseMaze[wallCoords[0]][wallCoords[1]][wallCoords[2]];
			
			if (charAtCoord == 'F') {
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
				if (pathFind(baseMaze, startCoords, endCoords) < 0) {
					baseMaze[wallCoords[0]][wallCoords[1]][wallCoords[2]] = 'T';
				}
			} else if (charAtCoord == 'Z' || charAtCoord == 'U' || charAtCoord == 'D') {
				startCoords[0] = wallCoords[0];
				endCoords[0] = wallCoords[0]+1;
				startCoords[1] = wallCoords[1];
				endCoords[1] = wallCoords[1];
				startCoords[2] = wallCoords[2];
				endCoords[2] = wallCoords[2];
				if (pathFind(baseMaze, startCoords, endCoords) < 0) {
					if (charAtCoord == 'U') {
						baseMaze[wallCoords[0]][wallCoords[1]][wallCoords[2]] = 'B';
					} else {
						baseMaze[wallCoords[0]][wallCoords[1]][wallCoords[2]] = 'D';
					}
					char charUnderCoord = baseMaze[endCoords[0]][endCoords[1]][endCoords[2]];
					if (charUnderCoord == 'D') {
						baseMaze[endCoords[0]][endCoords[1]][endCoords[2]] = 'B';
					} else {
						baseMaze[endCoords[0]][endCoords[1]][endCoords[2]] = 'U';
					}
				}
			}
		}
	}
	
	private void setActiveMaze(char[][][] baseMaze) {
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
	
	private boolean[] getRoomDirections(char baseMaze[][][], int level, int x, int y){
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
	
	private boolean coordsMatch(int[] coords1, int[] coords2) {
		if (coords1.length != coords2.length) {
			return false;
		}
		for (int i=0; i<coords1.length; i++) {
			if (coords1[i] != coords2[i]) {
				return false;
			}
		}
		return true;
	}
	
	private boolean movesMatchWithDifficulty(int moves) {
		return ((difficulty == 1 && moves >= 15 && moves <= 17) 
				|| (difficulty == 2 && moves >= 18 && moves <= 20) 
				|| (difficulty == 3 && moves >= 22 && moves <= 24));
	}
	
	private ArrayList<int[]> getDirsAroundCoords(char[][][] baseMaze, int[] coords) {
		int startz = coords[0];
		int startx = coords[1];
		int starty = coords[2];
		ArrayList<int[]> dirs = new ArrayList<int[]>();
		dirs.add(getCoords(startz, startx+1, starty));
		dirs.add(getCoords(startz, startx-1, starty));
		dirs.add(getCoords(startz, startx, starty+1));
		dirs.add(getCoords(startz, startx, starty-1));
		char currentBasePos = baseMaze[startz][startx][starty];
		if (startz < baseMaze.length-1 && (currentBasePos == 'D' || currentBasePos == 'B' || baseMaze[startz+1][startx][starty] == 'U' || baseMaze[startz+1][startx][starty] == 'B')) {
			dirs.add(getCoords(startz+1, startx, starty));
		}
		if (startz > 0 && (currentBasePos == 'U' || currentBasePos == 'B' || baseMaze[startz-1][startx][starty] == 'D' || baseMaze[startz-1][startx][starty] == 'B')) {
			dirs.add(getCoords(startz-1, startx, starty));
		}
		return dirs;
	}
	
	private boolean canTravelVertically(char[][][] baseMaze, int[] startCoords, int[] endCoords) {
		char startChar = baseMaze[startCoords[0]][startCoords[1]][startCoords[2]];
		char endChar = baseMaze[endCoords[0]][endCoords[1]][endCoords[2]];
		if (Math.abs(startCoords[0]-endCoords[0]) == 1 && startCoords[1] == endCoords[1] && startCoords[2] == endCoords[2]
				&& (startChar == 'B' || endChar == 'B' || (startCoords[0] > endCoords[0] && (startChar == 'U' || endChar == 'D')) 
				|| (startCoords[0] < endCoords[0] && (startChar == 'D' || endChar == 'U')))) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		// Basic testing
		Maze maze;
		char[][][] test =
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
		
		Maze maze2 = new Maze(3);
		System.out.println(maze2.getMinMoves());
		
//		int[] x = maze.createLevelPath(test[0], 6, 1, 1);
//		maze.createLevelPath(test[1], 6, 1, 1);
//		System.out.println(maze.meetsConditions(1, 4, test[0], 'E'));
//		maze.moveInDir(test[0], 5, 3);
//		int[] x = maze.createLevelPath(test[2], 6, 1, 1);
//		System.out.println("this:" + x[0] + " " + x);
		//maze.createPath(test);
		
		/*ArrayList<int[]> walls = new ArrayList<int[]>();
		maze.setBaseMazeAndWalls(test, walls);
		maze.fillBaseMaze(test, walls);
		for (int level=0; level<test.length; level++) {
			for (int x=0; x<test[0].length; x++) {
				for (int y=0; y<test[0][0].length; y++) {
					System.out.print(test[level][x][y] + " ");
				}
				System.out.println();
			}
			System.out.println("\n");
		}*/
//		System.out.println(maze.pathFind(test, new int[] {0, 1, 1}, new int[] {0, 7, 7}));
	}
}