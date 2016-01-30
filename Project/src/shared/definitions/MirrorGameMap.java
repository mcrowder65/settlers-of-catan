package shared.definitions;

import shared.locations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Class to represent the entire map
 * Contains Hexes, ports, roads, settlemetns, cities, a radius, and a robber
 * @author Brennen
 *
 */
public class MirrorGameMap {
	
	/**
	 * Array of all the hexes on the map
	 */
	private Hex[] hexes;
	/**
	 * Array of all the ports on the map
	 */
	private Port[] ports;
	/**
	 * Array of all the roads on the map
	 */
	private MirrorEdgeValue[] roads;
	/**
	 * Array of all the settlements on the map
	 */
	private MirrorVertexObject[] settlements;
	/**
	 * Array of all the cities on the map
	 */
	private MirrorVertexObject[] cities;
	/**
	 * Radius of the map
	 */
	private int radius;
	/**
	 * Location of the robber on the map
	 */
	private HexLocation robber;
	
	/**
	 * Constructor for map object
	 * Initializes hexes,ports,roads,settlements,cities, and radius 
	 * @param hexes
	 * @param ports
	 * @param roads
	 * @param settlements
	 * @param cities
	 * @param radius
	 * @param robber
	 */
	public MirrorGameMap(Hex[] hexes, Port[] ports, MirrorEdgeValue[] roads,
			MirrorVertexObject[] settlements, MirrorVertexObject[] cities, int radius,
			HexLocation robber) throws IllegalArgumentException  {
	
		this.hexes = hexes;
		this.ports = ports;
		this.roads = roads;
		this.settlements = settlements;
		this.cities = cities;
		this.radius = radius;
		this.robber = robber;
	}
	

	public MirrorGameMap() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * gets an array of all the Hexes
	 * @return hexes
	 */
	public Hex[] getHexes() {
		return hexes;
	}

	/**
	 * gets an array of all the ports on the map
	 * @return ports
	 */
	public Port[] getPorts() {
		return ports;
	}

	/**
	 * gets an array of all the roads on the map
	 * @return roads
	 */
	public MirrorEdgeValue[] getRoads() {
		return roads;
	}

	/**
	 * gets an array of all the settlements on the map
	 * @return settlements 
	 */
	public MirrorVertexObject[] getSettlements() {
		return settlements;
	}

	/**
	 * gets an array of all the cities on the map
	 * @return cities
	 */
	public MirrorVertexObject[] getCities() {
		return cities;
	}

	/**
	 * gets the radius of the map
	 * @return radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * gets the Hexlocation of the robber
	 * @return robber
	 */
	public HexLocation getRobber() {
		return robber;
	}
	/**
	 * Determines if the player has a road built in a location
	 * personalRaod contains the location where the road is to be built and the player index of the player that wants to build it
	 * @param personalRoad
	 * @return true or false
	 */
	
	/**
	 * Sets all of the hexes on the map
	 * @param hexes
	 */
	public void setHexes(Hex[] hexes) throws IllegalArgumentException  {
		this.hexes = hexes;
	}
	/**
	 * Sets all the ports on the map
	 * @param ports
	 */
	public void setPorts(Port[] ports) throws IllegalArgumentException  {
		this.ports = ports;
	}
	/**
	 * sets all the roads on the map
	 * @param roads
	 */
	public void setRoads(MirrorEdgeValue[] roads) throws IllegalArgumentException  {
		this.roads = roads;
	}
	/**
	 * sets all the settlements on the map
	 * @param settlements
	 */
	public void setSettlements(MirrorVertexObject[] settlements) throws IllegalArgumentException  {
		this.settlements = settlements;
	}
	/**
	 * sets all the cities on the map
	 * @param cities
	 */
	public void setCities(MirrorVertexObject[] cities) throws IllegalArgumentException  {
		this.cities = cities;
	}
	/**
	 * sets the radius of the map
	 * @param radius
	 */
	public void setRadius(int radius) throws IllegalArgumentException  {
		this.radius = radius;
	}
	/**
	 * sets the location of the robber on the map
	 * @param robber
	 */
	public void setRobber(HexLocation robber) throws IllegalArgumentException  {
		this.robber = robber;
	}
	
	
}
