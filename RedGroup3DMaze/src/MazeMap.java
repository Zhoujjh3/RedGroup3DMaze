import java.awt.*;

public class MazeMap {
	
	private int level;
	private Maze maze;
	private PlayerData player;
	
	MazeMap(Maze maze, PlayerData player) {
		this.maze = maze;
		this.player = player;
		level = player.getCoordinate('z');
	}
	
	public void display(Graphics g) {
		//testing
	}
	
	public void display(Graphics g, int newLevel) {
		
	}
	
}