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
	public static int width = 1000;
	public static int height = 700;
	
	public DrawShapes() {
		screen = new JFrame();
		panel = new ShapesPanel(width, height);
		panel.addMouseListener(new ShapesClicker());
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setContentPane(panel);
		screen.pack();
	    screen.setVisible(true);
	    ShapesTimer.start();
	}
	
	ActionListener rotate = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(width != panel.getWidth() || height != panel.getHeight()) {
				ShapesPanel.timeCounter = 0;
			}
			width = panel.getWidth();
			height = panel.getHeight();
			for(Shapes shape : ShapesPanel.ceilingAndFloor) {
				shape.update();
			}
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
					if(ShapesClicker.dir == 0) {
						i.setState(i.getState() + 1);
						i.setState(i.getState() % 4);
						clicked = false;
					} else if (ShapesClicker.dir == 1) {
						i.setState(i.getState() - 1);
						if(i.getState() == -1) {
							i.setState(3);
						}
						clicked = false;
					}
				}
				for(Shapes i : ShapesPanel.doors) {
					if(ShapesClicker.dir == 0) {
						i.setState(i.getState() + 1);
						i.setState(i.getState() % 4);
						clicked = false;
					} else if (ShapesClicker.dir == 1) {
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
	Timer ShapesTimer = new Timer(5, rotate);
	
	
	public static void main(String[] args) {
		new DrawShapes();
	}
}