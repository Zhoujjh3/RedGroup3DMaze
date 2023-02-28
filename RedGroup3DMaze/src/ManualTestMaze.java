public class ManualTestMaze {
	
	private Room[][][] activeMaze = new Room[4][4][4];
	private boolean[][][][] directions; // N, E, S, W, U, D
	private int difficulty = 2;
	
	public ManualTestMaze() {
		generateDirections();
		for(int x=1; x<=4; x++) {
			for(int y=1; y<=4; y++) {
				for(int z=1; z<=4; z++) {
					int[] coords = {x, y, z};
					activeMaze[x-1][y-1][z-1] = new Room(coords, directions[x-1][y-1][z-1]);
				}
			}
		}
	}
	
	private void generateDirections() {
		//row 1, floor 1 (top floor)
		boolean[] c_111 = {false, true, false, false, false, false}; //east	
		boolean[] c_211 = {false, true, false, true, false, false}; //east, west
		boolean[] c_311 = {false, false, true, true, false, false}; //west, south
		boolean[] c_411 = {false, false, true, false, false, true};  //down, south
		
		boolean[][] Floor1Row1 = {c_111, c_211, c_311, c_411};
		//row 2, floor 1 
		boolean[] c_121 = {false, false, false, false, false, false}; //blank
		boolean[] c_221 = {false, true, true, false, false, false}; //south, east
		boolean[] c_321 = {true, false, false, true, false, false}; //north, west
		boolean[] c_421 = {true, false, true, false, false, false}; //north, south
		
		boolean[][] Floor1Row2 = {c_121, c_221, c_321, c_421};
		//row 3, floor 1
		boolean[] c_131 = {false, false, false, false, false, false}; //blank
		boolean[] c_231 = {true, false, false, false, false, true}; //down, north
		boolean[] c_331 = {false, false, false, false, false, false}; //blank
		boolean[] c_431 = {true, false, true, false, false, false}; //north, south
		
		boolean[][] Floor1Row3 = {c_131, c_231, c_331, c_431};
		//row 4, floor 1
		boolean[] c_141 = {false, false, false, false, false, false}; //blank
		boolean[] c_241 = {false, false, false, false, false, false}; //blank
		boolean[] c_341 = {false, false, false, false, false, false}; //blank
		boolean[] c_441 = {true, false, false, false, false, true}; //north, down
		
		boolean[][] Floor1Row4 = {c_141, c_241, c_341, c_441};
		
		boolean[][][] directionsFloor1 = {Floor1Row1, Floor1Row2, Floor1Row3, Floor1Row4};
		
		
		/*******************************************************************************/
		
		//row 1, floor 2 (second to top floor)
		boolean[] c_112 = {false, false, false, false, false, false}; //blank
		boolean[] c_212 = {false, false, false, false, false, false}; //blank
		boolean[] c_312 = {false, false, false, false, false, false}; //blank
		boolean[] c_412 = {true, false, false, false, true, false}; //north, up
		
		boolean[][] Floor2Row1 = {c_112, c_212, c_312, c_412};
		//row 2, floor 2
		boolean[] c_122 = {false, false, false, false, false, false}; //blank
		boolean[] c_222 = {false, false, false, false, false, false}; //blank
		boolean[] c_322 = {false, false, false, false, false, false}; //blank
		boolean[] c_422 = {true, false, true, false, false, false}; //north, south
		
		boolean[][] Floor2Row2 = {c_122, c_222, c_322, c_422};
		//row 3, floor 2
		boolean[] c_132 = {false, false, false, false, false, false}; //blank
		boolean[] c_232 = {false, true, false, false, true, false}; //up, east
		boolean[] c_332 = {false, true, false, true, false, false}; //east, west
		boolean[] c_432 = {true, false, false, true, false, false}; //west, north
		
		boolean[][] Floor2Row3 = {c_132, c_232, c_332, c_432};
		//row 4, floor 2
		boolean[] c_142 = {false, false, false, false, false, false}; //blank
		boolean[] c_242 = {false, true, false, false, false, true}; //down, east
		boolean[] c_342 = {false, true, false, true, false, false}; //east, west
		boolean[] c_442 = {false, false, false, true, true, false}; //up, west
		
		boolean[][] Floor2Row4 = {c_142, c_242, c_342, c_442};
		
		boolean[][][] directionsFloor2 = {Floor2Row1, Floor2Row2, Floor2Row3, Floor2Row4};
		
		/*******************************************************************************/
		
		//row 1, floor 3 (second to bottom floor)
		boolean[] c_113 = {false, true, true, false, false, false}; //south, east
		boolean[] c_213 = {false, true, false, true, false, false}; //east, west
		boolean[] c_313 = {false, true, false, true, false, false}; //east, west
		boolean[] c_413 = {true, false, false, true, false, true}; //west, down
		
		boolean[][] Floor3Row1 = {c_113, c_213, c_313, c_413};
		//row 2, floor 3
		boolean[] c_123 = {true, true, false, false, false, false}; //east, north
		boolean[] c_223 = {false, false, true, true, false, false}; //south, west
		boolean[] c_323 = {false, false, false, false, false, false}; //blank
		boolean[] c_423 = {false, false, false, false, false, false}; //blank
		
		boolean[][] Floor3Row2 = {c_123, c_223, c_323, c_423};
		//row 3, floor 3
		boolean[] c_133 = {false, false, false, false, false, false}; //blank
		boolean[] c_233 = {true, false, true, false, false, false}; //south, north
		boolean[] c_333 = {false, false, false, false, false, false}; //blank
		boolean[] c_433 = {false, false, false, false, false, false}; //blank
		
		boolean[][] Floor3Row3 = {c_133, c_233, c_333, c_433};
		//row 4, floor 3
		boolean[] c_143 = {false, false, false, false, false, false}; //blank
		boolean[] c_243 = {true, false, false, false, true, false}; //up, north
		boolean[] c_343 = {false, false, false, false, false, false}; //blank
		boolean[] c_443 = {false, false, false, false, false, false}; //blank
		
		boolean[][] Floor3Row4 = {c_143, c_243, c_343, c_443};	
		
		boolean[][][] directionsFloor3 = {Floor3Row1, Floor3Row2, Floor3Row3, Floor3Row4};
		/*******************************************************************************/
		
		//row 1, floor 4 (bottom)
		boolean[] c_114 = {false, false, false, false, false, false}; //blank
		boolean[] c_214 = {false, false, false, false, false, false}; //blank
		boolean[] c_314 = {false, false, false, false, false, false}; //blank
		boolean[] c_414 = {false, false, true, false, false, true}; //down, south
		
		boolean[][] Floor4Row1 = {c_114, c_214, c_314, c_414};
		//row 2, floor 4
		boolean[] c_124 = {false, false, false, false, false, false}; //blank
		boolean[] c_224 = {false, false, false, false, false, false}; //blank
		boolean[] c_324 = {false, false, false, false, false, false}; //blank
		boolean[] c_424 = {true, false, true, false, false, false}; //north, south
		
		boolean[][] Floor4Row2 = {c_124, c_224, c_324, c_424};
		//row 3, floor 4
		boolean[] c_134 = {false, false, false, false, false, false}; //blank
		boolean[] c_234 = {false, false, false, false, false, false}; //blank
		boolean[] c_334 = {false, false, false, false, false, false}; //blank
		boolean[] c_434 = {true, false, true, false, false, false}; //north, south
		
		boolean[][] Floor4Row3 = {c_134, c_234, c_334, c_434};
		//ro2 4, floor 4
		boolean[] c_144 = {false, false, false, false, false, false}; //blank
		boolean[] c_244 = {false, false, false, false, false, false}; //blank
		boolean[] c_344 = {false, false, false, false, false, false}; //blank
		boolean[] c_444 = {true, false, false, false, false, false}; //north (END)
		boolean[][] Floor4Row4 = {c_144, c_244, c_344, c_444};
		
		boolean[][][] directionsFloor4 = {Floor4Row1, Floor4Row2, Floor4Row3, Floor4Row4};
		
		boolean[][][][] temp = {directionsFloor1, directionsFloor2, directionsFloor3, directionsFloor4};
		directions = temp;
	}
	
	private int[] getCoords(int x, int y, int z) {
		int[] result = {x, y, z};
		return result;
	}
	
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
	
	public int getMazeSize() {
		return 4;
	}
	
	/*
	public static void main(String[]args) {
		new ManualTestMaze();
	}
	*/
}