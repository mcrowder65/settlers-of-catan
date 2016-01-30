package shared.definitions;

import shared.locations.*;
/**
 * Class to represent the ports on the board
 * @author Brennen
 *
 */
public class Port {
	
	/**
	 * Type of resource the port has a good trade ratio on
	 */
	private ResourceType resource;
	/**
	 * Hex that the port is on
	 */
	private HexLocation location;
	/**
	 * the edge of the hex that the port is on
	 */
	private EdgeDirection direction; 
	/**
	 * the special trade ratio that the port offers
	 */
	private int ratio;
	
	/**
	 * Constructor to initiate variables for Ports
	 * @param resource
	 * @param location
	 * @param direction
	 * @param ratio
	 */
	public Port(ResourceType resource, HexLocation location, EdgeDirection direction,
			int ratio) {
		
		this.resource = resource;
		this.location = location;
		this.direction = direction;
		this.ratio = ratio;
	}

	/**
	 * retrieves the resource for which the port has a special trade ratio
	 * @return resource
	 */
	public ResourceType getResource() {
		return resource;
	}

	/**
	 * returns the location where the port is located
	 * @return location
	 */
	public HexLocation getLocation() {
		return location;
	}

	/**
	 * retrieves the direction of Hex which the port is on
	 * @return direction
	 */
	public EdgeDirection getDirection() {
		return direction;
	}

	/**
	 * returns the trade ratio at which players can trade if they have this port
	 * @return ratio
	 */
	public int getRatio() {
		return ratio;
	}
	/**
	 * Sets the resource of the port
	 * @param resource
	 */
	public void setResource(ResourceType resource) throws IllegalArgumentException  {
		this.resource = resource;
	}
	/**
	 * Sets the hex location of the port
	 * @param location
	 */
	public void setLocation(HexLocation location) throws IllegalArgumentException  {
		this.location = location;
	}
	/**
	 * Sets the direction of the port
	 * @param direction
	 */
	public void setDirection(EdgeDirection direction)  {
		this.direction = direction;
	}
	/**
	 * Sets the trade ratio of the port
	 * @param ratio
	 */
	public void setRatio(int ratio) throws IllegalArgumentException  {
		this.ratio = ratio;
	}
	
	
	
	
	
}
