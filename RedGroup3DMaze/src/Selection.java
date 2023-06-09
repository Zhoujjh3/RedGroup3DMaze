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
	//JLabel title, logo, background;
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
		//logo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("MazeLogo.png")));
		//title = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Welcome.png")));	
	}
	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawImage(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("selBackground.png"))), 0, 0, );
//	}
	
	public void display() {
		background.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("3D maze loading screen for MARTIN.png")).getImage().
				getScaledInstance((int)(frame.getWidth()*0.99), (int)(frame.getHeight()*0.954), 
						java.awt.Image.SCALE_SMOOTH)));
	    background.setBounds(-5, -17, frame.getWidth(), frame.getHeight());
//	    background.setOpaque(true);
		
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
	    
	    int panelWidth = panel.getSize().width;
	    int panelHeight = panel.getSize().height;
	    
	    easyButt.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("easy.png")).getImage().
				getScaledInstance((int)(300 * panelWidth/1000), (int)(75* panelHeight/750), 
						java.awt.Image.SCALE_SMOOTH)));
	    mediumButt.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("medium.png")).getImage().
				getScaledInstance((int)(300 * panelWidth/1000), (int)(75* panelHeight/750), 
						java.awt.Image.SCALE_SMOOTH)));
	    hardButt.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("hard.png")).getImage().
				getScaledInstance((int)(300 * panelWidth/1000), (int)(75* panelHeight/750), 
						java.awt.Image.SCALE_SMOOTH)));
	    
	    easyButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.333),(int)(400*panelWidth/1000),(int)(110*panelHeight/750));
	    //easyButt.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth*.03)));
	    mediumButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.5), (int)(400*panelWidth/1000),(int)(110*panelHeight/750));
	    //mediumButt.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth*.03)));
	    hardButt.setBounds((int)(panelWidth*.07), (int)(panelHeight*0.667), (int)(400*panelWidth/1000),(int)(110*panelHeight/750));
	    //hardButt.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth*.03)));
	    	    
	    //title.setBounds((int)(panelWidth*0.01), (int)(panelHeight*.06), (int)(panelWidth*1), (int)(panelHeight*.13));
	    	    
	    //logo.setBounds((int)(panelWidth*0.5), (int)(panelHeight*.267), (int)(panelWidth*.4), (int)(panelHeight*.53));
	    
//	    try {
//            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("selBackground.png")))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
	    
	    
	    panel.add(easyButt);
	    panel.add(mediumButt);
	    panel.add(hardButt);
	    panel.add(background);
	    //panel.add(logo);
	    //panel.add(title);
	}
	
	public void hide(JFrame frame, JPanel panel) {
		panel.remove(background);
		panel.remove(easyButt);
		panel.remove(mediumButt);
		panel.remove(hardButt);
		//panel.remove(title);
		//panel.remove(logo);
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
