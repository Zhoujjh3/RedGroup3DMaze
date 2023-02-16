public class PlayerData {
    private int[] coordinates = new int[3];
    private char direction;
    private int score;
    private int mazeSize;
    public PlayerData(Maze maze) {
        coordinates[0] = 0;
        coordinates[1] = 0;
        coordinates[2] = 0;
        direction = 'S';
        score = 0;
        mazeSize = maze.getMazeSize();
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
    public char getDirection() {
        return direction;
    }
    public int getScore() {
        return score;
    }
    public void setDirection(char newDir) {
    	if (newDir == 'N' || newDir == 'E' || newDir == 'S' || newDir == 'W' || newDir == 'U' || newDir == 'D') {
    		direction = newDir;
    	}
    }
    public void setScore(int newScore) {
    	score = newScore;
    }
    public void movePlayer(char direction) {
    	if (direction == 'N' && coordinates[1] > 0) {
    		coordinates[1]--;
    	} else if (direction == 'E' && coordinates[0] < mazeSize-1) {
    		coordinates[0]++;
    	} else if (direction == 'S' && coordinates[1] < mazeSize-1) {
    		coordinates[1]++;
    	} else if (direction == 'W' && coordinates[0] > 0) {
    		coordinates[0]--;
    	} else if (direction == 'U' && coordinates[2] > 0) {
    		coordinates[2]--;
    	} else if (direction == 'D' && coordinates[2] < mazeSize-1) {
    		coordinates[2]++;
    	}
    }
} // Testing