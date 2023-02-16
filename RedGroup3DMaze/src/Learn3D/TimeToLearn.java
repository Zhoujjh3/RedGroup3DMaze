package Learn3D;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
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
				
				g2.translate(getWidth() / 2, getHeight() / 2);
				g2.setColor(Color.WHITE);
				for(TriangleLearn t : tris) {
					VertexLearn v1 = transform.transform(t.vertices[0]);
					VertexLearn v2 = transform.transform(t.vertices[1]);
					VertexLearn v3 = transform.transform(t.vertices[2]);
					Path2D path = new Path2D.Double();
					path.moveTo(v1.coordinates[0], v1.coordinates[1]);
					path.lineTo(v2.coordinates[0], v2.coordinates[1]);
					path.lineTo(v3.coordinates[0], v3.coordinates[1]);
					path.closePath();
					g2.draw(path);
				}
				
				
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
	
}

