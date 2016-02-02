package shared.definitions;

import shared.locations.*;
/**
 * class to wrap all the different resources 
 * Contains the number of the various resources held
 * @author Brennen
 *
 */
public class ResourceList {
	
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
	
	/**
	 * Sets the number of Brick
	 * @param brick
	 */
	public void setBrick(int brick) throws IllegalArgumentException {
		this.brick = brick;
	}
	/**
	 * Sets the number of ore
	 * @param ore
	 */
	public void setOre(int ore)throws IllegalArgumentException  {
		this.ore = ore;
	}
	/**
	 * Sets the number of sheep
	 * @param sheep
	 */
	public void setSheep(int sheep)throws IllegalArgumentException  {
		this.sheep = sheep;
	}
	/**
	 * Sets the number of wheat
	 * @param wheat
	 */
	public void setWheat(int wheat)throws IllegalArgumentException  {
		this.wheat = wheat;
	}
	/**
	 * Sets the number of wood
	 * @param wood
	 */
	public void setWood(int wood) throws IllegalArgumentException {
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
	
	
	
}
