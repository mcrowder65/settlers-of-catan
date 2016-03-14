package shared.definitions;

import java.util.ArrayList;

import shared.locations.*;
/**
 * class to wrap all the different resources 
 * Contains the number of the various resources held
 * @author Brennen
 *
 */
public class ResourceList {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brick;
		result = prime * result + ore;
		result = prime * result + sheep;
		result = prime * result + wheat;
		result = prime * result + wood;
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
		ResourceList other = (ResourceList) obj;
		if (brick != other.brick)
			return false;
		if (ore != other.ore)
			return false;
		if (sheep != other.sheep)
			return false;
		if (wheat != other.wheat)
			return false;
		if (wood != other.wood)
			return false;
		return true;
	}
	public int getRatio(ResourceType resource, ArrayList<Port> playerPorts){
		int ratio = 4;
		
		for(Port i : playerPorts){
			if(i.getRatio() == 3)
				ratio = 3;
		}
		for(Port i : playerPorts){
			if(i.getResource().equals(resource))
				ratio = i.getRatio();
		}
		return ratio;
	}
	public ResourceType[] getGiveableResources(ArrayList<Port> playerPorts){
		int ratio = 4;
		
		ArrayList<ResourceType> resources = new ArrayList<ResourceType>();
		ratio = getRatio(ResourceType.BRICK, playerPorts);
		if(brick >= ratio){
			resources.add(ResourceType.BRICK);
		}
		ratio = getRatio(ResourceType.ORE, playerPorts);
		if(ore >= ratio){
			resources.add(ResourceType.ORE);
		}
		
		ratio = getRatio(ResourceType.SHEEP, playerPorts);
		if(sheep >= ratio){
			resources.add(ResourceType.SHEEP);
		}
		
		ratio = getRatio(ResourceType.WHEAT, playerPorts);
		if(wheat >= ratio){
			resources.add(ResourceType.WHEAT);
		}
		
		ratio = getRatio(ResourceType.WOOD, playerPorts);
		if(wood >= ratio){
			resources.add(ResourceType.WOOD);
		}
		ResourceType[] resourceArray = new ResourceType[resources.size()];
		for(int i = 0; i < resources.size(); i++)
			resourceArray[i] = resources.get(i); //idk why... but toArray was giving me trouble - Matt
		return resourceArray;
	}
	/**
	 * Number of brick resources
	 */
	private int brick;
	/**
	 * Number of ore resources
	 */
	private int ore;
	/**
	 * Number of sheep resources
	 */
	private int sheep;
	/**
	 * Number of wheat resources
	 */
	private int wheat;
	/**
	 * Number of wood resources
	 */
	private int wood;
	
	/**
	 * Constructor for ResourceList
	 * Sets brick,ore,sheep,wheat, and wood
	 * @param brick
	 * @param ore
	 * @param sheep
	 * @param wheat
	 * @param wood
	 */
	public ResourceList(int brick, int ore, int sheep, int wheat, int wood) {
		this.brick = brick;
		this.ore = ore;
		this.sheep = sheep;
		this.wheat = wheat;
		this.wood = wood;
	}
	
	public boolean hasResource(ResourceType resource){
		if(resource == ResourceType.WOOD){
			if(wood >0){
				return true;
			}
			return false;
		}
		if(resource == ResourceType.BRICK){
			if(brick >0){
				return true;
			}
			return false;
		}
		if(resource == ResourceType.WHEAT){
			if(wheat >0){
				return true;
			}
			return false;
		}
		
		if(resource == ResourceType.ORE){
			if(ore >0){
				return true;
			}
			return false;
		}
		if(resource == ResourceType.SHEEP){
			if(sheep >0){
				return true;
			}
			return false;
		}
		return false;
	}
	
	public boolean hasResourceCertainNumber(ResourceType resource, int ratio){
		if(resource == ResourceType.WOOD){
			if(wood >=ratio){
				return true;
			}
			return false;
		}
		if(resource == ResourceType.BRICK){
			if(brick >=ratio){
				return true;
			}
			return false;
		}
		if(resource == ResourceType.WHEAT){
			if(wheat >=ratio){
				return true;
			}
			return false;
		}
		
		if(resource == ResourceType.ORE){
			if(ore >=ratio){
				return true;
			}
			return false;
		}
		if(resource == ResourceType.SHEEP){
			if(sheep >=ratio){
				return true;
			}
			return false;
		}
		return false;
	}
	/**
	 * Sets the number of Brick
	 * @param brick
	 */
	public void setBrick(int brick) throws IllegalArgumentException {
		if (brick < 0) throw new IllegalArgumentException("amount cannot be negative.");
		this.brick = brick;
	}
	/**
	 * Sets the number of ore
	 * @param ore
	 */
	public void setOre(int ore)throws IllegalArgumentException  {
		if (ore < 0) throw new IllegalArgumentException("amount cannot be negative.");
		this.ore = ore;
	}
	/**
	 * Sets the number of sheep
	 * @param sheep
	 */
	public void setSheep(int sheep)throws IllegalArgumentException  {
		if (sheep < 0) throw new IllegalArgumentException("amount cannot be negative.");
		this.sheep = sheep;
	}
	/**
	 * Sets the number of wheat
	 * @param wheat
	 */
	public void setWheat(int wheat)throws IllegalArgumentException  {
		if (wheat < 0) throw new IllegalArgumentException("amount cannot be negative.");
		this.wheat = wheat;
	}
	/**
	 * Sets the number of wood
	 * @param wood
	 */
	public void setWood(int wood) throws IllegalArgumentException {
		if (wood < 0) throw new IllegalArgumentException("amount cannot be negative.");
		this.wood = wood;
	}

	/**
	 * retrieves number of bricks 
	 * @return brick
	 */
	public int getBrick() {
		return brick;
	}
	
	/**
	 * retrieves number of ore 
	 * @return ore
	 */
	public int getOre() {
		return ore;
	}
	
	/**
	 * retrieves number of sheep 
	 * @return sheep
	 */
	public int getSheep() {
		return sheep;
	}
	
	/**
	 * retrieves number of wheat 
	 * @return wheat
	 */
	public int getWheat() {
		return wheat;
	}
	
	/**
	 * retrieves number of wood 
	 * @return wood
	 */
	public int getWood() {
		return wood;
	}
	
	public int getResource(ResourceType resourceType) {
		switch (resourceType) {
		case BRICK:
			return brick;
		case ORE:
			return ore;
		case SHEEP:
			return sheep;
		case WHEAT:
			return wheat;
		case WOOD:
			return wood;
		default:
			return -1;
		
		}
	}
	/**
	 * Used to add resource cards to the Bank
	 * @param resourceType = Type
	 * @param quantity = How many cards should be added 
	 */
	public void addResource(ResourceType resourceType, int quantity) {
		try
		{
			switch (resourceType) {
			case BRICK:
				setBrick(brick + quantity);
				break;
			case ORE:
				setOre(ore + quantity);
				break;
			case SHEEP:
				setSheep(sheep + quantity);
				break;
			case WHEAT:
				setWheat(wheat + quantity);
				break;
			case WOOD:
				setWood(wood + quantity);
				break;
			default:
				break;
			}
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
	}
	
	public void removeResource(ResourceType resourceType, int quantity){
	    try {
			switch (resourceType) {
			case BRICK:
				setBrick(brick - quantity);
				break;
			case ORE:
				setOre(ore - quantity);
				break;
			case SHEEP:
				setSheep(sheep - quantity);
				break;
			case WHEAT:
				setWheat(wheat - quantity);
				break;
			case WOOD:
				setWood(wood - quantity);
				break;
			default:
				break;
			}
	    } catch (IllegalArgumentException ex) {
	    	ex.printStackTrace();
	    }
	}
	
	/**
	 * Determines if the Bank is out of resources
	 * @return True if the bank is empty
	 */
	public boolean isEmpty(){
		if(brick > 0) return false;
		else if(ore > 0) return false;
		else if(sheep> 0) return false;
		else if(wheat > 0) return false;
		else if(wood > 0) return false;
		
		return true;
	}
	
	public int total() {
		return wood + brick + ore + sheep + wheat;
	}
	
	
}





































