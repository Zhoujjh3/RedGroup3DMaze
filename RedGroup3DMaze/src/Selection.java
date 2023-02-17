import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
//https://www.tutorialspoint.com/how-to-set-the-location-of-a-button-anywhere-in-jframe

public class Selection {
	
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
	    easyButt.setBounds(300,200,400,100);
	    easyButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    easyButt.setBackground(Color.green);
	    easyButt.setOpaque(true);
	    panel.add(easyButt);
	    
	    mediumButt = new JButton("Medium");
	    mediumButt.setBounds(300, 350, 400, 100);
	    mediumButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    mediumButt.setBackground(Color.yellow);
	    mediumButt.setOpaque(true);
	    panel.add(mediumButt);
	    
	    hardButt = new JButton("Hard");
	    hardButt.setBounds(300,500,400,100);
	    hardButt.setFont(new Font("Serif", Font.PLAIN, 30));
	    hardButt.setBackground(Color.red);
	    hardButt.setOpaque(true);
	    panel.add(hardButt);
	    
	    logo = new JLabel(new ImageIcon("Images/MazeLogo.png"));
	    logo.setBounds(900, 200, 400, 400);
	    panel.add(logo);
	    
	    title = new JLabel("Welcome to 3D Maze");
	    title.setFont(new Font("Serif", Font.PLAIN, 50));
	    title.setBounds(500, 50, 1000, 100);
	    panel.add(title);
	    
	    
	    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.add(panel);
	    frame.setVisible(true);
		
		frame.setContentPane(panel);
		frame.setSize(1500, 750);
		
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
}