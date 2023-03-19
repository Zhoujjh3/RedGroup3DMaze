package Unused3D;
public abstract class Entity {
	private AffineTransform3D l2w;
	public AffineTransform3D localToWorld() {
		return l2w;
	}
	public AffineTransform3D worldToLocal(){
		return l2w.invert();
	}
	public void setLocalToWorld(AffineTransform3D t) {
		l2w = t;
	}
}