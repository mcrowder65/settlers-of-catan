package shared.locations;

public class MirrorEdgeLocation {
	private int x;
	private int y;
	private EdgeDirection direction;
	
	public MirrorEdgeLocation(int x, int y, EdgeDirection direction)
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

	public EdgeDirection getDirection() {
		return direction;
	}

	public void setDirection(EdgeDirection direction) {
		this.direction = direction;
	}
}
