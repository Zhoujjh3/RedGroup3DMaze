import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class ColorTester {
	public static void main(String[] args) {
		new ColorTester();
	}

	ColorTester() {
		JFrame frame = new JFrame();

		frame.add(new MyComponent());

		frame.setSize(300, 300);
		frame.setVisible(true);
	}

}

class MyComponent extends JComponent {

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Color startColor = Color.red;
		Color endColor = Color.blue;

		int startX = 10, startY = 20, endX = 150, endY = 40;

		GradientPaint gradient = new GradientPaint(startX, startY, startColor, endX, endY, endColor);
		g2d.setPaint(gradient);

		g2d.fill(new Rectangle(20, 20, 200, 200));

	}
}