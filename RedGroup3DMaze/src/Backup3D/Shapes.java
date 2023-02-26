package Backup3D;
import java.awt.Graphics;

public abstract class Shapes {
	double xTL, xTR, xBR, xBL;
	double yTL, yTR, yBR, yBL;
	
	public abstract void paint(Graphics g);
	public abstract void update();
	public abstract int getState();
	public abstract boolean getDir();
	public abstract void setState(int theState);
	public abstract void setDir(boolean theDir);
	
	public double getxTL() {return xTL;}
	public double getxBL() {return xBL;}
	public double getxTR() {return xTR;}
	public double getxBR() {return xBR;}
	public double getyTL() {return yTL;}
	public double getyBL() {return yBL;}
	public double getyTR() {return yTR;}
	public double getyBR() {return yBR;}
}
