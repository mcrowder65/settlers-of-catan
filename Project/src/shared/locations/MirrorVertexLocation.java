package shared.locations;

/**
 * Represents the location of a vertex on a hex map
 */
public class MirrorVertexLocation
{
	
	private int x;
	private int y;
	private MirrorVertexDirection direction;
	
	public MirrorVertexLocation(int x, int y, VertexDirection direction)
	{
		setX(x);
		setY(y);
		setDirection(direction);
	}
	public MirrorVertexLocation(VertexLocation location) {
		setX(location.getHexLoc().getX());
		setY(location.getHexLoc().getY());
		setDirection(location.getDir());
	}
	
	
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public MirrorVertexDirection getDirection() {
		return direction;
	}



	public void setDirection(VertexDirection direction) {
		this.direction = MirrorVertexDirection.getMirrorEdge(direction);
	}

}

