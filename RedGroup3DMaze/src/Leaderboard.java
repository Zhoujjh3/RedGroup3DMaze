import javax.imageio.ImageIO;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Leaderboard implements ActionListener {
	
	JTextField[] top10 = new JTextField[10];
	double scoresList[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	double score;
	static int width = 1000;
	static int height = 750;
	PlayerData player;
	
	JFrame frame;
	JPanel contentPane;
	JButton restartButt;
	JLabel background;
	JScrollPane scrollPane;
	JTextField yourScore;
	private Image restart;
	
	private boolean signal = false;
	
	public void getScore(double newScore) {
		score = newScore;
	}
	
	public void display(JFrame frame, JPanel contentPane/*GamePanel contentPane*/) {
		background = new JLabel(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("the 3D maze leaderboard screen.png")).getImage().
				getScaledInstance(1000, 750, java.awt.Image.SCALE_SMOOTH)));
		sortScores(score);
		
		this.frame = frame;
	    this.contentPane = contentPane;
		
	    contentPane.setLayout(null);
	    
	    
	    yourScore = new JTextField(Double.toString(score));
	    yourScore.setEditable(false);
	    yourScore.setHorizontalAlignment(JTextField.LEFT);
	    contentPane.add(yourScore);
	    
	    for (int i = 0; i < top10.length; i++) {
	    	if (scoresList[i] == 0) {
	    		top10[i] = new JTextField("---");
	    	} else {
	    		top10[i] = new JTextField(Double.toString(scoresList[i]));
	    	}
	    	top10[i].setHorizontalAlignment(JTextField.CENTER);
	    	top10[i].setEditable(false);
	    	contentPane.add(top10[i]);
	    }
	    for (int i=0; i<3; i++) {
    		top10[i].setSelectedTextColor(Color.BLUE);
        	top10[i].setForeground(Color.BLUE);
    	}
    	for (int i=3; i<10; i++) {
    		top10[i].setSelectedTextColor(Color.WHITE);
        	top10[i].setForeground(Color.WHITE);
    	}
    	restartButt = new JButton(new ImageIcon(new ImageIcon(getClass().getClassLoader().
    			getResource("restart.png")).getImage().
    			getScaledInstance(150, 37, java.awt.Image.SCALE_SMOOTH)));
	    restartButt.setOpaque(false);
	    restartButt.setContentAreaFilled(false);
	    restartButt.setBorderPainted(false);
	    restartButt.setActionCommand("click");
	    restartButt.addActionListener(this);
	    frame.addMouseListener(new Clicker2());
	    contentPane.add(restartButt);
	    contentPane.add(background);

	    //dynamic
	    Timer timer = new Timer();
	    TimerTask task = new TimerTask() {
	    	public void run() {
	    		int panelWidth = contentPane.getWidth();
	    		int panelHeight = contentPane.getHeight();
	    		
	    		background.setIcon(new ImageIcon(new ImageIcon(getClass().getClassLoader().
	    				getResource("the 3D maze leaderboard screen.png")).getImage().
	    				getScaledInstance((int)(frame.getWidth()*.99), (int)(frame.getHeight()*0.954), 
	    						java.awt.Image.SCALE_SMOOTH)));
	    	    background.setBounds(-5, -17, frame.getWidth(), frame.getHeight());
	    		
	    		for (int i = 0; i < 5; i++) {
	    			top10[i].setBounds((int)(panelWidth * 0.205), (int)(panelHeight * (0.265 + (i * 0.094))), (int)(panelWidth * 0.267), (int)(panelHeight * 0.0667));
	    			top10[i + 5].setBounds((int)(panelWidth * 0.519), (int)(panelHeight * (0.265 + (i * 0.094))), (int)(panelWidth * 0.267), (int)(panelHeight * 0.0667));
	    			top10[i].setOpaque(false);
	    			top10[i].setBorder(javax.swing.BorderFactory.createEmptyBorder());
	    			top10[i+5].setOpaque(false);
	    			top10[i+5].setBorder(javax.swing.BorderFactory.createEmptyBorder());
	    			top10[i].setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth * 0.04)));
	    			top10[i + 5].setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth * 0.04)));
	    		}
	    		top10[0].setSelectedTextColor(Color.BLUE);
	    		
	    		yourScore.setBounds((int)(panelWidth * 0.39), (int)(panelHeight * 0.727), (int)(panelWidth * 0.1), (int)(panelHeight * 0.0667));
	    		yourScore.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth * 0.025)));
	    		yourScore.setForeground(Color.WHITE);
	    		yourScore.setOpaque(false);
	    		yourScore.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	    		
	    		restartButt.setBounds((int)(panelWidth * 0.575), (int)(panelHeight * 0.73), (int)(panelWidth * 0.155), (int)(panelHeight * 0.055));
	    		restartButt.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth * 0.03)));
	    	}
	    };
	    timer.schedule(task, 1, 1);
	    
	    contentPane.setPreferredSize(new Dimension(width, height));     
	}
	
	public void hide(JFrame frame, JPanel panel) {
		panel.remove(yourScore);
		panel.remove(restartButt);
		for (int i = 0; i < top10.length; i++) {
			panel.remove(top10[i]);
		}
	}
	
	//if the new score is lower than the 10th score, the 10th score is removed
	public void sortScores(double newScore) {
		bubbleSort();	
		if (newScore > scoresList[scoresList.length - 1]) {
			scoresList[scoresList.length - 1] = newScore;
			bubbleSort();
		}
	}

	//sorts the top 10 scores from lowest to greatest
	private void bubbleSort() {
		double temp;
		for (int i = 0; i < scoresList.length; i++) {
			for (int j = i + 1; j < scoresList.length; j++) {
				if (scoresList[j] > scoresList[i]) {
					temp = scoresList[i];
					scoresList[i] = scoresList[j];
					scoresList[j] = temp;
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Fuck");
		if (e.getActionCommand().equals("click")) {
			System.out.println("Fuck");
			signal = true;
			hide(frame, contentPane);
		}
	}
	
	public boolean checkSignal() {
		return signal;
	}
	
	public void resetSignal() {
		signal = false;
	}
	
	private static void runGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		Leaderboard test2b = new Leaderboard();
	}
	
	public class Clicker2 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e.getX());
			System.out.println(e.getY());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}