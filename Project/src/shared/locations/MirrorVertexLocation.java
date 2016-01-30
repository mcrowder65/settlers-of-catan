package shared.locations;

/**
 * Represents the location of a vertex on a hex map
 */
public class MirrorVertexLocation
{
	
	private int x;
	private int y;
	private VertexDirection direction;
	
	public MirrorVertexLocation(int x, int y, VertexDirection direction)
	{
		setX(x);
		setY(y);
		setDirection(direction);
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



	public VertexDirection getDirection() {
		return direction;
	}



	public void setDirection(VertexDirection direction) {
		this.direction = direction;
	}

}

