import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClicker implements MouseListener {
	private int lastX = -1;
	private int lastY = -1;
	
	public int getLastX() {
		return lastX;
	}
	
	public int getLastY() {
		return lastY;
	}
	
	public void mouseClicked(MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY();
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
}