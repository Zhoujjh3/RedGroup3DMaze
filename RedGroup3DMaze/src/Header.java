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
        g.fillRect(0,0,500,50);
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.drawString("X: " + player.getCoordinate('x'), 20, 25);
        g.drawString("Y: " + player.getCoordinate('y'), 60, 25);
        g.drawString("Player Level: " + player.getCoordinate('z'), 120, 25);
        g.drawString("Moves: " + player.getScore(), 240, 25);

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
