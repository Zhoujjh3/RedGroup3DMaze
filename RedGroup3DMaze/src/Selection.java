import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
//https://www.tutorialspoint.com/how-to-set-the-location-of-a-button-anywhere-in-jframe

public class Selection implements ActionListener{
	
	JFrame frame; 
	JPanel panel;
	JLabel title, logo; 
	JButton easyButt, mediumButt, hardButt; 
	
	Selection(){
		
		frame = new JFrame("Selection2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(null);
		
	    easyButt = new JButton("Easy");
	    easyButt.setBounds(70,200,400,100);
	    easyButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    easyButt.setBackground(Color.green);
	    easyButt.setOpaque(true);
	    //easyButt.setBorderPainted(false);
	    panel.add(easyButt);
	    easyButt.setActionCommand("Easy");
	    easyButt.addActionListener(this);
	    
	    mediumButt = new JButton("Medium");
	    mediumButt.setBounds(70, 350, 400, 100);
	    mediumButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    mediumButt.setBackground(Color.yellow);
	    mediumButt.setOpaque(true);
	    panel.add(mediumButt);
	    mediumButt.setActionCommand("Medium");
	    mediumButt.addActionListener(this);
	    
	    hardButt = new JButton("Hard");
	    hardButt.setBounds(70,500,400,100);
	    hardButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    hardButt.setBackground(Color.red);
	    hardButt.setOpaque(true);
	    panel.add(hardButt);
	    hardButt.setActionCommand("Hard");
	    hardButt.addActionListener(this);
	    
	    logo = new JLabel(new ImageIcon("Images/MazeLogo.png"));
	    logo.setBounds(500, 200, 400, 400);
	    panel.add(logo);
	    
	    title = new JLabel("Welcome to 3D Maze");
	    title.setFont(new Font("Serif", Font.PLAIN, 50));
	    title.setBounds(250, 50, 1000, 100);
	    panel.add(title);
	    
	    
	    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.add(panel);
	    frame.setVisible(true);
		
		frame.setContentPane(panel);
		frame.setSize(1000, 750);
		
		frame.setVisible(true);
	}
	
	public void paintComponent() {
		
	}
	
	public void display(Graphics g) {
		
	}
	
	private static void runGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new Selection();
	}
	
   public static void main(String[] args) {      
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runGUI();
			}
		});
   }


	public void actionPerformed(ActionEvent e) {
		String eventName = e.getActionCommand();
		System.out.println(eventName);
		
	}



}