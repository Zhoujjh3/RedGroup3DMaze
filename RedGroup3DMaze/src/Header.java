import java.awt.*;

public class Header {
    private boolean chamberView;
    private PlayerData player;
    public Header(Maze maze, PlayerData player) {
        this.player = player;
        chamberView = true;
    }

    public void display(Graphics g, Dimension scaleFactor) {
        g.setColor(Color.gray);
        g.fillRect(0,0,(int)(scaleFactor.width),(int)(40 * scaleFactor.height/750));
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(15* scaleFactor.height/750)));
        g.drawString("X: " + (player.getCoordinate('X') + 1), (int)(20* scaleFactor.width/1000), (int)(25 * scaleFactor.height/750));
        g.drawString("Y: " + (player.getCoordinate('Y') + 1), (int)(60 * scaleFactor.width/1000), (int)(25 * scaleFactor.height/750));
        g.drawString("Player Level: " + (player.getCoordinate('Z') + 1), (int)(200 * scaleFactor.width/1000), (int)(25 * scaleFactor.height/750));
        g.drawString("Moves: " + player.getMoves(), (int)(400 * scaleFactor.width/1000), (int)(25 * scaleFactor.height/750));
        g.drawString("Direction: " + Character.toString(player.getDirection()), (int)(550 * scaleFactor.width/1000), (int)(25 * scaleFactor.height/750));
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