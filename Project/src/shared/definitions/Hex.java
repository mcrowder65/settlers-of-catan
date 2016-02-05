package shared.definitions;

import shared.locations.HexLocation;

/**
 * class to represent a Hex on the board
 * Contains a location, a resource, and the number that goes on that particular hex
 * @author bbarney7
 *
 */
public class Hex {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + number;
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hex other = (Hex) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (number != other.number)
			return false;
		if (resource != other.resource)
			return false;
		return true;
	}

	/**
	 * x and y corrdinates of the hex
	 */
	private HexLocation location; 
	/**
	 * resource that the hex represents and offers
	 */
	private ResourceType resource;
	/**
	 * Number to be rolled to receive resources of the hex
	 */
	private int number; //number of resources allocated by the hex
	
	/**
	 * Constructor for class Hex - sets the private variables in the class
	 * @param location
	 * @param resource
	 * @param number
	 */
	public Hex(HexLocation location, ResourceType resource, int number) throws IllegalArgumentException  {
		
		this.location = location;
		this.resource = resource;
		this.number = number;
	}
	
	/**
	 * gets the location of hex
	 * @return location
	 */
	public HexLocation getLocation() {
		return location;
	}
	
	/**
	 * gets the resource that the hex represents
	 * @return resource 
	 */
	public ResourceType getResource() {
		return resource;
	}
	
	/**
	 * gets the number that is allocated by the hex
	 * @return number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets the location of the hex
	 * @param location
	 */
	public void setLocation(HexLocation location) throws IllegalArgumentException  {
		this.location = location;
	}

	/**
	 * Sets the resource that the hex represents
	 * @param resource
	 */
	public void setResource(ResourceType resource) throws IllegalArgumentException  {
		this.resource = resource;
	}

	/**
	 * Sets number of resources allocated by the hex
	 * @param number
	 */
	public void setNumber(int number) throws IllegalArgumentException {
		this.number = number;
	}
	
	
	
	
	
	
	
}
