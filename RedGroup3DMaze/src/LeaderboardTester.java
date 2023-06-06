import javax.swing.*;

public class LeaderboardTester {
	JFrame frame;
	JPanel panel;
	
	LeaderboardTester() {
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 750);
		frame.setContentPane(panel);
		frame.setVisible(true);
        frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		
		Leaderboard test = new Leaderboard();
		test.display(frame, panel);
	}
	
	public static void main (String[] args){
        new LeaderboardTester();
    } 
}
