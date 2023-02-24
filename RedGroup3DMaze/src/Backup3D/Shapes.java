package Backup3D;
import java.awt.Graphics;

public abstract class Shapes {
	public abstract void paint(Graphics g);
	public abstract void update();
	public abstract int getState();
	public abstract boolean getDir();
	public abstract void setState(int theState);
	public abstract void setDir(boolean theDir);
}
