import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class Selection extends JPanel implements ActionListener{
	int difficulty; 
	JFrame frame; 
	JPanel panel;
	JLabel background;
	JButton easyButt, mediumButt, hardButt; 
	private boolean signal = false;
	
	
	public Selection(JFrame frame, JPanel panel) {
		this.frame = frame;
		this.panel = panel;
		panel.setLayout(null);
		background = new JLabel(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("3D maze loading screen for MARTIN.png")).getImage()));
		easyButt = new JButton(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("easy.png")).getImage().
				getScaledInstance(400, 100, java.awt.Image.SCALE_SMOOTH)));
		mediumButt = new JButton(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("medium.png")).getImage().
				getScaledInstance(400, 100, java.awt.Image.SCALE_SMOOTH)));
		hardButt = new JButton(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("hard.png")).getImage().
				getScaledInstance(400, 100, java.awt.Image.SCALE_SMOOTH)));
	}
	
	public void display() {
		background.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("3D maze loading screen for MARTIN.png")).getImage().
				getScaledInstance((int)(frame.getWidth()*0.99), (int)(frame.getHeight()*0.954), 
						java.awt.Image.SCALE_SMOOTH)));
	    background.setBounds(-5, -17, frame.getWidth(), frame.getHeight());
		
	    easyButt.setActionCommand("Easy");
	    easyButt.addActionListener(this);
	    
	    mediumButt.setActionCommand("Medium");
	    mediumButt.addActionListener(this);
	    
	    hardButt.setActionCommand("Hard");
	    hardButt.addActionListener(this);
	    
	    easyButt.setOpaque(false);
	    easyButt.setContentAreaFilled(false);
	    easyButt.setBorderPainted(false);
	    mediumButt.setOpaque(false);
	    mediumButt.setContentAreaFilled(false);
	    mediumButt.setBorderPainted(false);
	    hardButt.setOpaque(false);
	    hardButt.setContentAreaFilled(false);
	    hardButt.setBorderPainted(false);
	    
	    double panelWidth = panel.getSize().width;
	    double panelHeight = panel.getSize().height;
	    
	    easyButt.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("easy.png")).getImage().
				getScaledInstance((int)(300.0 * panelWidth/1000.0), (int)(75.0* panelHeight/750.0), 
						java.awt.Image.SCALE_SMOOTH)));
	    mediumButt.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("medium.png")).getImage().
				getScaledInstance((int)(300.0 * panelWidth/1000.0), (int)(75.0* panelHeight/750.0), 
						java.awt.Image.SCALE_SMOOTH)));
	    hardButt.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("hard.png")).getImage().
				getScaledInstance((int)(300.0 * panelWidth/1000.0), (int)(75.0* panelHeight/750.0), 
						java.awt.Image.SCALE_SMOOTH)));
	    
	    easyButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.333),(int)(400*panelWidth/1000),(int)(110*panelHeight/750));
	    mediumButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.5), (int)(400*panelWidth/1000),(int)(110*panelHeight/750));
	    hardButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.667), (int)(400*panelWidth/1000),(int)(110*panelHeight/750));
	    
	    
	    panel.add(easyButt);
	    panel.add(mediumButt);
	    panel.add(hardButt);
	    panel.add(background);
	}
	
	public void hide(JFrame frame, JPanel panel) {
		panel.remove(background);
		panel.remove(easyButt);
		panel.remove(mediumButt);
		panel.remove(hardButt);
	}

//handles setting the difficulty of the maze
	public void actionPerformed(ActionEvent event) {
		String eventName = event.getActionCommand();
		display();
		if(eventName.equals("Easy")) {
			difficulty = 1;
		}else if(eventName.equals("Medium")) {
			difficulty = 2;
		}else{
			difficulty = 3;
		}
		signal = true;
		panel.requestFocus();
		hide(frame, panel);
	}
	
	public boolean checkSignal() {
		return signal;
	}
	
	public void resetSignal() {
		signal = false;
	}
}
