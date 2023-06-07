import java.awt.Color;
import java.awt.Font;
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
	JLabel title, logo, background;
	JButton easyButt, mediumButt, hardButt; 
	private boolean signal = false;
	
	
	public Selection(JFrame frame, JPanel panel) {
		this.frame = frame;
		this.panel = panel;
		panel.setLayout(null);
		background = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("selBackground.png")));	
		easyButt = new JButton("Easy");
		mediumButt = new JButton("Medium");
		hardButt = new JButton("Hard");
		logo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("MazeLogo.png")));
		title = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Welcome.png")));	
	}
	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawImage(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("selBackground.png"))), 0, 0, );
//	}
	
	public void display() {
//	    background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
//	    background.setOpaque(true);
		
	    easyButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    easyButt.setBackground(Color.green);
//	    easyButt.setOpaque(true);
	    easyButt.setActionCommand("Easy");
	    easyButt.addActionListener(this);
	    
	    mediumButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    mediumButt.setBackground(Color.yellow);
//	    mediumButt.setOpaque(true);
	    mediumButt.setActionCommand("Medium");
	    mediumButt.addActionListener(this);
	    
	    hardButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    hardButt.setBackground(Color.red);
//	    hardButt.setOpaque(true);
	    hardButt.setActionCommand("Hard");
	    hardButt.addActionListener(this);
	    
	    int panelWidth = panel.getSize().width;
	    int panelHeight = panel.getSize().height;
	    		
	    easyButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.267),(int)(panelWidth*.4),(int)(panelHeight*.1));
	    easyButt.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth*.03)));
	    mediumButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.467), (int)(panelWidth*.4),(int)(panelHeight*.1));
	    mediumButt.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth*.03)));
	    hardButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.667), (int)(panelWidth*.4),(int)(panelHeight*.1));
	    hardButt.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth*.03)));
	    	    
	    title.setBounds((int)(panelWidth*0.01), (int)(panelHeight*.06), (int)(panelWidth*1), (int)(panelHeight*.13));
	    	    
	    logo.setBounds((int)(panelWidth*0.5), (int)(panelHeight*.267), (int)(panelWidth*.4), (int)(panelHeight*.53));
	    
//	    try {
//            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("selBackground.png")))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
	    
	    panel.add(background);
	    panel.add(easyButt);
	    panel.add(mediumButt);
	    panel.add(hardButt);
	    panel.add(logo);
	    panel.add(title);
	}
	
	public void hide(JFrame frame, JPanel panel) {
		panel.remove(background);
		panel.remove(easyButt);
		panel.remove(mediumButt);
		panel.remove(hardButt);
		panel.remove(title);
		panel.remove(logo);
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
