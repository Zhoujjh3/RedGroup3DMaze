package Learn3D;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

public class TimeToLearn {

	static int key;
	static double x = 0;
	static double y = 0;
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		JPanel renderPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.BLACK);
				g2.fillRect(0, 0,  getWidth(), getHeight());
				
				ArrayList<TriangleLearn> tris = new ArrayList<>();
				tris.add(new TriangleLearn(new VertexLearn(100, 100, 100), 
				new VertexLearn(-100, -100, 100), 
				new VertexLearn(-100, 100, -100), 
				Color.WHITE));
				tris.add(new TriangleLearn(new VertexLearn(100, 100, 100), 
				new VertexLearn(-100, -100, 100), 
				new VertexLearn(100, -100, -100), 
				Color.RED));
				tris.add(new TriangleLearn(new VertexLearn(-100, 100, -100), 
				new VertexLearn(100, -100, -100), 
				new VertexLearn(100, 100, 100), 
				Color.GREEN));
				tris.add(new TriangleLearn(new VertexLearn(-100, 100, -100), 
				new VertexLearn(100, -100, -100), 
				new VertexLearn(-100, -100, 100), 
				Color.BLUE));
				
				double heading = Math.toRadians(x);
				MatrixLearn headingTransform = new MatrixLearn(new double[] {
						Math.cos(heading), 0, -Math.sin(heading), 
						0, 1, 0, 
						Math.sin(heading), 0 , Math.cos(heading)
				});
				double pitch = Math.toRadians(y);
				MatrixLearn pitchTransform = new MatrixLearn(new double[] {
						1, 0, 0,
						0, Math.cos(pitch), Math.sin(pitch),
						0, -Math.sin(pitch), Math.cos(pitch)
				});
				MatrixLearn transform = headingTransform.multiply(pitchTransform);
				
				double[] zBuffer = new double[img.getWidth() * img.getHeight()];
				for(int q =0; q < zBuffer.length; q++) {
					zBuffer[q] = Double.NEGATIVE_INFINITY;
				}
				
				for(TriangleLearn t : tris) {
					VertexLearn v1 = transform.transform(t.vertices[0]);
					VertexLearn v2 = transform.transform(t.vertices[1]);
					VertexLearn v3 = transform.transform(t.vertices[2]);
					v1.coordinates[0] += getWidth() / 2.0;
					v1.coordinates[1] += getHeight() / 2.0;
					v2.coordinates[0] += getWidth() / 2.0;
					v2.coordinates[1] += getHeight() / 2.0;
					v3.coordinates[0] += getWidth() / 2.0;
					v3.coordinates[1] += getHeight() / 2.0;
					int minX = (int) Math.max(0, Math.ceil(Math.min(v1.coordinates[0], 
					Math.min(v2.coordinates[0], v3.coordinates[0]))));
					int maxX = (int) Math.min(img.getWidth() - 1, Math.floor(Math.max(v1.coordinates[0], 
					Math.max(v2.coordinates[0], v3.coordinates[0]))));
					int minY = (int) Math.max(0, Math.ceil(Math.min(v1.coordinates[1], 
					Math.min(v2.coordinates[1], v3.coordinates[1]))));
					int maxY = (int) Math.min(img.getHeight() - 1, Math.floor(Math.max(v1.coordinates[1], 
					Math.max(v2.coordinates[1], v3.coordinates[1]))));
					
					for(int y = minY; y <= maxY; y++) {
						for(int x = minX; x <= maxX; x++) {
							VertexLearn p = new VertexLearn(x, y, 0);
							
							boolean V1 = sameSide(v1, v2, v3, p);
							boolean V2 = sameSide(v2, v3, v1, p);
							boolean V3 = sameSide(v3, v1, v2, p);
							if(V3 && V2 && V1) {
								double depth = v1.coordinates[2] + v2.coordinates[2] + v3.coordinates[2];
								int zIndex = y * img.getWidth() + x;
								if(zBuffer[zIndex] < depth) {
									img.setRGB(x,  y,  t.theColor.getRGB());
									zBuffer[zIndex] = depth;
								}
							}
						}
					}
					
				}
				
				g2.drawImage(img, 0, 0, null);
			}
			
		};
		
		pane.add(renderPanel, BorderLayout.CENTER);
		renderPanel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				double yi = 180.0 / renderPanel.getHeight();
				double xi = 180.0 / renderPanel.getWidth();
				x = (int) (e.getX() * xi);
				y = -(int) (e.getY() * yi);
				renderPanel.repaint();
			}
			
			public void mouseMoved(MouseEvent e) {
				
			}
		});
		renderPanel.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				key = e.getKeyCode();
				animation.start();
			}
		});
		frame.setSize(600, 600);
		frame.setVisible(true);
		
	}
	
	static ActionListener moveDaTriangle = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (key == KeyEvent.VK_LEFT) {
			
			} else if (key == KeyEvent.VK_RIGHT) {
				
			}
		}
	};
	
	static Timer animation = new Timer(15, moveDaTriangle);
	
	static boolean sameSide(VertexLearn A, VertexLearn B, VertexLearn C, VertexLearn p) {
		VertexLearn V1V2 = new VertexLearn(B.coordinates[0] - A.coordinates[0], 
		B.coordinates[1] - A.coordinates[1], B.coordinates[2] - A.coordinates[2]);
		VertexLearn V1V3 = new VertexLearn(C.coordinates[0] - A.coordinates[0], 
		C.coordinates[1] - A.coordinates[1], C.coordinates[2] - A.coordinates[2]);
		VertexLearn V1P = new VertexLearn(p.coordinates[0] - A.coordinates[0], 
		p.coordinates[1] - A.coordinates[1], p.coordinates[2] - A.coordinates[2]);
		
		double V1V2CrossV1V3 = V1V2.coordinates[0] * V1V3.coordinates[1] - V1V3.coordinates[0] * V1V2.coordinates[1];
		double V1V2CrossP = V1V2.coordinates[0] * V1P.coordinates[1] - V1P.coordinates[0] * V1V2.coordinates[1];

		return V1V2CrossV1V3 * V1V2CrossP >= 0;
	}
	
}

