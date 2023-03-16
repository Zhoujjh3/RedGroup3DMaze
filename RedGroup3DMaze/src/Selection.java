import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//import Run3DMaze.mazeState;

public class Selection implements ActionListener{
	int difficulty; 
	JFrame frame; 
	JPanel panel;
	JLabel title, logo; 
	JButton easyButt, mediumButt, hardButt; 
	private boolean signal = false;
	
	public void display(JFrame frame, JPanel panel) {
		this.frame = frame;
		this.panel = panel;
		
	    easyButt = new JButton("Easy");
	    easyButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    easyButt.setBackground(Color.green);
	    easyButt.setOpaque(true);
	    easyButt.setActionCommand("Easy");
	    easyButt.addActionListener(this);
	    
	    mediumButt = new JButton("Medium");
	    mediumButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    mediumButt.setBackground(Color.yellow);
	    mediumButt.setOpaque(true);
	    mediumButt.setActionCommand("Medium");
	    mediumButt.addActionListener(this);
	    
	    hardButt = new JButton("Hard");
	    hardButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    hardButt.setBackground(Color.red);
	    hardButt.setOpaque(true);
	    hardButt.setActionCommand("Hard");
	    hardButt.addActionListener(this);
	    
	    logo = new JLabel(new ImageIcon("Images/MazeLogo.png"));
	    
	    title = new JLabel("Welcome to 3D Maze");

//	    Timer timer = new Timer();
//	    TimerTask task = new TimerTask() {
//	    	public void run() {
	    		int panelWidth = Run3DMaze.width;
	    		int panelHeight = Run3DMaze.height;
	    		
	    		easyButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.267),(int)(panelWidth*.4),(int)(panelHeight*.1));
	    		easyButt.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth*.03)));
	    	    mediumButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.467), (int)(panelWidth*.4),(int)(panelHeight*.1));
	    	    mediumButt.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth*.03)));
	    	    hardButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.667), (int)(panelWidth*.4),(int)(panelHeight*.1));
	    	    hardButt.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth*.03)));
	    	    
	    	    title.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth*.05)));
	    	    title.setBounds((int)(panelWidth*0.25), (int)(panelHeight*.06), (int)(panelWidth*1), (int)(panelHeight*.13));
	    	    
	    	    logo.setBounds((int)(panelWidth*0.5), (int)(panelHeight*.267), (int)(panelWidth*.4), (int)(panelHeight*.53));
	    		  	    
	    	    panel.add(easyButt);
	    	    panel.add(mediumButt);
	    	    panel.add(hardButt);
	    	    panel.add(logo);
	    	    panel.add(title);
//	    	}
//	    };
//	    timer.schedule(task, 1, 1);
	}
	
	public void hide(JFrame frame, JPanel panel) {
		panel.remove(easyButt);
		panel.remove(mediumButt);
		panel.remove(hardButt);
		panel.remove(title);
		panel.remove(logo);
	}

//handles setting the difficulty of the maze
	public void actionPerformed(ActionEvent event) {
		String eventName = event.getActionCommand();
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
