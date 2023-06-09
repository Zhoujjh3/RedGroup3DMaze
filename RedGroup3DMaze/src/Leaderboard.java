import javax.imageio.ImageIO;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Leaderboard implements ActionListener {
	
//	JTextField[] top10 = new JTextField[10];
	JTextField[] top10 = new JTextField[10];
	double scoresList[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	double score;
	static int width = 1000;
	static int height = 750;
	PlayerData player;
	
	JFrame frame;
	JPanel contentPane;
	JButton restartButt;
	JScrollPane scrollPane;
	JTextField yourScore;
	private Image restart;
	
	private boolean signal = false;
	
	public void getScore(double newScore) {
		score = newScore;
	}
	
	public void display(JFrame frame, JPanel contentPane/*GamePanel contentPane*/) {
		sortScores(score);
		
		this.frame = frame;
	    this.contentPane = contentPane;
		
	    contentPane.setLayout(null);
	    
	    
	    yourScore = new JTextField(Double.toString(score));
	    yourScore.setEditable(false);
	    yourScore.setHorizontalAlignment(JTextField.CENTER);
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
	    
	    restartButt = new JButton(new ImageIcon(new ImageIcon(getClass().getClassLoader().
				getResource("restart.png")).getImage().
				getScaledInstance(300, 75, java.awt.Image.SCALE_SMOOTH)));
	    restartButt.setOpaque(false);
	    restartButt.setContentAreaFilled(false);
	    restartButt.setBorderPainted(false);
	    restartButt.setActionCommand("click");
	    restartButt.addActionListener(this);
	    contentPane.add(restartButt);

	    //dynamic
	    Timer timer = new Timer();
	    TimerTask task = new TimerTask() {
	    	public void run() {
	    		int panelWidth = contentPane.getWidth();
	    		int panelHeight = contentPane.getHeight();
	    		
	    		for (int i = 0; i < 5; i++) {
	    			top10[i].setBounds((int)(panelWidth * 0.140), (int)(panelHeight * (0.2 + (i * 0.1))), (int)(panelWidth * 0.350), (int)(panelHeight * 0.0667));
	    			top10[i + 5].setBounds((int)(panelWidth * 0.520), (int)(panelHeight * (0.2 + (i * 0.1))), (int)(panelWidth * 0.350), (int)(panelHeight * 0.0667));
	    			
	    			top10[i].setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth * 0.04)));
	    			top10[i + 5].setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth * 0.04)));
	    		}
	    		
	    		yourScore.setBounds((int)(panelWidth * 0.165), (int)(panelHeight * 0.7), (int)(panelWidth * 0.3), (int)(panelHeight * 0.0667));
	    		yourScore.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth * 0.025)));
	    		
	    		restartButt.setBounds((int)(panelWidth * 0.545), (int)(panelHeight * 0.7), (int)(panelWidth * 0.3), (int)(panelHeight * 0.0667));
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
		if (e.getActionCommand() == "click") {
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
	
	//test2ing
	private static void runGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		Leaderboard test2b = new Leaderboard();
//		test2b.display(new JFrame(), new JPanel());
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				runGUI();
			}
		});
	}
}