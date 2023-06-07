import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Header {
    private boolean chamberView;
    private PlayerData player;
    private Image X, Y, PlayerLevel, Moves, Direction;
    private Image[] digits, directions;
    public Header(Maze maze, PlayerData player) {
        this.player = player;
        chamberView = true;
        try {
			X = ImageIO.read(getClass().getClassLoader().getResource("X.png"));
			Y = ImageIO.read(getClass().getClassLoader().getResource("Y.png"));
			PlayerLevel = ImageIO.read(getClass().getClassLoader().getResource("Player Level.png"));
			Moves = ImageIO.read(getClass().getClassLoader().getResource("Moves.png"));
			Direction = ImageIO.read(getClass().getClassLoader().getResource("direction.png"));
			digits = new Image[10];
			for (int i=0; i<10; i++) {
				digits[i] = ImageIO.read(getClass().getClassLoader().getResource(String.valueOf(i)+".png"));
			}
			directions = new Image[4];
			directions[0] = ImageIO.read(getClass().getClassLoader().getResource("N.png"));
			directions[1] = ImageIO.read(getClass().getClassLoader().getResource("e.png"));
			directions[2] = ImageIO.read(getClass().getClassLoader().getResource("s.png"));
			directions[3] = ImageIO.read(getClass().getClassLoader().getResource("w.png"));
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
        Image temp = X.getScaledInstance((int)(100*scaleFactor.width/1000), (int)(30*scaleFactor.height/750), java.awt.Image.SCALE_SMOOTH);
        g.drawImage(temp, (int)(-40*scaleFactor.width/1000), (int)(4 * scaleFactor.height/750), null);
        temp = digits[player.getCoordinate('X') + 1].getScaledInstance((int)(13*scaleFactor.width/1000), (int)(13*scaleFactor.height/750), java.awt.Image.SCALE_SMOOTH);
        g.drawImage(temp, (int)(65*scaleFactor.width/1000), (int)(13 * scaleFactor.height/750), null);
        
        temp = Y.getScaledInstance((int)(100*scaleFactor.width/1000), (int)(30*scaleFactor.height/750), java.awt.Image.SCALE_SMOOTH);
        g.drawImage(temp, (int)(20*scaleFactor.width/1000), (int)(4 * scaleFactor.height/750), null);
        temp = digits[player.getCoordinate('Y') + 1].getScaledInstance((int)(13*scaleFactor.width/1000), (int)(13*scaleFactor.height/750), java.awt.Image.SCALE_SMOOTH);
        g.drawImage(temp, (int)(125*scaleFactor.width/1000), (int)(13 * scaleFactor.height/750), null);
        
        temp = PlayerLevel.getScaledInstance((int)(140*scaleFactor.width/1000), (int)(30*scaleFactor.height/750), java.awt.Image.SCALE_SMOOTH);
        g.drawImage(temp, (int)(200*scaleFactor.width/1000), (int)(4 * scaleFactor.height/750), null);
        temp = digits[player.getCoordinate('Z') + 1].getScaledInstance((int)(13*scaleFactor.width/1000), (int)(13*scaleFactor.height/750), java.awt.Image.SCALE_SMOOTH);
        g.drawImage(temp, (int)(345*scaleFactor.width/1000), (int)(13 * scaleFactor.height/750), null);
        
        temp = Moves.getScaledInstance((int)(140*scaleFactor.width/1000), (int)(30*scaleFactor.height/750), java.awt.Image.SCALE_SMOOTH);
        g.drawImage(temp, (int)(360*scaleFactor.width/1000), (int)(4 * scaleFactor.height/750), null);
        String digit = String.valueOf(player.getMoves()).substring(0,1);
        temp = digits[Integer.parseInt(digit)].getScaledInstance((int)(13*scaleFactor.width/1000), (int)(13*scaleFactor.height/750), java.awt.Image.SCALE_SMOOTH);
        g.drawImage(temp, (int)((505)*scaleFactor.width/1000), (int)(13 * scaleFactor.height/750), null);
    	for (int i = 1; i<String.valueOf(player.getMoves()).length(); i++) {
    		digit = String.valueOf(player.getMoves()).substring(i, i+1);
    		temp = digits[Integer.parseInt(digit)].getScaledInstance((int)(13*scaleFactor.width/1000), (int)(13*scaleFactor.height/750), java.awt.Image.SCALE_SMOOTH);
    		g.drawImage(temp, (int)((505+13*i)*scaleFactor.width/1000), (int)(13 * scaleFactor.height/750), null);
    	}
    	
    	temp = Direction.getScaledInstance((int)(140*scaleFactor.width/1000), (int)(30*scaleFactor.height/750), java.awt.Image.SCALE_SMOOTH);
        g.drawImage(temp, (int)(580*scaleFactor.width/1000), (int)(4 * scaleFactor.height/750), null);
        int num = 3;
        if (player.getDirection()=='N') {
        	num = 0;
        } else if (player.getDirection()=='E') {
        	num = 1;
        } else if (player.getDirection()=='S') {
        	num = 2;
        }
        temp = directions[num].getScaledInstance((int)(100*scaleFactor.width/1000), (int)(30*scaleFactor.height/750), java.awt.Image.SCALE_SMOOTH);
        g.drawImage(temp, (int)(638*scaleFactor.width/1000), (int)(4 * scaleFactor.height/750), null);
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
    public String getButtonView() {
        if(chamberView) {
            return("CHAMBER");
        } else {
            return("MAP");
        }
    }
}