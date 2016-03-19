package shared.definitions;

import shared.locations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import client.data.RobPlayerInfo;

/**
 * Class to represent the entire map
 * Contains Hexes, ports, roads, settlemetns, cities, a radius, and a robber
 * @author Brennen
 *
 */
public class GameMap {
	
	/**
	 * Array of all the hexes on the map
	 */
	protected Hex[] hexes;
	/**
	 * Array of all the ports on the map
	 */
	protected Port[] ports;
	/**
	 * Array of all the roads on the map
	 */
	protected EdgeValue[] roads;
	/**
	 * Array of all the settlements on the map
	 */
	protected VertexObject[] settlements;
	/**
	 * Array of all the cities on the map
	 */
	protected VertexObject[] cities;
	/**
	 * Radius of the map
	 */
	protected int radius;
	/**
	 * Location of the robber on the map
	 */
	protected HexLocation robber;
	protected List<EdgeValue>allRoads = new ArrayList<EdgeValue>();
	protected List<VertexObject>allSettlements = new ArrayList<VertexObject>();
	protected List<VertexObject>secondRoundSettlements = new ArrayList<VertexObject>();
	protected List<VertexObject>allCities = new ArrayList<VertexObject>();
	private List<Port>allPorts = new ArrayList<Port>();
	
	
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
	public GameMap(Hex[] hexes, Port[] ports, EdgeValue[] roads,
			VertexObject[] settlements, VertexObject[] cities, int radius,
			HexLocation robber) throws IllegalArgumentException  {
	
		this.hexes = hexes;
		this.ports = ports;
		this.roads = roads;
		this.settlements = settlements;
		this.cities = cities;
		this.radius = radius;
		this.robber = robber;
		this.allRoads.addAll(Arrays.asList(roads));
		this.allSettlements.addAll(Arrays.asList(settlements));
		this.allCities.addAll(Arrays.asList(cities));
		this.allPorts.addAll(Arrays.asList(ports));
	}
	public boolean isEqual(GameMap other) throws IllegalArgumentException{
		
		return true;
	}
	public GameMap(){
		
	}

	/**
	 * gets an array of all the Hexes
	 * @return hexes
	 */
	public Hex[] getHexes() throws IllegalArgumentException{
		return hexes;
	}

	/**
	 * gets an array of all the ports on the map
	 * @return ports
	 */
	public Port[] getPorts() throws IllegalArgumentException{
		return ports;
	}
	/**
	 * gets all the personal ports of an owner
	 * @param owner
	 * @return
	 */
	public List<Port> getPersonalPorts(int owner){
		List<Port>myPorts = new ArrayList();
		if(allSettlements.size() >0){
			for (int i=0; i<settlements.length; i++){
				if(settlements[i].getOwner() == owner){
					VertexObject settlement = settlements[i];
					VertexLocation location = settlement.getLocation();
					HexLocation hexLoc = location.getHexLoc();
					if((hexLoc.getX()==1 && hexLoc.getY()==-3)&&(location.getDir()==VertexDirection.SouthEast || location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==1 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==1 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.NorthEast || location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==1 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==-3)&&(location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==1 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==0 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==1 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==3 && hexLoc.getY()==-3)&&(location.getDir()==VertexDirection.West || location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.East || location.getDir()==VertexDirection.NorthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==-3)&&(location.getDir()==VertexDirection.SouthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==3 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==3 && hexLoc.getY()==-1)&&(location.getDir()==VertexDirection.West || location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==-1)&&(location.getDir()==VertexDirection.East || location.getDir()==VertexDirection.SouthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==3 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==0)&&(location.getDir()==VertexDirection.NorthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-1){
								myPorts.add(ports[j]);
							}
						}
					}
					
					if((hexLoc.getX()==2 && hexLoc.getY()==1)&&(location.getDir()==VertexDirection.NorthWest || location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==2 &&ports[j].getLocation().getY()==1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==1 && hexLoc.getY()==1)&&(location.getDir()==VertexDirection.SouthEast || location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==2 &&ports[j].getLocation().getY()==1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==1 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.NorthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==2 &&ports[j].getLocation().getY()==1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==0)&&(location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==2 &&ports[j].getLocation().getY()==1){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==0 && hexLoc.getY()==3)&&(location.getDir()==VertexDirection.NorthWest || location.getDir()==VertexDirection.NorthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==0 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==0 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.SouthWest || location.getDir()==VertexDirection.SouthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==0 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==1 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==0 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-1 && hexLoc.getY()==3)&&(location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==0 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==-2 && hexLoc.getY()==3)&&(location.getDir()==VertexDirection.NorthEast || location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-2 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-1 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.SouthWest || location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-2 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-1 && hexLoc.getY()==3)&&(location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-2 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.SouthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-2 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==-3 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.NorthEast || location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==1)&&(location.getDir()==VertexDirection.SouthWest || location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-3 && hexLoc.getY()==1)&&(location.getDir()==VertexDirection.SouthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==2){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==-3 && hexLoc.getY()==0)&&(location.getDir()==VertexDirection.SouthEast || location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==0){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==0)&&(location.getDir()==VertexDirection.NorthWest || location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==0){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==-1)&&(location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==0){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-3 && hexLoc.getY()==1)&&(location.getDir()==VertexDirection.NorthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==0){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==-1 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.SouthEast || location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-1 &&ports[j].getLocation().getY()==-2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-1 && hexLoc.getY()==-1)&&(location.getDir()==VertexDirection.NorthEast || location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-1 &&ports[j].getLocation().getY()==-2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==0 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-1 &&ports[j].getLocation().getY()==-2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==-1)&&(location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-1 &&ports[j].getLocation().getY()==-2){
								myPorts.add(ports[j]);
							}
						}
					}
				}
				
			}
		}
		if(allCities.size() >0){
			for (int i=0; i<cities.length; i++){
				if(cities[i].getOwner() == owner){
					VertexObject settlement = cities[i];
					VertexLocation location = settlement.getLocation();
					HexLocation hexLoc = location.getHexLoc();
					if((hexLoc.getX()==1 && hexLoc.getY()==-3)&&(location.getDir()==VertexDirection.SouthEast || location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==1 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==1 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.NorthEast || location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==1 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==-3)&&(location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==1 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==0 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==1 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==3 && hexLoc.getY()==-3)&&(location.getDir()==VertexDirection.West || location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.East || location.getDir()==VertexDirection.NorthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==-3)&&(location.getDir()==VertexDirection.SouthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==3 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-3){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==3 && hexLoc.getY()==-1)&&(location.getDir()==VertexDirection.West || location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==-1)&&(location.getDir()==VertexDirection.East || location.getDir()==VertexDirection.SouthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==3 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==0)&&(location.getDir()==VertexDirection.NorthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==3 &&ports[j].getLocation().getY()==-1){
								myPorts.add(ports[j]);
							}
						}
					}
					
					if((hexLoc.getX()==2 && hexLoc.getY()==1)&&(location.getDir()==VertexDirection.NorthWest || location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==2 &&ports[j].getLocation().getY()==1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==1 && hexLoc.getY()==1)&&(location.getDir()==VertexDirection.SouthEast || location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==2 &&ports[j].getLocation().getY()==1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==1 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.NorthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==2 &&ports[j].getLocation().getY()==1){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==2 && hexLoc.getY()==0)&&(location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==2 &&ports[j].getLocation().getY()==1){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==0 && hexLoc.getY()==3)&&(location.getDir()==VertexDirection.NorthWest || location.getDir()==VertexDirection.NorthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==0 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==0 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.SouthWest || location.getDir()==VertexDirection.SouthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==0 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==1 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==0 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-1 && hexLoc.getY()==3)&&(location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==0 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==-2 && hexLoc.getY()==3)&&(location.getDir()==VertexDirection.NorthEast || location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-2 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-1 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.SouthWest || location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-2 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-1 && hexLoc.getY()==3)&&(location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-2 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.SouthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-2 &&ports[j].getLocation().getY()==3){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==-3 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.NorthEast || location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==1)&&(location.getDir()==VertexDirection.SouthWest || location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-3 && hexLoc.getY()==1)&&(location.getDir()==VertexDirection.SouthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==2)&&(location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==2){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==-3 && hexLoc.getY()==0)&&(location.getDir()==VertexDirection.SouthEast || location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==0){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==0)&&(location.getDir()==VertexDirection.NorthWest || location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==0){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==-1)&&(location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==0){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-3 && hexLoc.getY()==1)&&(location.getDir()==VertexDirection.NorthEast)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-3 &&ports[j].getLocation().getY()==0){
								myPorts.add(ports[j]);
							}
						}
					}
					if((hexLoc.getX()==-1 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.SouthEast || location.getDir()==VertexDirection.SouthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-1 &&ports[j].getLocation().getY()==-2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-1 && hexLoc.getY()==-1)&&(location.getDir()==VertexDirection.NorthEast || location.getDir()==VertexDirection.NorthWest)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-1 &&ports[j].getLocation().getY()==-2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==0 && hexLoc.getY()==-2)&&(location.getDir()==VertexDirection.West)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-1 &&ports[j].getLocation().getY()==-2){
								myPorts.add(ports[j]);
							}
						}
					}
					else if((hexLoc.getX()==-2 && hexLoc.getY()==-1)&&(location.getDir()==VertexDirection.East)){          
						for(int j=0; j<ports.length; j++){
							if(ports[j].getLocation().getX() ==-1 &&ports[j].getLocation().getY()==-2){
								myPorts.add(ports[j]);
							}
						}
					}
				}
				
			}
		}
		return myPorts;
	}
	
	

	/**
	 * gets an array of all the roads on the map
	 * @return roads
	 */
	public EdgeValue[] getRoads() throws IllegalArgumentException{
		return roads;
	}

	/**
	 * gets an array of all the settlements on the map
	 * @return settlements 
	 */
	public VertexObject[] getSettlements() throws IllegalArgumentException{
		return settlements;
	}

	/**
	 * gets an array of all the cities on the map
	 * @return cities
	 */
	public VertexObject[] getCities() throws IllegalArgumentException{
		return cities;
	}

	/**
	 * gets the radius of the map
	 * @return radius
	 */
	public int getRadius() throws IllegalArgumentException{
		return radius;
	}

	/**
	 * gets the Hexlocation of the robber
	 * @return robber
	 */
	public HexLocation getRobber() throws IllegalArgumentException{
		return robber;
	}
	/**
	 * Determines if the player has a road built in a location
	 * personalRaod contains the location where the road is to be built and the player index of the player that wants to build it
	 * @param value
	 * @return true or false
	 */
	public Boolean hasRoadPersonal(EdgeValue value) throws IllegalArgumentException {
		int owner = value.getOwner();
		EdgeLocation location = value.getLocation();
		EdgeDirection direction = location.getDir();
		HexLocation hexLocation = location.getHexLoc();
		int xCoord = hexLocation.getX();
		int yCoord = hexLocation.getY();
		
		if(allRoads.size() > 0){
			for(int i=0; i<roads.length; i++){
				EdgeValue road = roads[i];
				EdgeLocation roadLocation = road.getLocation();
				int roadOwner = road.getOwner();
				HexLocation roadHexLoc = roadLocation.getHexLoc();
				int roadX = roadHexLoc.getX();
				int roadY = roadHexLoc.getY();
				EdgeDirection roadDirection = roadLocation.getDir();
				
				if(owner ==roadOwner && direction == roadDirection && roadY == yCoord && xCoord == roadX){
					return true;
				}
				
			}
		}
		return false;
	}
	
	public HexLocation getOppositeHex(HexLocation hex, EdgeDirection direction) throws IllegalArgumentException{
		int xCoord = hex.getX();
		int yCoord = hex.getY();
		
		if(direction == EdgeDirection.North){
			yCoord = yCoord -1;
		}
		else if(direction == EdgeDirection.SouthWest){
			xCoord = xCoord -1;
			yCoord = yCoord +1;
		}
		else if(direction == EdgeDirection.NorthWest){
			xCoord = xCoord -1;
		}
		else if(direction == EdgeDirection.NorthEast){
			xCoord = xCoord +1;
			yCoord = yCoord -1;
		}
		else if(direction == EdgeDirection.SouthEast){
			xCoord = xCoord +1;
		}
		else if(direction == EdgeDirection.South){
			yCoord = yCoord +1;
		}
		
		HexLocation oppositeHex = new HexLocation(xCoord, yCoord);
		return oppositeHex;
	}
	/**
	 * determines if a player can lay a road at a certain location
	 * @param value
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Boolean canLayRoad(EdgeValue value) throws IllegalArgumentException{
		int owner = value.getOwner();
		EdgeLocation location = value.getLocation();
		HexLocation hex = location.getHexLoc();
		Boolean isLand = this.isLand(hex);
		EdgeDirection direction = location.getDir();
		
		Boolean hasRoad = this.hasRoadAllPlayers(location);
		if(hasRoad == true){
			return false;
		}
		
		if(direction == EdgeDirection.SouthWest){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.NorthWest);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.West);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
				
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.South);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.SouthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			HexLocation opp = getOppositeHex(hex, EdgeDirection.NorthWest);
			EdgeDirection oppDir = EdgeDirection.NorthWest.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			oppDir = EdgeDirection.South;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, EdgeDirection.South);
			oppDir = EdgeDirection.South.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			oppDir = EdgeDirection.NorthWest;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, EdgeDirection.SouthWest);
			oppDir = EdgeDirection.SouthEast;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.East);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			oppDir = EdgeDirection.North;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			
		}
		else if(direction == EdgeDirection.NorthWest){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.SouthWest);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.West);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
				
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.North);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.NorthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			HexLocation opp = getOppositeHex(hex, EdgeDirection.SouthWest);
			EdgeDirection oppDir = EdgeDirection.SouthWest.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			oppDir = EdgeDirection.North;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, EdgeDirection.North);
			oppDir = EdgeDirection.North.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			oppDir = EdgeDirection.SouthWest;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, EdgeDirection.NorthWest);
			oppDir = EdgeDirection.South;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			oppDir = EdgeDirection.NorthEast;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.East);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
		}
		else if(direction == EdgeDirection.North){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.NorthWest);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.NorthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.NorthEast);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.NorthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			HexLocation opp = getOppositeHex(hex, EdgeDirection.NorthWest);
			EdgeDirection oppDir = EdgeDirection.NorthWest.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.East);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			oppDir = EdgeDirection.NorthEast;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.East);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			
			opp = getOppositeHex(hex, EdgeDirection.NorthEast);
			oppDir = EdgeDirection.NorthEast.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.West);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, EdgeDirection.NorthEast);
			oppDir = EdgeDirection.NorthWest;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.West);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, EdgeDirection.North);
			oppDir = EdgeDirection.SouthWest;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, EdgeDirection.North);
			oppDir = EdgeDirection.SouthEast;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
		}
		else if(direction == EdgeDirection.NorthEast){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.North);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.NorthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.SouthEast);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.East);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			HexLocation opp = getOppositeHex(hex, EdgeDirection.North);
			EdgeDirection oppDir = EdgeDirection.North.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			oppDir = EdgeDirection.SouthEast;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, EdgeDirection.SouthEast);
			oppDir = EdgeDirection.SouthEast.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, EdgeDirection.SouthEast);
			oppDir = EdgeDirection.North;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, direction);
			oppDir = EdgeDirection.South;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, direction);
			oppDir = EdgeDirection.NorthWest;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.West);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
		}
		else if(direction == EdgeDirection.SouthEast){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.NorthEast);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.East);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.South);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.SouthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			HexLocation opp = getOppositeHex(hex, EdgeDirection.NorthEast);
			EdgeDirection oppDir = EdgeDirection.NorthEast.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			oppDir = EdgeDirection.South;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.SouthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, EdgeDirection.South);
			oppDir = EdgeDirection.South.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			oppDir = EdgeDirection.NorthEast;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, direction);
			oppDir = EdgeDirection.North;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			opp = getOppositeHex(hex, direction);
			oppDir = EdgeDirection.SouthWest;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.West);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}

		}
		else if(direction == EdgeDirection.South){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.SouthWest);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.SouthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.SouthEast);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(hex,VertexDirection.SouthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			HexLocation opp = getOppositeHex(hex, EdgeDirection.SouthWest);
			EdgeDirection oppDir = EdgeDirection.SouthWest.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.East);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			oppDir = EdgeDirection.SouthEast;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.East);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			
			opp = getOppositeHex(hex, EdgeDirection.SouthEast);
			oppDir = EdgeDirection.SouthEast.getOppositeDirection();
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.West);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			oppDir = EdgeDirection.SouthWest;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.West);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
		
			opp = getOppositeHex(hex, direction);
			oppDir = EdgeDirection.NorthWest;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthWest);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
			oppDir = EdgeDirection.NorthEast;
			newLocation = new EdgeLocation(opp,oppDir);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				VertexLocation loc = new VertexLocation(opp,VertexDirection.NorthEast);
				VertexObject settle = new VertexObject(owner,loc);
				boolean check = hasMunicipality(loc);
				if(check == false){
					return true;
				}
				else{
					check = hasSettlement(settle);
				}
				if(check==true){
					return true;
				}
				else{
					check = hasCity(settle);
				}
				if(check==true){
					return true;
				}
			}
		}
		
		
		return false;
	}
	
	public Boolean canLayRoadFirstRounds(EdgeValue value) throws IllegalArgumentException{
		int owner = value.getOwner();
		EdgeLocation location = value.getLocation();
		HexLocation hex = location.getHexLoc();
		Boolean hasProperty = false;
		EdgeDirection direction = location.getDir();
		
		if(direction == EdgeDirection.SouthWest){
			boolean bottomCheck = false;
			boolean topCheck = false;
			//topCheck
			VertexLocation property = new VertexLocation(hex, VertexDirection.West);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hex, VertexDirection.NorthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				topCheck = true;
			}
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.NorthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				topCheck = true;
			}
			
			
			//bottomCheck
			property = new VertexLocation(hex, VertexDirection.SouthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hex, VertexDirection.SouthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				bottomCheck = true;
			}
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.SouthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				bottomCheck = true;
			}
			
			if(topCheck == true && bottomCheck ==true){
				return false;
			}
			
			return true;
			
		}
		else if(direction == EdgeDirection.NorthWest){
			boolean topCheck = false;
			boolean bottomCheck = false;
			
			//bottomCheck
			VertexLocation property = new VertexLocation(hex, VertexDirection.West);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hex, VertexDirection.SouthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				bottomCheck=true;
			}
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.SouthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				bottomCheck=true;
			}

			
			//topCheck
			property = new VertexLocation(hex, VertexDirection.NorthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hex, VertexDirection.NorthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				topCheck = true;
			}
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.NorthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				topCheck = true;
			}
			
			if(bottomCheck ==true && topCheck ==true){
				return false;
			}
			
			return true;
		}
		else if(direction == EdgeDirection.North){
			boolean rightCheck = false;
			boolean leftCheck = false;
			//leftCheck
			VertexLocation property = new VertexLocation(hex, VertexDirection.NorthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			
			property = new VertexLocation(hex, VertexDirection.West);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				leftCheck = true;
			}
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.West);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				leftCheck = true;
			}
			
			
			//Right check
			property = new VertexLocation(hex, VertexDirection.NorthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hex, VertexDirection.East);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				rightCheck = true;
			}
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.East);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				rightCheck = true;
			}
			
			if(rightCheck ==true && leftCheck ==true){
				return false;
			}

			
			return true;
		}
		else if(direction == EdgeDirection.NorthEast){
			boolean bottomCheck = false;
			boolean topCheck = false;
			
			//topCheck
			VertexLocation property = new VertexLocation(hex, VertexDirection.NorthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			
			property = new VertexLocation(hex, VertexDirection.NorthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				topCheck = true;
			}
			
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.NorthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				topCheck = true;
			}
			
			//bottomCheck
			property = new VertexLocation(hex, VertexDirection.East);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			
			property = new VertexLocation(hex, VertexDirection.SouthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				bottomCheck = true;
			}
			
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.SouthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				bottomCheck = true;
			}
			
			if(bottomCheck == true && topCheck == true){
				return false;
			}

			
			return true;
		}
		else if(direction == EdgeDirection.SouthEast){
			boolean topCheck = false;
			boolean bottomCheck = true;
			
			//topCheck
			VertexLocation property = new VertexLocation(hex, VertexDirection.East);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hex, VertexDirection.NorthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				topCheck = true;
			}
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.NorthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				topCheck = true; 
			}
			
			
			//bottomCheck
			property = new VertexLocation(hex, VertexDirection.SouthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hex, VertexDirection.SouthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				bottomCheck = true;
			}
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.SouthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				bottomCheck = true;
			}
			
			if(bottomCheck == true && topCheck == true){
				return false;
			}
			
			return true;
		}
		else if(direction == EdgeDirection.South){
			boolean rightCheck = false;
			boolean leftCheck = false;
			
			//leftCheck
			VertexLocation property = new VertexLocation(hex, VertexDirection.SouthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			
			property = new VertexLocation(hex, VertexDirection.West);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				leftCheck = true;
			}
			
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.West);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				leftCheck = true;
			}
			
			
			//rightCheck
			property = new VertexLocation(hex, VertexDirection.SouthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hex, VertexDirection.East);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				rightCheck = true;
			}
			property = new VertexLocation(getOppositeHex(hex,direction), VertexDirection.East);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				rightCheck = true;
			}
			
			if(rightCheck ==true && leftCheck ==true){
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Determines if any player has a road built in a location
	 * Returns true if there is already a road there
	 * @param location
	 * @return true or false
	 */
	public Boolean hasRoadAllPlayers(EdgeLocation location) throws IllegalArgumentException {
		
		//Extracting info from location that is passed in
		HexLocation hexLocation = location.getHexLoc();
		int xCoord = hexLocation.getX();
		int yCoord = hexLocation.getY();
		EdgeDirection direction = location.getDir();
	
		//Loop through the array of allRoads to see if there is already a road in the location
		HexLocation oppositeHex = getOppositeHex(hexLocation, direction);
		int xOpp = oppositeHex.getX();
		int yOpp = oppositeHex.getY();
		EdgeDirection directionOpp = direction.getOppositeDirection();
		if(allRoads.size() > 0){
			for(int i=0; i<roads.length; i++){
				//Extracting info from the roads in the roads array
				EdgeLocation roadLocation = roads[i].getLocation();
				HexLocation roadHex = roadLocation.getHexLoc();
				
				int xRoadCoord = roadHex.getX();
				int yRoadCoord = roadHex.getY();
				EdgeDirection roadDirection = roadLocation.getDir();
				
				//Checking to see if there is already a road in that location
				if(xRoadCoord == xCoord && yCoord == yRoadCoord && direction == roadDirection){
					return true;
				}
				if(xRoadCoord == xOpp && yOpp == yRoadCoord && directionOpp == roadDirection){
					return true;
				}
		
			}
		}

		return false; //No road found - location is available 
	}
	
	/**
	 * Determines if the location passed in is on land or at sea
	 * Returns true if its on land
	 * Returns false if at sea
	 * @param hexLocation
	 * @return
	 */
	public Boolean isLand(HexLocation hexLocation) throws IllegalArgumentException{
		int xCoord = hexLocation.getX();
		int yCoord = hexLocation.getY();
		
		if(xCoord > 2){
			return false;
		}
		if(xCoord <-2){
			return false;
		}
		if(yCoord > 2){
			return false;
		}
		if(yCoord <-2){
			return false;
		}
		if(xCoord >=1 && yCoord >=2){
			return false;
		}
		if(xCoord >=2 && yCoord>=1){
			return false;
		}
		if(xCoord <= -2 && yCoord <= -1){
			return false;
		}
		if(xCoord <= -1 && yCoord <= -2){
			return false;
		}
		return true;
	}
	
	/**
	 * Determines if any player has a city or settlement built in a location
	 * @param municipalityLocation
	 * @return true or false
	 */
	public boolean hasMunicipality(VertexLocation municipalityLocation) throws IllegalArgumentException {
		int x = municipalityLocation.getHexLoc().getX();
		int y = municipalityLocation.getHexLoc().getY();
		VertexDirection direction = municipalityLocation.getDir();
		HexLocation location = municipalityLocation.getHexLoc();

		//Check Settlements array
		if(allSettlements.size()>0){
			for(VertexObject temp : settlements){
				int xTemp = temp.getLocation().getHexLoc().getX();
				int yTemp = temp.getLocation().getHexLoc().getY();
				VertexDirection directionTemp = temp.getLocation().getDir();
	
				if((x == xTemp) && (y == yTemp)){
					if(direction == directionTemp){
						return true;
					}
				}
			}
		}

		//Check Cities array
		if(allCities.size() >0){
			for(VertexObject temp : cities){
				int xTemp = temp.getLocation().getHexLoc().getX();
				int yTemp = temp.getLocation().getHexLoc().getY();
				VertexDirection directionTemp = temp.getLocation().getDir();
	
				if((x == xTemp) && (y == yTemp)){
					if(direction == directionTemp){
						return true;
					}
				}
			}
		}
		
		if(direction == VertexDirection.West){
			HexLocation hex = getOppositeHex(location,EdgeDirection.NorthWest);
			int x1 = hex.getX();
			int y1 = hex.getY();
			HexLocation temp2 = getOppositeHex(location,EdgeDirection.SouthWest);
			int x2 = temp2.getX();
			int y2 = temp2.getY();
			if(allCities.size() >0){
				for(VertexObject temp : cities){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.SouthEast == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.NorthEast == directionTemp){
							return true;
						}
					}
				}
			}
			if(allSettlements.size()>0){
				for(VertexObject temp : settlements){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.SouthEast == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.NorthEast == directionTemp){
							return true;
						}
					}
				}
			}
		}
		
		if(direction == VertexDirection.NorthWest){
			HexLocation hex = getOppositeHex(location,EdgeDirection.North);
			int x1 = hex.getX();
			int y1 = hex.getY();
			HexLocation temp2 = getOppositeHex(location,EdgeDirection.NorthWest);
			int x2 = temp2.getX();
			int y2 = temp2.getY();
			if(allCities.size() >0){
				for(VertexObject temp : cities){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.SouthWest == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.East == directionTemp){
							return true;
						}
					}
				}
			}
			if(allSettlements.size()>0){
				for(VertexObject temp : settlements){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.SouthWest == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.East == directionTemp){
							return true;
						}
					}
				}
			}
			
		}
		
		if(direction == VertexDirection.SouthWest){
			HexLocation hex = getOppositeHex(location,EdgeDirection.South);
			int x1 = hex.getX();
			int y1 = hex.getY();
			HexLocation temp2 = getOppositeHex(location,EdgeDirection.SouthWest);
			int x2 = temp2.getX();
			int y2 = temp2.getY();
			if(allCities.size() >0){
				for(VertexObject temp : cities){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.NorthWest == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.East == directionTemp){
							return true;
						}
					}
				}
			}
			if(allSettlements.size()>0){
				for(VertexObject temp : settlements){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.NorthWest == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.East == directionTemp){
							return true;
						}
					}
				}
			}
		}
		if(direction == VertexDirection.SouthEast){
			HexLocation hex = getOppositeHex(location,EdgeDirection.South);
			int x1 = hex.getX();
			int y1 = hex.getY();
			HexLocation temp2 = getOppositeHex(location,EdgeDirection.SouthEast);
			int x2 = temp2.getX();
			int y2 = temp2.getY();
			if(allCities.size() >0){
				for(VertexObject temp : cities){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.NorthEast == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.West == directionTemp){
							return true;
						}
					}
				}
			}
			if(allSettlements.size()>0){
				for(VertexObject temp : settlements){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.NorthEast == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.West == directionTemp){
							return true;
						}
					}
				}
			}
			
		}
		
		if(direction == VertexDirection.East){
			HexLocation hex = getOppositeHex(location,EdgeDirection.NorthEast);
			int x1 = hex.getX();
			int y1 = hex.getY();
			HexLocation temp2 = getOppositeHex(location,EdgeDirection.SouthEast);
			int x2 = temp2.getX();
			int y2 = temp2.getY();
			if(allCities.size() >0){
				for(VertexObject temp : cities){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.SouthWest == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.NorthWest == directionTemp){
							return true;
						}
					}
				}
			}
			if(allSettlements.size()>0){
				for(VertexObject temp : settlements){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.SouthWest == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.NorthWest == directionTemp){
							return true;
						}
					}
				}
			}
			
		}
		if(direction == VertexDirection.NorthEast){
			HexLocation hex = getOppositeHex(location,EdgeDirection.NorthEast);
			int x1 = hex.getX();
			int y1 = hex.getY();
			HexLocation temp2 = getOppositeHex(location,EdgeDirection.North);
			int x2 = temp2.getX();
			int y2 = temp2.getY();
			if(allCities.size() >0){
				for(VertexObject temp : cities){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.West == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.SouthEast == directionTemp){
							return true;
						}
					}
				}
			}
			if(allSettlements.size()>0){
				for(VertexObject temp : settlements){
					int xTemp = temp.getLocation().getHexLoc().getX();
					int yTemp = temp.getLocation().getHexLoc().getY();
					VertexDirection directionTemp = temp.getLocation().getDir();
		
					if((x1 == xTemp) && (y1 == yTemp)){
						if(VertexDirection.West == directionTemp){
							return true;
						}
					}
					if((x2 == xTemp) && (y2 == yTemp)){
						if(VertexDirection.SouthEast == directionTemp){
							return true;
						}
					}
				}
			}
			
		}

		//If there's no municipality at that vertex
		return false;
	}
	
	
	public Boolean hasMunicipalityPort(int owner, VertexObject municipality){
		int ownerTemp = municipality.getOwner();
		VertexLocation locationTemp = municipality.getLocation();
		VertexDirection directionTemp = locationTemp.getDir();
		int xCoord = locationTemp.getHexLoc().getX();
		int yCoord = locationTemp.getHexLoc().getY();
		if(xCoord == 0 && yCoord == 2 && ownerTemp == owner){
			if(directionTemp == VertexDirection.SouthWest || directionTemp == VertexDirection.SouthEast){
				return true;
			}
		}
		if(xCoord == -1 && yCoord == 2 && ownerTemp == owner){
			if(directionTemp == VertexDirection.SouthWest || directionTemp == VertexDirection.West){
				return true;
			}
		}
		if(xCoord == -2 && yCoord == 2 && ownerTemp == owner){
			if(directionTemp == VertexDirection.SouthWest || directionTemp == VertexDirection.SouthEast){
				return true;
			}
		}
		if(xCoord == -2 && yCoord == 2 && ownerTemp == owner){
			if(directionTemp == VertexDirection.West || directionTemp == VertexDirection.NorthWest){
				return true;
			}
		}
		if(xCoord == -2 && yCoord == 1 && ownerTemp == owner){
			if(directionTemp == VertexDirection.SouthWest || directionTemp == VertexDirection.West){
				return true;
			}
		}
		if(xCoord == -2 && yCoord == 0 && ownerTemp == owner){
			if(directionTemp == VertexDirection.West || directionTemp == VertexDirection.NorthWest){
				return true;
			}
		}
		if(xCoord == -1 && yCoord == -1 && ownerTemp == owner){
			if(directionTemp == VertexDirection.NorthWest || directionTemp == VertexDirection.NorthEast){
				return true;
			}
		}
		if(xCoord == 0 && yCoord == -2 && ownerTemp == owner){
			if(directionTemp == VertexDirection.NorthWest || directionTemp == VertexDirection.West){
				return true;
			}
			if(directionTemp == VertexDirection.NorthEast || directionTemp == VertexDirection.East){
				return true;
			}
			
		}
		if(xCoord == 1 && yCoord == -2 && ownerTemp == owner){
			if(directionTemp == VertexDirection.NorthWest || directionTemp == VertexDirection.NorthEast){
				return true;
			}
		}
		if(xCoord == 2 && yCoord == -2 && ownerTemp == owner){
			if(directionTemp == VertexDirection.NorthEast || directionTemp == VertexDirection.East){
				return true;
			}
		}
		if(xCoord == 2 && yCoord == -1 && ownerTemp == owner){
			if(directionTemp == VertexDirection.East || directionTemp == VertexDirection.SouthEast){
				return true;
			}
		}
		if(xCoord == 2 && yCoord == 0 && ownerTemp == owner){
			if(directionTemp == VertexDirection.East || directionTemp == VertexDirection.NorthEast){
				return true;
			}
			if(directionTemp == VertexDirection.SouthEast || directionTemp == VertexDirection.SouthWest){
				return true;
			}
		}
		if(xCoord == 1 && yCoord == 1 && ownerTemp == owner){
			if(directionTemp == VertexDirection.East || directionTemp == VertexDirection.SouthEast){
				return true;
			}
		}
		
		return false;
	}
	
	public Boolean hasPort(int owner) throws IllegalArgumentException{
		
		if(allSettlements.size() > 0){
			if(allPorts.size() > 0){
				for(int i=0; i<allSettlements.size(); i++){
					VertexObject settlement = allSettlements.get(i);
					if(this.hasMunicipalityPort(owner,settlement) == true){
						return true;
					}
				}
				
			}
		}
		if(allCities.size() >0){
			if(allPorts.size() > 0){
				for(int j=0; j<allCities.size(); j++){
					VertexObject city = allCities.get(j);
					if(this.hasMunicipalityPort(owner,city) == true){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean hasCity(VertexObject settlement) throws IllegalArgumentException {
		int owner = settlement.getOwner();
		HexLocation coordinates = settlement.getLocation().getHexLoc();
		VertexDirection direction = settlement.getLocation().getDir();
		if(direction == VertexDirection.West){
			VertexDirection direction2 = VertexDirection.SouthEast;
			VertexDirection direction3 = VertexDirection.NorthEast;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.NorthWest);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.SouthWest);
			if(allCities.size() > 0){
				for(int i =0; i<allCities.size(); i++){
					int xTemp = allCities.get(i).getLocation().getHexLoc().getX();
					int yTemp = allCities.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allCities.get(i).getLocation().getDir();
					int ownerTemp = allCities.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}
		
		if(direction == VertexDirection.NorthWest){
			VertexDirection direction2 = VertexDirection.East;
			VertexDirection direction3 = VertexDirection.SouthWest;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.NorthWest);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.North);
			if(allCities.size() > 0){
				for(int i =0; i<allCities.size(); i++){
					int xTemp = allCities.get(i).getLocation().getHexLoc().getX();
					int yTemp = allCities.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allCities.get(i).getLocation().getDir();
					int ownerTemp = allCities.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}
		
		if(direction == VertexDirection.NorthEast){
			VertexDirection direction2 = VertexDirection.West;
			VertexDirection direction3 = VertexDirection.SouthEast;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.NorthEast);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.North);
			if(allCities.size() > 0){
				for(int i =0; i<allCities.size(); i++){
					int xTemp = allCities.get(i).getLocation().getHexLoc().getX();
					int yTemp = allCities.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allCities.get(i).getLocation().getDir();
					int ownerTemp = allCities.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}
		
		if(direction == VertexDirection.East){
			VertexDirection direction2 = VertexDirection.SouthWest;
			VertexDirection direction3 = VertexDirection.NorthWest;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.NorthEast);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.SouthEast);
			if(allCities.size() > 0){
				for(int i =0; i<allCities.size(); i++){
					int xTemp = allCities.get(i).getLocation().getHexLoc().getX();
					int yTemp = allCities.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allCities.get(i).getLocation().getDir();
					int ownerTemp = allCities.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}
		if(direction == VertexDirection.SouthEast){
			VertexDirection direction2 = VertexDirection.West;
			VertexDirection direction3 = VertexDirection.NorthEast;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.SouthEast);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.South);
			if(allCities.size() > 0){
				for(int i =0; i<allCities.size(); i++){
					int xTemp = allCities.get(i).getLocation().getHexLoc().getX();
					int yTemp = allCities.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allCities.get(i).getLocation().getDir();
					int ownerTemp = allCities.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}
		if(direction == VertexDirection.SouthWest){
			VertexDirection direction2 = VertexDirection.NorthWest;
			VertexDirection direction3 = VertexDirection.East;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.South);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.SouthWest);
			if(allCities.size() > 0){
				for(int i =0; i<allCities.size(); i++){
					int xTemp = allCities.get(i).getLocation().getHexLoc().getX();
					int yTemp = allCities.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allCities.get(i).getLocation().getDir();
					int ownerTemp = allCities.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}

		return false;
	}
	
/**
	 * Determines if the player has a settlement built in a location
	 * settlement contains the location at which the city is to be built and the player index of the player that wants to build it
	 * @param settlement
	 * @return true or false
	 */
	public boolean hasSettlement(VertexObject settlement) throws IllegalArgumentException {
		int owner = settlement.getOwner();
		HexLocation coordinates = settlement.getLocation().getHexLoc();
		VertexDirection direction = settlement.getLocation().getDir();
		if(direction == VertexDirection.West){
			VertexDirection direction2 = VertexDirection.SouthEast;
			VertexDirection direction3 = VertexDirection.NorthEast;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.NorthWest);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.SouthWest);
			if(allSettlements.size() > 0){
				for(int i =0; i<allSettlements.size(); i++){
					int xTemp = allSettlements.get(i).getLocation().getHexLoc().getX();
					int yTemp = allSettlements.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allSettlements.get(i).getLocation().getDir();
					int ownerTemp = allSettlements.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}
		
		if(direction == VertexDirection.NorthWest){
			VertexDirection direction2 = VertexDirection.East;
			VertexDirection direction3 = VertexDirection.SouthWest;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.NorthWest);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.North);
			if(allSettlements.size() > 0){
				for(int i =0; i<allSettlements.size(); i++){
					int xTemp = allSettlements.get(i).getLocation().getHexLoc().getX();
					int yTemp = allSettlements.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allSettlements.get(i).getLocation().getDir();
					int ownerTemp = allSettlements.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}
		
		if(direction == VertexDirection.NorthEast){
			VertexDirection direction2 = VertexDirection.West;
			VertexDirection direction3 = VertexDirection.SouthEast;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.NorthEast);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.North);
			if(allSettlements.size() > 0){
				for(int i =0; i<allSettlements.size(); i++){
					int xTemp = allSettlements.get(i).getLocation().getHexLoc().getX();
					int yTemp = allSettlements.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allSettlements.get(i).getLocation().getDir();
					int ownerTemp = allSettlements.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}
		
		if(direction == VertexDirection.East){
			VertexDirection direction2 = VertexDirection.SouthWest;
			VertexDirection direction3 = VertexDirection.NorthWest;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.NorthEast);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.SouthEast);
			if(allSettlements.size() > 0){
				for(int i =0; i<allSettlements.size(); i++){
					int xTemp = allSettlements.get(i).getLocation().getHexLoc().getX();
					int yTemp = allSettlements.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allSettlements.get(i).getLocation().getDir();
					int ownerTemp = allSettlements.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}
		if(direction == VertexDirection.SouthEast){
			VertexDirection direction2 = VertexDirection.West;
			VertexDirection direction3 = VertexDirection.NorthEast;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.SouthEast);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.South);
			if(allSettlements.size() > 0){
				for(int i =0; i<allSettlements.size(); i++){
					int xTemp = allSettlements.get(i).getLocation().getHexLoc().getX();
					int yTemp = allSettlements.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allSettlements.get(i).getLocation().getDir();
					int ownerTemp = allSettlements.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}
		if(direction == VertexDirection.SouthWest){
			VertexDirection direction2 = VertexDirection.NorthWest;
			VertexDirection direction3 = VertexDirection.East;
			HexLocation loc1 = getOppositeHex(coordinates,EdgeDirection.South);
			HexLocation loc2 = getOppositeHex(coordinates,EdgeDirection.SouthWest);
			if(allSettlements.size() > 0){
				for(int i =0; i<allSettlements.size(); i++){
					int xTemp = allSettlements.get(i).getLocation().getHexLoc().getX();
					int yTemp = allSettlements.get(i).getLocation().getHexLoc().getY();
					VertexDirection directionTemp = allSettlements.get(i).getLocation().getDir();
					int ownerTemp = allSettlements.get(i).getOwner();
		
					if((coordinates.getX()) == xTemp && (coordinates.getY() == yTemp)){
						if(direction == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc1.getX()) == xTemp && (loc1.getY() == yTemp)){
						if(direction2 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
					if((loc2.getX()) == xTemp && (loc2.getY() == yTemp)){
						if(direction3 == directionTemp){
							if(owner == ownerTemp)
							{
		
								return true;
							}
						}
					}
				}
			}
		}

		return false;
	}
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
		this.allPorts.clear();
		this.allPorts.addAll(Arrays.asList(this.ports));
	}
	/**
	 * sets all the roads on the map
	 * @param roads
	 */
	public void setRoads(EdgeValue[] roads) throws IllegalArgumentException  {
		this.roads = roads;
		this.allRoads.clear();
		this.allRoads.addAll(Arrays.asList(this.roads));
	}
	/**
	 * sets all the settlements on the map
	 * @param settlements
	 */
	public void setSettlements(VertexObject[] settlements) throws IllegalArgumentException  {
		this.settlements = settlements;
		this.allSettlements.clear();
		this.allSettlements.addAll(Arrays.asList(this.settlements));
	}
	/**
	 * sets all the cities on the map
	 * @param cities
	 */
	public void setCities(VertexObject[] cities) throws IllegalArgumentException  {
		this.cities = cities;
		this.allCities.clear();
		this.allCities.addAll(Arrays.asList(this.cities));
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
	/**
	 * adds a road to roads
	 * @param roadToBuild
	 */
	public void buildRoad(EdgeValue roadToBuild) throws IllegalArgumentException {
		allRoads.add(roadToBuild);
		
		int owner = roadToBuild.getOwner();
		EdgeLocation location = roadToBuild.getLocation();
		HexLocation hexLoc = location.getHexLoc();
		EdgeDirection direction = location.getDir();
		HexLocation oppositeHex = this.getOppositeHex(hexLoc, direction);
		Boolean isLand = this.isLand(oppositeHex);
		if(isLand == true){
			EdgeDirection oppositeDirection = direction.getOppositeDirection();
			EdgeLocation oppLocation = new EdgeLocation(oppositeHex, oppositeDirection);
			EdgeValue oppositeRoad = new EdgeValue(owner, oppLocation);
			allRoads.add(oppositeRoad);
		}
		
		roads = new EdgeValue[allRoads.size()];
		roads = allRoads.toArray(roads);
		
	}
	/**
	 * Adds a settlement to settlements 
	 * @param settlementToBuild
	 */
	public void buildSettlement(VertexObject settlementToBuild,boolean secondRound) throws IllegalArgumentException {
		allSettlements.add(settlementToBuild);
		settlements = new VertexObject[allSettlements.size()];
		settlements = allSettlements.toArray(settlements);
		if(secondRound == true){
			secondRoundSettlements.add(settlementToBuild);
		}
	}
	
	public List<VertexObject> getSecondRoundSettlements(int owner){
		List<VertexObject> settlements = new ArrayList<VertexObject>();
		for(int i=0; i<secondRoundSettlements.size(); i++){
			if(secondRoundSettlements.get(i).getOwner() == owner){
				settlements.add(secondRoundSettlements.get(i));
			}
		}
		
		return settlements;
	}

	/**
	 * adds a city to cities
	 * @param cityToBuild
	 */
	public void buildCity(VertexObject cityToBuild) throws IllegalArgumentException {
		allCities.add(cityToBuild);
		cities = new VertexObject[allCities.size()];
		cities = allCities.toArray(cities);
	}

	public VertexObject vertexObjectFactory(int owner, int x, int y, VertexDirection dir)throws IllegalArgumentException
	{
		HexLocation hexTemp = new HexLocation(x,y);
		VertexLocation locationTemp = new VertexLocation(hexTemp, dir);
		VertexObject temp = new VertexObject(owner, locationTemp);

		return temp;
	}
	/**
	 * Adds a settlement to settlements 
	 * @param location
	 */
	public boolean laySettlement(VertexObject location, boolean secondRound){
		int x = location.getLocation().getHexLoc().getX(); 
		int y = location.getLocation().getHexLoc().getY();
		VertexDirection direction = location.getLocation().getDir();
		int owner = location.getOwner();

		//Save the road based on the "home" Hex

		buildSettlement(location,secondRound);


		//Save the road based on the adjacent Hexes		
		if(direction == VertexDirection.NorthWest){
			//Hex1
			if(isLand(new HexLocation(x-1,y))){
				VertexObject temp = vertexObjectFactory(owner, x-1, y, VertexDirection.East);
				buildSettlement(temp,secondRound);
			}
			//Hex2
			if(isLand(new HexLocation(x,y-1))){
				VertexObject temp = vertexObjectFactory(owner,x,y-1,VertexDirection.SouthWest);
				buildSettlement(temp,secondRound);
			}

		}
		else if(direction == VertexDirection.NorthEast){
			//Hex1
			if(isLand(new HexLocation(x,y-1))){
				VertexObject temp = vertexObjectFactory(owner, x, y-1, VertexDirection.SouthEast);
				buildSettlement(temp,secondRound);
			}
			//Hex2
			if(isLand(new HexLocation(x+1,y-1))){
				VertexObject temp = vertexObjectFactory(owner,x+1,y-1,VertexDirection.West);
				buildSettlement(temp,secondRound);
			}

		}
		else if(direction == VertexDirection.East){
			//Hex1
			if(isLand(new HexLocation(x+1,y-1))){
				VertexObject temp = vertexObjectFactory(owner, x+1, y-1, VertexDirection.SouthWest);
				buildSettlement(temp,secondRound);
			}
			//Hex2
			if(isLand(new HexLocation(x+1,y))){
				VertexObject temp = vertexObjectFactory(owner,x+1,y,VertexDirection.NorthWest);
				buildSettlement(temp,secondRound);
			}
		}
		else if(direction == VertexDirection.SouthEast){
			//Hex1
			if(isLand(new HexLocation(x+1,y))){
				VertexObject temp = vertexObjectFactory(owner, x+1, y, VertexDirection.West);
				buildSettlement(temp,secondRound);
			}
			//Hex2
			if(isLand(new HexLocation(x,y+1))){
				VertexObject temp = vertexObjectFactory(owner,x,y+1,VertexDirection.NorthEast);
				buildSettlement(temp,secondRound);
			}
		}
		else if(direction == VertexDirection.SouthWest){
			//Hex1
			if(isLand(new HexLocation(x-1,y+1))){
				VertexObject temp = vertexObjectFactory(owner, x-1, y+1, VertexDirection.East);
				buildSettlement(temp,secondRound);
			}
			//Hex2
			if(isLand(new HexLocation(x,y+1))){
				VertexObject temp = vertexObjectFactory(owner,x,y+1,VertexDirection.NorthWest);
				buildSettlement(temp,secondRound);
			}

		}
		else if(direction == VertexDirection.West){
			//Hex1
			if(isLand(new HexLocation(x-1,y+1))){
				VertexObject temp = vertexObjectFactory(owner, x-1, y+1, VertexDirection.NorthEast);
				buildSettlement(temp,secondRound);
			}
			//Hex2
			if(isLand(new HexLocation(x-1,y))){
				VertexObject temp = vertexObjectFactory(owner,x-1,y,VertexDirection.SouthEast);
				buildSettlement(temp,secondRound);
			}
		}


		//The Settlement was laid successfully
		return true;
	}
	
	/**
	 * This method updates the Settlement array after an item has been removed
	 * from the ArrayList AllSettlements (because a city was built instead)
	 */
	public void updateSettlementArray() throws IllegalArgumentException{
		settlements = allSettlements.toArray(settlements);
	}
	
	public void updateRoadArray(){
		roads = allRoads.toArray(roads);
	}

	/**
	 * When a Settlement is upgraded to a City, the settlement should be eliminated,
	 * this function deletes the settlement from the array
	 * @param location
	 */
	public void deleteSettlement(VertexLocation location) throws IllegalArgumentException{
		int x = location.getHexLoc().getX();
		int y = location.getHexLoc().getY();
		VertexDirection direction = location.getDir();
		
		
		if(allSettlements.size() >0){
			for(int i = allSettlements.size()-1; i >=0; i--){
				int xTemp = allSettlements.get(i).getLocation().getHexLoc().getX();
				int yTemp = allSettlements.get(i).getLocation().getHexLoc().getY();
				VertexDirection directionTemp = allSettlements.get(i).getLocation().getDir();
	
				if( (x == xTemp) && (y == yTemp)){
					if(direction == directionTemp){
						allSettlements.remove(i);
						updateSettlementArray();
					}
				}
			}
		}
		

	}
	
	public void deleteRoad(EdgeLocation location) throws IllegalArgumentException{
		int x = location.getHexLoc().getX();
		int y = location.getHexLoc().getY();
		EdgeDirection direction = location.getDir();
		
		
		if(allRoads.size() >0){
			for(int i = allRoads.size()-1; i >=0; i--){
				int xTemp = allRoads.get(i).getLocation().getHexLoc().getX();
				int yTemp = allRoads.get(i).getLocation().getHexLoc().getY();
				EdgeDirection directionTemp = allRoads.get(i).getLocation().getDir();
	
				if( (x == xTemp) && (y == yTemp)){
					if(direction == directionTemp){
						allRoads.remove(i);
						updateRoadArray();
					}
				}
			}
		}
		
		HexLocation loc = location.getHexLoc();
		EdgeDirection oppDirection = direction.getOppositeDirection();
		HexLocation oppoLoc = getOppositeHex(loc,oppDirection);
		
		
		
		if(allRoads.size() >0){
			for(int i = allRoads.size()-1; i >=0; i--){
				int xTemp = allRoads.get(i).getLocation().getHexLoc().getX();
				int yTemp = allRoads.get(i).getLocation().getHexLoc().getY();
				EdgeDirection directionTemp = allRoads.get(i).getLocation().getDir();
	
				if( (x == xTemp) && (y == yTemp)){
					if(oppDirection == directionTemp){
						allRoads.remove(i);
						updateRoadArray();
					}
				}
			}
		}

	}
	
	public boolean layCity(VertexObject location) throws IllegalArgumentException{

		int x = location.getLocation().getHexLoc().getX(); 
		int y = location.getLocation().getHexLoc().getY();
		VertexDirection direction = location.getLocation().getDir();
		int owner = location.getOwner();

		//Save the road based on the "home" Hex

		buildCity(location);
		deleteSettlement(location.getLocation());


		//Save the road based on the adjacent Hexes		
		if(direction == VertexDirection.NorthWest){
			//Hex1
			if(isLand(new HexLocation(x-1,y))){
				VertexObject temp = vertexObjectFactory(owner, x-1, y, VertexDirection.East);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}
			//Hex2
			if(isLand(new HexLocation(x,y-1))){
				VertexObject temp = vertexObjectFactory(owner,x,y-1,VertexDirection.SouthWest);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}

		}
		else if(direction == VertexDirection.NorthEast){
			//Hex1
			if(isLand(new HexLocation(x,y-1))){
				VertexObject temp = vertexObjectFactory(owner, x, y-1, VertexDirection.SouthEast);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}
			//Hex2
			if(isLand(new HexLocation(x+1,y-1))){
				VertexObject temp = vertexObjectFactory(owner,x+1,y-1,VertexDirection.West);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}

		}
		else if(direction == VertexDirection.East){
			//Hex1
			if(isLand(new HexLocation(x+1,y-1))){
				VertexObject temp = vertexObjectFactory(owner, x+1, y-1, VertexDirection.SouthWest);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}
			//Hex2
			if(isLand(new HexLocation(x+1,y))){
				VertexObject temp = vertexObjectFactory(owner,x+1,y,VertexDirection.NorthWest);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}
		}
		else if(direction == VertexDirection.SouthEast){
			//Hex1
			if(isLand(new HexLocation(x+1,y))){
				VertexObject temp = vertexObjectFactory(owner, x+1, y, VertexDirection.West);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}
			//Hex2
			if(isLand(new HexLocation(x,y+1))){
				VertexObject temp = vertexObjectFactory(owner,x,y+1,VertexDirection.NorthEast);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}
		}
		else if(direction == VertexDirection.SouthWest){
			//Hex1
			if(isLand(new HexLocation(x-1,y+1))){
				VertexObject temp = vertexObjectFactory(owner, x-1, y+1, VertexDirection.East);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}
			//Hex2
			if(isLand(new HexLocation(x,y+1))){
				VertexObject temp = vertexObjectFactory(owner,x,y+1,VertexDirection.NorthWest);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}

		}
		else if(direction == VertexDirection.West){
			//Hex1
			if(isLand(new HexLocation(x-1,y+1))){
				VertexObject temp = vertexObjectFactory(owner, x-1, y+1, VertexDirection.NorthEast);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}
			//Hex2
			if(isLand(new HexLocation(x-1,y))){
				VertexObject temp = vertexObjectFactory(owner,x-1,y,VertexDirection.SouthEast);
				buildCity(temp);
				deleteSettlement(temp.getLocation());
			}
		}

		//The City was laid successfully
		return true;
	}
	
	public boolean twoRoadChecker(HexLocation homeHex, VertexDirection vDirection, int owner) throws IllegalArgumentException{
		
		if(vDirection == VertexDirection.NorthWest){
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.North);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.NorthEast);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.North), EdgeDirection.South);
			EdgeValue road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			EdgeValue road4 = new EdgeValue(owner,location2);
			boolean hasRoad3 = this.hasRoadPersonal(road3);
			boolean hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			/////
			location1 = new EdgeLocation(homeHex, EdgeDirection.NorthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.SouthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			
			location1 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			//////
			HexLocation oppNorth = this.getOppositeHex(homeHex, EdgeDirection.North);
			location1 = new EdgeLocation(oppNorth, EdgeDirection.South);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorth, EdgeDirection.SouthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorth,EdgeDirection.South), EdgeDirection.North);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorth,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			////
			location1 = new EdgeLocation(oppNorth, EdgeDirection.SouthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorth, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorth,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorth,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			//////
			HexLocation oppNorthWest = this.getOppositeHex(homeHex, EdgeDirection.NorthWest);
			location1 = new EdgeLocation(oppNorthWest, EdgeDirection.South);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthWest, EdgeDirection.SouthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorthWest,EdgeDirection.South), EdgeDirection.North);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorthWest,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			
			/////
			location1 = new EdgeLocation(oppNorthWest, EdgeDirection.NorthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthWest, EdgeDirection.North);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorthWest,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorthWest,EdgeDirection.North), EdgeDirection.South);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
		}
		else if (vDirection == VertexDirection.NorthEast){
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.SouthEast);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.NorthEast);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			EdgeValue road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			EdgeValue road4 = new EdgeValue(owner,location2);
			boolean hasRoad3 = this.hasRoadPersonal(road3);
			boolean hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			///////
			location1 = new EdgeLocation(homeHex, EdgeDirection.NorthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.North);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.North), EdgeDirection.South);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			///////
			HexLocation oppNorth = this.getOppositeHex(homeHex, EdgeDirection.North);
			location1 = new EdgeLocation(oppNorth, EdgeDirection.South);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorth, EdgeDirection.SouthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorth,EdgeDirection.South), EdgeDirection.North);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorth,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			/////
			location1 = new EdgeLocation(oppNorth, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorth, EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorth,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorth,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			//////
			HexLocation oppNorthEast = this.getOppositeHex(homeHex, EdgeDirection.NorthEast);
			location1 = new EdgeLocation(oppNorthEast, EdgeDirection.South);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthEast, EdgeDirection.SouthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorthEast,EdgeDirection.South), EdgeDirection.North);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorthEast,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			//////
			location1 = new EdgeLocation(oppNorthEast,EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthEast,EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorthEast,EdgeDirection.North), EdgeDirection.South);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorthEast,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
		}
		else if (vDirection == VertexDirection.East){
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.North);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.NorthEast);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.North), EdgeDirection.South);
			EdgeValue road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			EdgeValue road4 = new EdgeValue(owner,location2);
			boolean hasRoad3 = this.hasRoadPersonal(road3);
			boolean hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			////
			location1 = new EdgeLocation(homeHex, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.South);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.South), EdgeDirection.North);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			/////
			HexLocation oppNorthEast = this.getOppositeHex(homeHex, EdgeDirection.NorthEast);
			location1 = new EdgeLocation(oppNorthEast, EdgeDirection.NorthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthEast, EdgeDirection.SouthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorthEast,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorthEast,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			///////
			location1 = new EdgeLocation(oppNorthEast, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthEast, EdgeDirection.South);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorthEast,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorthEast,EdgeDirection.South), EdgeDirection.North);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			//////
			HexLocation oppSouthEast = this.getOppositeHex(homeHex, EdgeDirection.SouthEast);
			location1 = new EdgeLocation(oppSouthEast, EdgeDirection.NorthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthEast, EdgeDirection.SouthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouthEast,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouthEast,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			////
			location1 = new EdgeLocation(oppSouthEast, EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthEast, EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouthEast,EdgeDirection.North), EdgeDirection.South);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouthEast,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
		}
		else if (vDirection == VertexDirection.SouthEast){
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.South);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.SouthWest);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			location1 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.South), EdgeDirection.North);
			EdgeValue road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			EdgeValue road4 = new EdgeValue(owner,location2);
			boolean hasRoad3 = this.hasRoadPersonal(road3);
			boolean hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			////
			location1 = new EdgeLocation(homeHex, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			//////
			HexLocation oppSouthEast = this.getOppositeHex(homeHex, EdgeDirection.SouthEast);
			location1 = new EdgeLocation(oppSouthEast, EdgeDirection.NorthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthEast, EdgeDirection.North);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouthEast,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouthEast,EdgeDirection.North), EdgeDirection.South);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			////
			location1 = new EdgeLocation(oppSouthEast, EdgeDirection.SouthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthEast, EdgeDirection.South);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouthEast,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouthEast,EdgeDirection.South), EdgeDirection.North);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			/////
			HexLocation oppSouth = this.getOppositeHex(homeHex, EdgeDirection.South);
			location1 = new EdgeLocation(oppSouth, EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouth, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouth,EdgeDirection.North), EdgeDirection.South);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouth,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			/////
			location1 = new EdgeLocation(oppSouth, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouth, EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouth,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouth,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
		}
		
		else if (vDirection == VertexDirection.SouthWest){
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.South);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.SouthEast);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.South), EdgeDirection.North);
			EdgeValue road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			EdgeValue road4 = new EdgeValue(owner,location2);
			boolean hasRoad3 = this.hasRoadPersonal(road3);
			boolean hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			////
			location1 = new EdgeLocation(homeHex, EdgeDirection.SouthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			////
			HexLocation oppSouth = this.getOppositeHex(homeHex, EdgeDirection.South);
			location1 = new EdgeLocation(oppSouth, EdgeDirection.NorthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouth, EdgeDirection.North);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouth,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouth,EdgeDirection.North), EdgeDirection.South);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			/////
			location1 = new EdgeLocation(oppSouth, EdgeDirection.SouthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouth, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouth,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouth,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			//////
			HexLocation oppSouthWest = this.getOppositeHex(homeHex, EdgeDirection.SouthWest);
			location1 = new EdgeLocation(oppSouthWest, EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthWest, EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouthWest,EdgeDirection.North), EdgeDirection.South);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouthWest,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			/////
			location1 = new EdgeLocation(oppSouthWest, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthWest, EdgeDirection.South);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouthWest,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouthWest,EdgeDirection.South), EdgeDirection.North);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
		}
		else if (vDirection == VertexDirection.West){
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.South);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.SouthWest);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.South), EdgeDirection.North);
			EdgeValue road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			EdgeValue road4 = new EdgeValue(owner,location2);
			boolean hasRoad3 = this.hasRoadPersonal(road3);
			boolean hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			////
			location1 = new EdgeLocation(homeHex, EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.North), EdgeDirection.South);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			////
			HexLocation oppSouthWest = this.getOppositeHex(homeHex, EdgeDirection.SouthWest);
			location1 = new EdgeLocation(oppSouthWest, EdgeDirection.NorthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthWest, EdgeDirection.SouthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouthWest,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouthWest,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			////
			location1 = new EdgeLocation(oppSouthWest, EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthWest, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppSouthWest,EdgeDirection.North), EdgeDirection.South);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppSouthWest,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			HexLocation oppNorthWest = this.getOppositeHex(homeHex, EdgeDirection.NorthWest);
			location1 = new EdgeLocation(oppNorthWest, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthWest, EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorthWest,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorthWest,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
			////
			location1 = new EdgeLocation(oppNorthWest, EdgeDirection.SouthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthWest, EdgeDirection.South);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(oppNorthWest,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road3 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(oppNorthWest,EdgeDirection.South), EdgeDirection.North);
			road4 = new EdgeValue(owner,location2);
			hasRoad3 = this.hasRoadPersonal(road3);
			hasRoad4 = this.hasRoadPersonal(road4);
			if(hasRoad3 == true && hasRoad4 == true){
				return true;
			}
			if(hasRoad1 ==true && hasRoad4 ==true){
				return true;
			}
			if(hasRoad2 ==true && hasRoad3 ==true){
				return true;
			}
			
		}
		
		return false;
	}
	
	public boolean oneRoadChecker(HexLocation homeHex, VertexDirection vDirection, int owner) throws IllegalArgumentException{
		
		if(vDirection == VertexDirection.NorthWest){
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.North);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.NorthWest);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.NorthWest), EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.North), EdgeDirection.South);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.North), EdgeDirection.SouthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
		}
		else if (vDirection == VertexDirection.NorthEast){
			
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.North);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.NorthEast);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.North), EdgeDirection.South);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.North), EdgeDirection.SouthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.NorthEast), EdgeDirection.NorthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
		}
		else if (vDirection == VertexDirection.East){
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.NorthEast);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.SouthEast);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.NorthEast), EdgeDirection.South);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.NorthEast), EdgeDirection.SouthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.SouthEast), EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
		}
		else if (vDirection == VertexDirection.SouthEast){
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.South);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.SouthEast);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.South), EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.South), EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.SouthEast), EdgeDirection.SouthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthEast), EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
		}
		
		else if (vDirection == VertexDirection.SouthWest){
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.South);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.SouthWest);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.South), EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.South), EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthWest), EdgeDirection.SouthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
		}
		else if (vDirection == VertexDirection.West){
			EdgeLocation location1 = new EdgeLocation(homeHex, EdgeDirection.NorthWest);
			EdgeValue road1 = new EdgeValue(owner,location1);
			EdgeLocation location2 = new EdgeLocation(homeHex, EdgeDirection.SouthWest);
			EdgeValue road2 = new EdgeValue(owner,location2);
			Boolean hasRoad1 = this.hasRoadPersonal(road1);
			Boolean hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.NorthWest), EdgeDirection.South);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.NorthWest), EdgeDirection.SouthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
			
			location1 = new EdgeLocation(getOppositeHex(homeHex, EdgeDirection.SouthWest), EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(getOppositeHex(homeHex,EdgeDirection.SouthWest), EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true || hasRoad2 == true){
				return true;
			}
		}
		return false;
	}
	/**
	 * Determines if a player can build a settlement at a location
	 * @param settlement
	 * @return boolean
	 * @throws IllegalArgumentException
	 */
	public boolean canBuildSettlement(VertexObject settlement) throws IllegalArgumentException{
		
		int owner = settlement.getOwner();
		VertexLocation location = settlement.getLocation();
		HexLocation hexLoc = location.getHexLoc();
		VertexDirection vertexDirection = location.getDir();
		
		Boolean hasProperty = this.hasMunicipality(location);
		if(hasProperty == true){
			return false;
		}
		
		if(vertexDirection == VertexDirection.NorthWest){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.NorthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.West);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.North);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.West);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			Boolean hasTwoRoads = this.twoRoadChecker(hexLoc, vertexDirection, owner);
			if(hasTwoRoads == true){
				return true;
			}
			else{
				return false;
			}
		}
		else if(vertexDirection == VertexDirection.NorthEast){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.East);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.NorthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.North);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.East);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			Boolean hasTwoRoads = this.twoRoadChecker(hexLoc, vertexDirection, owner);
			if(hasTwoRoads == true){
				return true;
			}
			else{
				return false;
			}
		}
		
		else if(vertexDirection == VertexDirection.East){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.NorthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.SouthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.NorthEast);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.SouthEast);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			Boolean hasTwoRoads = this.twoRoadChecker(hexLoc, vertexDirection, owner);
			if(hasTwoRoads == true){
				return true;
			}
			else{
				return false;
			}
		}
		else if(vertexDirection == VertexDirection.SouthEast){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.East);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.SouthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.South);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.East);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			Boolean hasTwoRoads = this.twoRoadChecker(hexLoc, vertexDirection, owner);
			if(hasTwoRoads == true){
				return true;
			}
			else{
				return false;
			}
		}
		else if(vertexDirection == VertexDirection.SouthWest){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.SouthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.West);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.South);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.West);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			Boolean hasTwoRoads = this.twoRoadChecker(hexLoc, vertexDirection, owner);
			if(hasTwoRoads == true){
				return true;
			}
			else{
				return false;
			}
		}
		else if(vertexDirection == VertexDirection.West){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.SouthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.NorthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.SouthWest);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.NorthWest);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			Boolean hasTwoRoads = this.twoRoadChecker(hexLoc, vertexDirection, owner);
			if(hasTwoRoads == true){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	public boolean canBuildSettlementFirstRound(VertexObject settlement) throws IllegalArgumentException{
		
		int owner = settlement.getOwner();
		VertexLocation location = settlement.getLocation();
		HexLocation hexLoc = location.getHexLoc();
		VertexDirection vertexDirection = location.getDir();
		
		Boolean hasProperty = this.hasMunicipality(location);
		if(hasProperty == true){
			return false;
		}
		
		if(vertexDirection == VertexDirection.NorthWest){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.NorthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.West);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.North);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.West);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			
			
			//TODO: Look at this
			Boolean hasOneRoad = this.oneRoadChecker(hexLoc, vertexDirection, owner);
			if(hasOneRoad == true){
				return true;
			}
			else{
				return false;
			}
			
		}
		else if(vertexDirection == VertexDirection.NorthEast){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.East);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.NorthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.North);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.East);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			Boolean hasOneRoad = this.oneRoadChecker(hexLoc, vertexDirection, owner);
			if(hasOneRoad == true){
				return true;
			}
			else{
				return false;
			}
		}
		
		else if(vertexDirection == VertexDirection.East){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.NorthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.SouthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.NorthEast);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.SouthEast);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			Boolean hasOneRoad = this.oneRoadChecker(hexLoc, vertexDirection, owner);
			if(hasOneRoad == true){
				return true;
			}
			else{
				return false;
			}
		}
		else if(vertexDirection == VertexDirection.SouthEast){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.East);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.SouthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.South);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.East);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			Boolean hasOneRoad = this.oneRoadChecker(hexLoc, vertexDirection, owner);
			if(hasOneRoad == true){
				return true;
			}
			else{
				return false;
			}
		}
		else if(vertexDirection == VertexDirection.SouthWest){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.SouthEast);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.West);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.South);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.West);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			Boolean hasOneRoad = this.oneRoadChecker(hexLoc, vertexDirection, owner);
			if(hasOneRoad == true){
				return true;
			}
			else{
				return false;
			}
			
		}
		else if(vertexDirection == VertexDirection.West){
			VertexLocation property = new VertexLocation(hexLoc, VertexDirection.SouthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			property = new VertexLocation(hexLoc, VertexDirection.NorthWest);
			hasProperty = this.hasMunicipality(property);
			if(hasProperty == true){
				return false;
			}
			HexLocation oppositeHex = this.getOppositeHex(hexLoc, EdgeDirection.SouthWest);
			Boolean isHexLand = this.isLand(oppositeHex);
			
				property = new VertexLocation(oppositeHex, VertexDirection.NorthWest);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
			
			Boolean hasOneRoad = this.oneRoadChecker(hexLoc, vertexDirection, owner);
			if(hasOneRoad == true){
				return true;
			}
			else{
				return false;
			}
			
		}
		return false;
	}
	/**
	 * determines if a player can build a city at a location
	 * @param city
	 * @return boolean
	 * @throws IllegalArgumentException
	 */
	public boolean canBuildCity(VertexObject city) throws IllegalArgumentException{
		return hasSettlement(city);
	}
	/**
	 * determines if a player can lay robber
	 * @param robberLocation
	 * @return boolean
	 * @throws IllegalArgumentException
	 */
	public boolean canLayRobber(HexLocation robberLocation) throws IllegalArgumentException{
		Boolean isHexLand = this.isLand(robberLocation);
		if(isHexLand == false){
			return false;
		}
		else{
			int xCoord = robberLocation.getX();
			int yCoord = robberLocation.getY();
			int xCoordOldRobber = robber.getX();
			int yCoordOldRobber = robber.getY();
			if(xCoord == xCoordOldRobber && yCoord == yCoordOldRobber){
				return false;
			}			
		}
		return true;
	}
	public VertexObject findMunicipality(VertexLocation location) {
		for (VertexObject obj : allSettlements) {
			if (obj.getLocation().getNormalizedLocation().equals(location.getNormalizedLocation()))
				return obj;
		}
		for (VertexObject obj : allCities) {
			if (obj.getLocation().getNormalizedLocation().equals(location.getNormalizedLocation()))
				return obj;
		}
		return null;
	}
	public List<VertexObject> getBorderingMunicipalities(HexLocation hexLoc) {
		List<VertexObject> municipalities = new ArrayList<VertexObject>();
		VertexObject v = null;
		v = findMunicipality(hexLoc.getBorderingVertex(VertexDirection.NorthWest));
		if (v != null) municipalities.add(v);
		v = findMunicipality(hexLoc.getBorderingVertex(VertexDirection.NorthEast));
		if (v != null) municipalities.add(v);
		v = findMunicipality(hexLoc.getBorderingVertex(VertexDirection.SouthWest));
		if (v != null) municipalities.add(v);
		v = findMunicipality(hexLoc.getBorderingVertex(VertexDirection.SouthEast));
		if (v != null) municipalities.add(v);
		v = findMunicipality(hexLoc.getBorderingVertex(VertexDirection.West));
		if (v != null) municipalities.add(v);
		v = findMunicipality(hexLoc.getBorderingVertex(VertexDirection.East));
		if (v != null) municipalities.add(v);
		return municipalities;
		
	}
	
}
