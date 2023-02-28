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
	private double playerSpeed;
	private Shape tetra;
	Checker cc;
	private RectanglePrism room;
	private ArrayList<Shape> shapes;
	Map<String, double[]> keyTracker = 
			new HashMap<String, double[]>();
	Engine3D(double angle, double distance){
		this.playerSpeed = 0.5;
		displayFrame = new JFrame("Dodger");
		displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = new Camera(angle,distance);
		shapes = new ArrayList<Shape>();
		Vertex v1 = new Vertex(10, 10, 10);
		Vertex v2 = new Vertex(-10, -10, 10);
		Vertex v3 = new Vertex(-10, 10, -10);
		Vertex v4 = new Vertex(10, -10, -10);
		Triangle[] ts = {new Triangle(v1,v3,v2,Color.WHITE),
				new Triangle(v1,v2,v4,Color.RED),
				new Triangle(v3,v4,v1,Color.GREEN),
				new Triangle(v3,v4,v2,Color.BLUE)};
		tetra = new Shape(ts, new double[] {30,30,-30});
		v1 = new Vertex(100, 100, 100);
		v2 = new Vertex(-100, 100, 100);
		v3 = new Vertex(-100, 100, -100);
		v4 = new Vertex(100, 100, -100);
		Vertex v5 = new Vertex(100, -100, 100);
		Vertex v6 = new Vertex(-100, -100, 100);
		Vertex v7 = new Vertex(-100, -100, -100);
		Vertex v8 = new Vertex(100, -100, -100);
		tetra = new Shape(new Triangle[] {new Triangle(v1,v4,v8,Color.BLUE)}, new double[] {0,0,0});
		room = new RectanglePrism(new Vertex[]{v1,v2,v3,v4,v5,v6,v7,v8},
				new double[]{0,0,0}, new Color[]{Color.BLUE,Color.BLUE,
						Color.CYAN,Color.CYAN,Color.GREEN,Color.MAGENTA});
		shapes.add(room);
		displayPane = new World();
		RotateCamera r = new RotateCamera();
		displayPane.addMouseMotionListener(r);
		displayPane.addMouseListener(r);
		cc = new Checker();
		displayPane.addMouseListener(cc);
//		displayPane.addMouseMotionListener(new MouseMotionListener() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                double yi = 180.0 / displayPane.getHeight();
//                double xi = 180.0 / displayPane.getWidth();
//                x[0] = (int) (e.getX() * xi);
//                y[0] = -(int) (e.getY() * yi);
//                displayPane.repaint();
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent e) {
//
//            }
//        });
		createActions();
		displayFrame.add(displayPane);
		displayFrame.setSize(600, 600);
		displayFrame.setResizable(false);
		displayFrame.setVisible(true);
	}
	public void createActions() {
		addMove("UP",0,0,-playerSpeed);
		addMove("DOWN",0,0,playerSpeed);
		addMove("X",0,playerSpeed,0);
		addMove("Z",0,-playerSpeed,0);
		addMove("LEFT",-playerSpeed,0,0);
		addMove("RIGHT",playerSpeed,0,0);
		addActions();
		addMove("released UP",0,0,0);
		addMove("released DOWN",0,0,0);
		addMove("released X",0,0,0);
		addMove("released Z",0,0,0);
		addMove("released LEFT",0,0,0);
		addMove("released RIGHT",0,0,0);
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
	class World extends JPanel {
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			BufferedImage img = new BufferedImage(getWidth(), getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			BufferedImage ig = new BufferedImage(getWidth(), getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics gg = ig.createGraphics();
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, getWidth(), getHeight());
			double[][] zBuffer = new double[img.getWidth()][img.getHeight()];
			for (int x= 0; x< img.getWidth(); x++) {
				for (int y = 0; y < img.getHeight(); y++) {
				    zBuffer[x][y] = Double.POSITIVE_INFINITY;
				}
			}
			for (Shape shape:shapes) {
				Shape tris = shape.changeCoords(tetra.localToWorld());
				tris = tris.changeCoords(c.worldToLocal());
				for (int i=0;i<tris.getTriangles().length;i++) {
					Triangle tri = tris.getTriangles()[i];
					Triangle[] t = {tri};
//					System.out.println("huk"+(!c.isVisible(tri,c.screenDistance)&&!c.isInvisible(tri,c.screenDistance)));
					if (!c.isVisible(tri,c.screenDistance)&&!c.isInvisible(tri, c.screenDistance)) {
						for (Vertex v:tri.v) {
//							if (v.z() == 0)
//								v.coordinate[2] = 0.001;
						}
						t = tri.clipTriangle(c.screenDistance);
					}
//						t[0].v1.print();
//						t[0].v2.print();
//						t[0].v3.print();
//						System.out.println("\n\n\n\n");
					cc.setT(0, tri,c,getWidth(),getHeight());
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
	                gg = img.getGraphics();
//	                gg.drawRect(minX, minY, maxX-minX, maxY-minY);
	                gg.drawLine((int)tempTri.getByX(0).x(), (int)tempTri.getByX(0).y(), (int)tempTri.getByX(1).x(), (int)tempTri.getByX(1).y());
	                gg.drawLine((int)tempTri.getByX(1).x(), (int)tempTri.getByX(1).y(), (int)tempTri.getByX(2).x(), (int)tempTri.getByX(2).y());
	                gg.drawLine((int)tempTri.getByX(2).x(), (int)tempTri.getByX(2).y(), (int)tempTri.getByX(0).x(), (int)tempTri.getByX(0).y());
				}
				}
			}
			g2.drawImage(img, 0, 0, null);
			g2.drawImage(ig, 0, 0, null);
		}
	}
	static double inTriangle(Vertex v1, Vertex v2, Vertex P){
			return (P.x() - v1.x()) * (v2.y() - v1.y()) - (P.y() - v1.y()) * (v2.x() - v1.x());
	}
	class MoveAction implements Runnable{
		
		public void run() {
			for(double[] d:keyTracker.values()) {
				c.localToWorld().translate(d[0], d[1], d[2]);
			}
			displayPane.repaint();
		}
	}
	class RotateCamera implements MouseMotionListener,MouseListener{
		double xStart;
		double yStart;
	    int xCoord;
	    int yCoord;
		Robot robot;
		public void mouseDragged(MouseEvent e) {
			double XZ = Math.toRadians((xCoord-e.getX())/5);
			double YZ = Math.toRadians((yCoord-e.getY())/5);
			AffineTransform3D v = new AffineTransform3D();
			v.rotateX(YZ);
			v.rotateY(XZ);
			c.setLocalToWorld(v.concatenate(c.localToWorld()));
			try {
			    // These coordinates are screen coordinates

			    // Move the cursor
			    robot = new Robot();
			    robot.mouseMove(xCoord, yCoord);
			} catch (AWTException a) {
			}
			displayPane.repaint();
		}
		public void mouseMoved(MouseEvent e) {
		}
		public void mouseClicked(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
			xStart=e.getX();
			yStart=e.getY();
			xCoord = 500;
		    yCoord = 500;
		}
		public void mouseReleased(MouseEvent e) {
			
			
		}
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
