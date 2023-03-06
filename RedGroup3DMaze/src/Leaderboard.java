import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Dimension;

public class Leaderboard implements ActionListener {
	
	JTextField[] top10 = new JTextField[10];
	int scoresList[] = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
	int score;
	static int width = 1000;
	static int height = 750;
	PlayerData player;
	
	JFrame frame;
	JPanel contentPane;
	JButton restartButt;
	JScrollPane scrollPane;
	JTextField title, yourScore;
	
	Leaderboard (PlayerData thePlayer) {
		score = thePlayer.getScore();
		if (score != 0) {
			sortScores(score);
		}
		
		frame = new JFrame("Leaderboard");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    contentPane = new JPanel();
	    contentPane.setLayout(null);
	    
	    title = new JTextField("Leaderboard");
	    title.setEditable(false);
	    title.setBorder(BorderFactory.createEmptyBorder());
	    contentPane.add(title);
	    
	    yourScore = new JTextField("Your Score: " + Integer.toString(score));
	    yourScore.setEditable(false);
	    yourScore.setHorizontalAlignment(JTextField.CENTER);
	    contentPane.add(yourScore);
	    
	    for (int i = 0; i < top10.length; i++) {
	    	if (scoresList[i] == 2147483647) {
	    		top10[i] = new JTextField("---");
	    	} else {
	    		top10[i] = new JTextField(Integer.toString(scoresList[i]));
	    	}
	    	top10[i].setHorizontalAlignment(JTextField.CENTER);
	    	top10[i].setEditable(false);
	    	contentPane.add(top10[i]);
	    }
	    
	    restartButt = new JButton("Restart");
	    restartButt.setBackground(new Color(150, 150, 148));
	    restartButt.setActionCommand("click");
	    restartButt.addActionListener(this);
	    contentPane.add(restartButt);

	    //dynamic
	    Timer timer = new Timer();
	    TimerTask task = new TimerTask() {
	    	public void run() {
	    		int panelWidth = contentPane.getWidth();
	    		int panelHeight = contentPane.getHeight();
	    		
	    		title.setBounds((int)(panelWidth * 0.4), (int)(panelHeight * 0.08), (int)(panelWidth * 0.275), (int)(panelHeight * 0.08));
	    		title.setFont(new Font("Serif", Font.PLAIN, (int)(panelWidth * 0.04)));
	    		
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
	    frame.setContentPane(contentPane);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public void restartGame() {
//		Selection newGame = new Selection();
	}
	
	//if the new score is lower than the 10th score, the 10th score is removed
	public void sortScores(int newScore) {
		bubbleSort();	
		if (newScore < scoresList[scoresList.length - 1]) {
			scoresList[scoresList.length - 1] = newScore;
			bubbleSort();
		}
	}

	//sorts the top 10 scores from lowest to greatest2
	private void bubbleSort() {
		int temp;
		for (int i = 0; i < scoresList.length; i++) {
			for (int j = i + 1; j < scoresList.length; j++) {
				if (scoresList[j] < scoresList[i]) {
					temp = scoresList[i];
					scoresList[i] = scoresList[j];
					scoresList[j] = temp;
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "click") {
			restartGame();
		}
	}
	
	//test2ing
	private static void runGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		Leaderboard test2b = new Leaderboard(new PlayerData(1));
	}
	
	public static void main(String[] args) {
		/*
		 * Methods that create and show a GUI should be
		 * run from an event-dispatching thread
		 */
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				runGUI();
			}
		});
	}
}