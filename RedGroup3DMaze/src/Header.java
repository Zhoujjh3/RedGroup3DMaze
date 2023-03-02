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

    public void display(Graphics g, double scaleFactor) {
        g.setColor(Color.gray);
        g.fillRect(0,0,(int)(1000* scaleFactor),(int)(40 * scaleFactor));
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.drawString("X: " + (player.getCoordinate('X') + 1), (int)(20* scaleFactor), (int)(25 * scaleFactor));
        g.drawString("Y: " + (player.getCoordinate('Y') + 1), (int)(60 * scaleFactor), (int)(25 * scaleFactor));
        g.drawString("Player Level: " + (player.getCoordinate('Z') + 1), (int)(250 * scaleFactor), (int)(25 * scaleFactor));
        g.drawString("Moves: " + player.getScore(), (int)(510 * scaleFactor), (int)(25 * scaleFactor));
        g.drawString(Character.toString(player.getDirection()), (int)(640 * scaleFactor), (int)(25 * scaleFactor));
//        g.setColor(Color.BLACK);
//        g.drawRect(740,7,100,25);

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

    public String getView() {
        if(chamberView) {
            return("MAP");
        } else {
            return("CHAMBER");
        }
    }

}