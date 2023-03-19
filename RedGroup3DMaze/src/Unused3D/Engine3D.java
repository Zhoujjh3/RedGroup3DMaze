package Unused3D;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;
import javax.swing.Timer;

public class Engine3D {
	private JFrame displayFrame;
	private JPanel displayPane;
	private ScheduledThreadPoolExecutor timer;
	private ScheduledFuture<?> ghostTimer;
	private Camera c;
	//private PlayerData player;
	private double playerSpeed;
	boolean running;
	
	private ArrayList<Shape> shapes;
	Map<String, double[]> keyTracker = 
			new HashMap<String, double[]>();
	Engine3D(double angle, double distance){
		this.playerSpeed = 0.5;
		displayFrame = new JFrame("Dodger");
		displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = new Camera(angle,distance);
		//player = new PlayerData(4);
		shapes = new ArrayList<Shape>();
		Vertex v1 = new Vertex(10, 10, 10);
		Vertex v2 = new Vertex(-10, -10, 10);
		Vertex v3 = new Vertex(-10, 10, -10);
		Vertex v4 = new Vertex(10, -10, -10);
		Triangle[] ts = {new Triangle(v1,v3,v2,Color.WHITE),
				new Triangle(v1,v2,v4,Color.RED),
				new Triangle(v3,v4,v1,Color.GREEN),
				new Triangle(v3,v4,v2,Color.BLUE)};
		AffineTransform3D orient = new AffineTransform3D();
		RoomShapes room = new RoomShapes(new int[] {0,0,0}, new boolean[]
				{true,false, true, false, true, false});
		RoomShapes room2 = new RoomShapes(new int[] {1,0,0}, new boolean[]
				{true,false, true, false, true, false});
		shapes = room.walls;
		shapes.addAll(room2.walls);
		displayPane = new World();
		createActions();
		displayFrame.add(displayPane);
		displayFrame.setSize(600, 600);
		displayFrame.setVisible(true);
	}
	public void createActions() {
		addMove("UP",0,0,-playerSpeed);
		addMove("DOWN",0,0,playerSpeed);
		addMove("X",0,playerSpeed,0);
		addMove("Z",0,-playerSpeed,0);
		Action newAction = new CameraRotateHori("LEFT");
		KeyStroke key = KeyStroke.getKeyStroke("LEFT");
		displayPane.getInputMap().put(key, "LEFT");
		displayPane.getActionMap().put("LEFT", newAction);
		newAction = new CameraRotateHori("RIGHT");
		key = KeyStroke.getKeyStroke("RIGHT");
		displayPane.getInputMap().put(key, "RIGHT");
		displayPane.getActionMap().put("RIGHT", newAction);
		newAction = new MoveCameraForward("SPACE");
		key = KeyStroke.getKeyStroke("SPACE");
		displayPane.getInputMap().put(key, "SPACE");
		displayPane.getActionMap().put("SPACE", newAction);
		addActions();
		addMove("released UP",0,0,0);
		addMove("released DOWN",0,0,0);
		addMove("released X",0,0,0);
		addMove("released Z",0,0,0);
		timer = new ScheduledThreadPoolExecutor(2);
		timer.scheduleAtFixedRate(new Refresh(), 0, 5, TimeUnit.MILLISECONDS);
	}
	public void addMove(String keyStr,String name,double x,double y, double z){
		Action newAction = new MoveCamera(name,x,y, z);
		KeyStroke key = KeyStroke.getKeyStroke(keyStr);
		displayPane.getInputMap().put(key, name);
		displayPane.getActionMap().put(name, newAction);
	}
	public void addActions(){
		KeyStroke key = KeyStroke.getKeyStroke("W");
		displayPane.getInputMap().put(key, "UP");
		key = KeyStroke.getKeyStroke("released W");
		displayPane.getInputMap().put(key, "released UP");
	}
	public void addMove(String name, double x, double y,double z){
		addMove(name,name,x,y,z);
	}
	class MoveCamera extends AbstractAction{
		private String name;
		private double xShift;
		private double yShift;
		private double zShift;
		MoveCamera(String name, double x, double y, double z){
			super(name);
			this.name = name;
			xShift = x;
			yShift = y;
			zShift = z;
		}
		public void actionPerformed(ActionEvent e) {
			if(name.contains("release")) {
				keyTracker.remove(name.replace("released ", ""));
				if(keyTracker.isEmpty()){
					ghostTimer.cancel(false);
				}
			}else {
				if(keyTracker.isEmpty())
					ghostTimer = timer.scheduleWithFixedDelay
							(new MoveAction(), 0, 5, TimeUnit.MILLISECONDS);
				keyTracker.put(name, new double[]{xShift, yShift,zShift});
			}
		}
	}

	class MoveCameraForward extends AbstractAction{
		private String name;
		MoveCameraForward(String name){
			super(name);
			this.name = name;
		}
		public void actionPerformed(ActionEvent e) {
			Timer t = new Timer(10, null);
			ActionListener rotate = new ActionListener() {
				int i=0;
				@Override
				public void actionPerformed(ActionEvent e) {
					c.localToWorld().relativeTranslate(0, 0, -10);
					i++;
					displayPane.repaint();
					if (i==20) {
						t.stop();
						running = false;
					}
				}
			};
			t.addActionListener(rotate);
			t.setRepeats(true);
			if(!running)
			t.start();
		}
	}
	class CameraRotateHori extends AbstractAction{
		private String name;
		private double rotation;
		CameraRotateHori(String name){
			super(name);
			if (name == "LEFT")
				rotation = -Math.PI/40;
			else
				rotation = Math.PI/40;
		}
		public void actionPerformed(ActionEvent e) {
			Timer t = new Timer(10, null);
			ActionListener rotate = new ActionListener() {
				int i=0;
				@Override
				public void actionPerformed(ActionEvent e) {
					running = true;
//					player.rotateHori(name);
					AffineTransform3D movement = new AffineTransform3D();
//					c.localToWorld().translate(0, 0, 30);
					movement = movement.rotateY(rotation);
					c.setLocalToWorld(movement.concatenate(c.localToWorld()));
//					c.localToWorld().print();
//					c.localToWorld().translate(0, 0, -30);
//					System.out.println(i);
					i++;
					displayPane.repaint();
					if (i==20) {
						t.stop();
						running = false;
					}
				}
			};
			t.addActionListener(rotate);
			t.setRepeats(true);
			if(!running)
			t.start();
		}
	}
	class World extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			BufferedImage img = new BufferedImage(getWidth(), getHeight(),
					BufferedImage.TYPE_INT_ARGB);
//			BufferedImage ig = new BufferedImage(getWidth(), getHeight(),
//					BufferedImage.TYPE_INT_ARGB);
//			Graphics gg = ig.createGraphics();
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, getWidth(), getHeight());
			double[][] zBuffer = new double[img.getWidth()][img.getHeight()];
			for (int x= 0; x< img.getWidth(); x++) {
				for (int y = 0; y < img.getHeight(); y++) {
				    zBuffer[x][y] = Double.POSITIVE_INFINITY;
				}
			}
			for (Shape shape:shapes) {
				Shape tris = shape.changeCoords(shape.localToWorld());
				tris = tris.changeCoords(c.worldToLocal());
				for (int i=0;i<tris.getTriangles().length;i++) {
					Triangle tri = tris.getTriangles()[i];
					Triangle[] t = {tri};
					if (!c.isVisible(tri,c.screenDistance)&&!c.isInvisible(tri, c.screenDistance)) {
						for (Vertex v:tri.v) {
						}
						t = tri.clipTriangle(c.screenDistance);
					}
					if(!c.isInvisible(tri, c.screenDistance))
					for (Triangle tempTri:t) {
					tempTri = c.worldToRaster(tempTri,getWidth(),getHeight());
					int minX = (int) Math.max(0, Math.ceil(tempTri.getByX(0).x()));
					int maxX = (int) Math.min(img.getWidth() - 1,tempTri.getByX(2).x());
					int minY = (int) Math.max(0, Math.ceil(tempTri.getByY(0).y()));
					int maxY = (int) Math.min(img.getHeight() - 1,tempTri.getByY(2).y());
	                tempTri.toClockwise();
	                for (int y = minY; y <= maxY; y++) {
						for (int x = minX; x <= maxX; x++) {
							Vertex p = new Vertex(x,y,0);
							double triArea = inTriangle(tempTri.v1,tempTri.v2,tempTri.v3);
							double bary1 = inTriangle(tempTri.v2,tempTri.v3,p);
							double bary2 = inTriangle(tempTri.v3,tempTri.v1,p);
							double bary3 = inTriangle(tempTri.v1,tempTri.v2,p);
							if (bary1>=0&&bary2>= 0&&bary3>= 0) {
								bary1/=triArea;
								bary2/=triArea;
								bary3/=triArea;
								double depth = 1/(bary1/tempTri.v1.z()+bary2/tempTri.v2.z()+bary3/tempTri.v3.z());
								if (zBuffer[x][y] > depth&&(depth>c.screenDistance)) {
									img.setRGB((int)x, (int)y, tri.color.getRGB());
									zBuffer[x][y] = depth;
								}else if (zBuffer[x][y] == depth&&!(tempTri.getByZ(2).z()< depth)) {
								}
							}
						}
					}
//	                gg = img.getGraphics();
//	                gg.drawRect(minX, minY, maxX-minX, maxY-minY);
//	                gg.drawLine((int)tempTri.getByX(0).x(), (int)tempTri.getByX(0).y(), (int)tempTri.getByX(1).x(), (int)tempTri.getByX(1).y());
//	                gg.drawLine((int)tempTri.getByX(1).x(), (int)tempTri.getByX(1).y(), (int)tempTri.getByX(2).x(), (int)tempTri.getByX(2).y());
//	                gg.drawLine((int)tempTri.getByX(2).x(), (int)tempTri.getByX(2).y(), (int)tempTri.getByX(0).x(), (int)tempTri.getByX(0).y());
				}
				}
			}
			g2.drawImage(img, 0, 0, null);
//			g2.drawImage(ig, 0, 0, null);
		}
	}
	static double inTriangle(Vertex v1, Vertex v2, Vertex P){
			return (P.x() - v1.x()) * (v2.y() - v1.y()) - (P.y() - v1.y()) * (v2.x() - v1.x());
	}
	class MoveAction implements Runnable{
		
		public void run() {
			for(double[] d:keyTracker.values()) {
				c.localToWorld().relativeTranslate(d[0], d[1], d[2]);
			}
			displayPane.repaint();
		}
	}
	class Refresh implements Runnable{
		public void run() {
		}
	}
	public static void main(String[] args) {
		Engine3D test = new Engine3D(Math.PI/2,0.01);
	}
}
