import java.awt.*;
//requires java.datatransfer;
//    requires java.desktop;
public class Header {
    private boolean chamberView;
    private Maze maze;
    private PlayerData player;
    public Header(Maze maze, PlayerData player) {
        this.maze = maze;
        this.player = player;
        chamberView = true;
    }

    public void display(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0,0,1000,40);
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.drawString("X: " + player.getCoordinate('X'), 20, 25);
        g.drawString("Y: " + player.getCoordinate('Y'), 60, 25);
        g.drawString("Player Level: " + player.getCoordinate('Z'), 250, 25);
        g.drawString("Moves: " + player.getScore(), 510, 25);
        g.drawString(Character.toString(player.getDirection()), 640, 25);
        g.setColor(Color.BLACK);
        g.drawRect(740,7,100,25);
        if(chamberView) {
            g.drawString("MAP",775,25);
        } else {
            g.drawString("CHAMBER",750,25);
        }
    }
    public void update() {

    }
    public void changeView() {
        if (chamberView) {
            chamberView = false;
        } else {
            chamberView = true;
        }
    }

}
