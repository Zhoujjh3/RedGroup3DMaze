package Backup3D;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawShapes {
	public JFrame screen;
	public static JPanel panel;
	public static boolean clicked;
	
	public DrawShapes() {
		screen = new JFrame();
		panel = new ShapesPanel();
		panel.setPreferredSize(new Dimension(1000, 700));
		panel.addMouseListener(new ShapesClicker());
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setContentPane(panel);
		screen.pack();
	    screen.setVisible(true);
	    ShapesTimer.start();
	}
	
	ActionListener rotate = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			for(Shapes shape : ShapesPanel.walls) {
				shape.update();
			}
			for(Shapes shape : ShapesPanel.doors) {
				shape.update();
			}
			panel.repaint();
			ShapesPanel.timeCounter++;
			
			//updates states after animation is finished
			if(ShapesPanel.timeCounter >= 200 && clicked) {
				for(Shapes i : ShapesPanel.walls) {
					if(ShapesClicker.dir) {
						i.setState(i.getState() + 1);
						i.setState(i.getState() % 4);
						clicked = false;
					} else if (!ShapesClicker.dir) {
						i.setState(i.getState() - 1);
						if(i.getState() == -1) {
							i.setState(3);
						}
						clicked = false;
					}
				}
				/*for(int i = 0; i < ShapesPanel.walls.length; i++) {
					if(ShapesClicker.dir) {
						ShapesPanel.walls[i].setState(ShapesPanel.walls[i].getState() + 1);
						ShapesPanel.walls[i].setState(ShapesPanel.walls[i].getState() % 4);
						clicked = false;
						if(i > 0) {
							ShapesPanel.tempWalls[i-1] = ShapesPanel.walls[i];
						} else {
							ShapesPanel.tempWalls[3] = ShapesPanel.walls[i];
						}
					} else if (!ShapesClicker.dir) {
						ShapesPanel.walls[i].setState(ShapesPanel.walls[i].getState() - 1);
						if(ShapesPanel.walls[i].getState() == -1) {
							ShapesPanel.walls[i].setState(3);
						}
						clicked = false;
						if(i == 3) {
							ShapesPanel.tempWalls[0] = ShapesPanel.walls[i];
						} else {
							ShapesPanel.tempWalls[i+1] = ShapesPanel.walls[i];
						}
					}
				}
				for(int i = 0; i < ShapesPanel.walls.length; i++) {
					ShapesPanel.walls[i] = ShapesPanel.tempWalls[i]; 
				}*/
				for(Shapes i : ShapesPanel.doors) {
					if(ShapesClicker.dir) {
						i.setState(i.getState() + 1);
						i.setState(i.getState() % 4);
						clicked = false;
					} else if (!ShapesClicker.dir) {
						i.setState(i.getState() - 1);
						if(i.getState() == -1) {
							i.setState(3);
						}
						clicked = false;
					}
				}
			} 
			
		}
	};
	Timer ShapesTimer = new Timer(2, rotate);
	
	
	public static void main(String[] args) {
		new DrawShapes();
	}
}