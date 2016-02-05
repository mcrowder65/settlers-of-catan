package shared.locations;
/**
 * Wrapper for the owner and location of the edge
 * @author Brennen
 *
 */
public class EdgeValue {
	
	/**
	 * Index of the player that owns the edge 0-3
	 */
	private int owner;
	/**
	 * location of the edge (x and y coordinates + a direction)
	 */
	private EdgeLocation location;
	
	/**
	 * Constructor for EdgeValue
	 * sets the owner and location of the EdgeValue
	 * @param owner
	 * @param location
	 */
	public EdgeValue(int owner, EdgeLocation location) throws IllegalArgumentException  {
		
		this.owner = owner;
		this.location = location;
	}

	/**
	 * retrieves the owner which is a player index of the edge
	 * @return owner
	 */
	public int getOwner() {
		return owner;
	}

	/**
	 * retrieves the location (x and y coordinates + direction) of the location of the edge
	 * @return location
	 */
	public EdgeLocation getLocation() {
		return location;
	}

	/**
	 * Sets the owner of the edgeValue 
	 * Parameter should be a player id (0-3)
	 * @param owner
	 */
	public void setOwner(int owner) throws IllegalArgumentException  {
		this.owner = owner;
	}

	/**
	 * Sets the location of the edge
	 * Parameter should be an EdgeLocation which contains an x coordinate, a y coordinate, and a direction
	 * @param location
	 */
	public void setLocation(EdgeLocation location)  throws IllegalArgumentException {
		this.location = location;
	} 
	
	
	
	
	
	
}
