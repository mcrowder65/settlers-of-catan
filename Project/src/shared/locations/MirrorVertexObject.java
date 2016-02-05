package shared.locations;
/**
 * 
 * @author Brennen
 * Vertex Object represents Cities and Settlements 
 * It contains an owner and has an EdgeLocation
 */
public class MirrorVertexObject {

	/**
	 * Player index that owns the object (0-3)
	 */
	private int owner; 
	/**
	 * location of the edge (x and y coordinates + a direction)
	 */
	private MirrorVertexLocation location;
	
	/**
	 * Constructor for VertexObject
	 * Sets the owner and location of the object
	 * @param owner
	 * @param location
	 */
	public MirrorVertexObject(int owner, MirrorVertexLocation location) throws IllegalArgumentException  {
		this.owner = owner;
		this.location = location;
	}

	public MirrorVertexObject() {
	}

	/**
	 * retrieves the owner of the city or settlement
	 * @return owner
	 */
	public int getOwner() {
		return owner;
	}

	/**
	 * retrieves the location of the vertexObject 
	 * Contains an x coordinate, a y coordinate, and a direction
	 * @return location
	 */
	public MirrorVertexLocation getLocation() {
		return location;
	}

	/**
	 * Sets the owner of the VertexObject 
	 * Parameter should be a player id (0-3)
	 * @param owner
	 */
	public void setOwner(int owner)  throws IllegalArgumentException  {
		this.owner = owner;
	}
	/**
	 * Sets the location of the VertexObject
	 * Parameter should be an EdgeLocation which contains an x coordinate, a y coordinate, and a direction
	 * @param location
	 */
	public void setLocation(MirrorVertexLocation location) throws IllegalArgumentException  {
		this.location = location;
	}
	
	
	
	
	
	
	
	
}
