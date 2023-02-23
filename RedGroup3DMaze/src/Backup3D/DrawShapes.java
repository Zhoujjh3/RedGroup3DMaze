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
	public static boolean runTimer = false;
	
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
	
	//public static int counter = 0;
	ActionListener rotate = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			for(Shapes shape : ShapesPanel.shapesList) {
				shape.update();
			}
			panel.repaint();
			ShapesPanel.timeCounter++;
		}
	};
	Timer ShapesTimer = new Timer(1, rotate);
	
	
	public static void main(String[] args) {
		new DrawShapes();
	}
}