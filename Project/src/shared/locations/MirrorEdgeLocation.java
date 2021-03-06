package shared.locations;

public class MirrorEdgeLocation {
	private int x;
	private int y;
	private MirrorEdgeDirection direction;
	
	public MirrorEdgeLocation(int x, int y, EdgeDirection direction)
	{
		setX(x);
		setY(y);
		setDirection(direction);
	}
	public MirrorEdgeLocation(EdgeLocation direction)
	{
		
		setX(direction.getHexLoc().getX());
		setY(direction.getHexLoc().getY());
		setDirection(direction.getDir());
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

	public MirrorEdgeDirection getDirection() {
		return direction;
	}

	public void setDirection(EdgeDirection direction) {
		this.direction = MirrorEdgeDirection.getMirrorEdge(direction);
	}
    public EdgeLocation getOriginal() {
    	EdgeLocation loc = new EdgeLocation(new HexLocation(x, y), MirrorEdgeDirection.getOriginal(direction));
    	return loc;
    }
}
