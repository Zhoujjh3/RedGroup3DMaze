import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Header {
    private boolean chamberView;
    private PlayerData player;
    private Image X, Y, PlayerLevel, Moves;
    public Header(Maze maze, PlayerData player) {
        this.player = player;
        chamberView = true;
        try {
			X = ImageIO.read(getClass().getClassLoader().getResource("X.png"));
			Y = ImageIO.read(getClass().getClassLoader().getResource("Y.png"));
			PlayerLevel = ImageIO.read(getClass().getClassLoader().getResource("Player Level.png"));
			Moves = ImageIO.read(getClass().getClassLoader().getResource("Moves.png"));
		} catch (IOException e) {
			System.out.println("Background image not found.");
			e.printStackTrace();
		}
    }

    public void display(Graphics g, Dimension scaleFactor) {
        g.setColor(Color.black);
        g.fillRect(0,0,(int)(scaleFactor.width),(int)(40 * scaleFactor.height/750));
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(15* scaleFactor.height/750)));
        //g.drawString("X: " + (player.getCoordinate('X') + 1), (int)(20* scaleFactor.width/1000), (int)(25 * scaleFactor.height/750));
        g.drawImage(X, (int)(25*scaleFactor.width/1000-100), (int)(10 * scaleFactor.height/750 - 7), null);
        //g.drawString("Y: " + (player.getCoordinate('Y') + 1), (int)(60 * scaleFactor.width/1000), (int)(25 * scaleFactor.height/750));
        g.drawImage(Y, (int)(65*scaleFactor.width/1000-100), (int)(10 * scaleFactor.height/750 - 7), null);
        //g.drawString("Player Level: " + (player.getCoordinate('Z') + 1), (int)(200 * scaleFactor.width/1000), (int)(25 * scaleFactor.height/750));
        g.drawImage(PlayerLevel, (int)(205*scaleFactor.width/1000-140), (int)(10 * scaleFactor.height/750-7), null);
        //g.drawString("Moves: " + player.getMoves(), (int)(400 * scaleFactor.width/1000), (int)(25 * scaleFactor.height/750));
        g.drawImage(Moves, (int)(405*scaleFactor.width/1000-140), (int)(10 * scaleFactor.height/750 - 7), null);
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