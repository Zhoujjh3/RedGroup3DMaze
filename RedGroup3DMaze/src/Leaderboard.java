import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;  

public class Leaderboard implements ActionListener {
	
	JTextField[] top10 = new JTextField[10];
	int scoresList[] = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
	int score;
	PlayerData player;
	
	JFrame frame;
	JPanel contentPane;
	JButton restartButt;
	JScrollPane scrollPane;
	JTextField title, yourScore;
	
	Leaderboard(PlayerData thePlayer) {
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
	    title.setBounds(400, 75, 250, 50);
	    title.setFont(new Font("Serif", Font.PLAIN, 40));
	    title.setBorder(BorderFactory.createEmptyBorder());
	    contentPane.add(title);
	    
	    yourScore = new JTextField("Your Score: " + Integer.toString(score));
	    yourScore.setFont(new Font("Serif", Font.PLAIN, 25));
	    yourScore.setEditable(false);
	    yourScore.setBounds(165, 500, 300, 50);
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
	    	top10[i].setFont(new Font("Serif", Font.PLAIN, 25));
	    }
	    
	    for (int i = 0; i < 5; i++) {
	    	top10[i].setBounds(140, (150 + i*65), 350, 50);
	    	top10[i + 5].setBounds(520, (150 + i*65), 350, 50);	    	
	    	contentPane.add(top10[i]);
	    	contentPane.add(top10[i + 5]);
	    }
	    
	    restartButt = new JButton("Restart");
	    restartButt.setFont(new Font("Serif", Font.PLAIN, 25));
	    restartButt.setBounds(545, 500, 300, 50);
	    restartButt.setBackground(new Color(150, 150, 148));
	    restartButt.setActionCommand("click");
	    restartButt.addActionListener(this);
	    contentPane.add(restartButt);

	    frame.setContentPane(contentPane);
	    frame.setSize(1000, 750);
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

	//sorts the top 10 scores from lowest to greatest
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
	
	//testing
	private static void runGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		Leaderboard testb = new Leaderboard(new PlayerData(1));
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
