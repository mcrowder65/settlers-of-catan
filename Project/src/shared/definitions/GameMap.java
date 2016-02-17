package shared.definitions;

import shared.locations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
	private Hex[] hexes;
	/**
	 * Array of all the ports on the map
	 */
	private Port[] ports;
	/**
	 * Array of all the roads on the map
	 */
	private EdgeValue[] roads;
	/**
	 * Array of all the settlements on the map
	 */
	private VertexObject[] settlements;
	/**
	 * Array of all the cities on the map
	 */
	private VertexObject[] cities;
	/**
	 * Radius of the map
	 */
	private int radius;
	/**
	 * Location of the robber on the map
	 */
	private HexLocation robber;
	private List<EdgeValue>allRoads = new ArrayList<EdgeValue>();
	private List<VertexObject>allSettlements = new ArrayList<VertexObject>();
	private List<VertexObject>allCities = new ArrayList<VertexObject>();
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
		this.allRoads = Arrays.asList(roads);
		this.allSettlements = Arrays.asList(settlements);
		this.allCities = Arrays.asList(cities);
		this.allPorts = Arrays.asList(ports);
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
	 * @param personalRoad
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
	
	public Boolean canLayRoad(EdgeValue value) throws IllegalArgumentException{
		int owner = value.getOwner();
		EdgeLocation location = value.getLocation();
		HexLocation hex = location.getHexLoc();
		Boolean isLand = this.isLand(hex);
		EdgeDirection direction = location.getDir();
		if(isLand == false){
			return false;
		}
		
		Boolean hasRoad = this.hasRoadAllPlayers(location);
		if(hasRoad == true){
			return false;
		}
		
		if(direction == EdgeDirection.SouthWest){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.NorthWest);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return true;
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.South);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return true;
			}
			
			HexLocation oppositeHex = this.getOppositeHex(hex, direction);
			
			Boolean isHexLand = this.isLand(oppositeHex);
			if(isHexLand == true){
				if(isHexLand == true){
					newLocation = new EdgeLocation(oppositeHex,EdgeDirection.SouthEast);
					road = new EdgeValue(owner, newLocation);
					alreadyHasRoad = this.hasRoadPersonal(road);
					if(alreadyHasRoad == true){
						return true;
					}
					
					newLocation = new EdgeLocation(oppositeHex,EdgeDirection.North);
					road = new EdgeValue(owner, newLocation);
					alreadyHasRoad = this.hasRoadPersonal(road);
					if(alreadyHasRoad == true){
						return true;
					}
				}
			}
			
			
		}
		else if(direction == EdgeDirection.NorthWest){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.SouthWest);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return false;
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.North);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return true;
			}
			
			HexLocation oppositeHex = this.getOppositeHex(hex, direction);
			Boolean isHexLand = this.isLand(oppositeHex);
			if(isHexLand == true){
				newLocation = new EdgeLocation(oppositeHex,EdgeDirection.NorthEast);
				road = new EdgeValue(owner, newLocation);
				alreadyHasRoad = this.hasRoadPersonal(road);
				if(alreadyHasRoad == true){
					return true;
				}
				
				newLocation = new EdgeLocation(oppositeHex,EdgeDirection.South);
				road = new EdgeValue(owner, newLocation);
				alreadyHasRoad = this.hasRoadPersonal(road);
				if(alreadyHasRoad == true){
					return true;
				}
			}
		}
		else if(direction == EdgeDirection.North){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.NorthWest);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return true;
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.NorthEast);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return true;
			}
			
			HexLocation oppositeHex = this.getOppositeHex(hex, direction);
			Boolean isHexLand = this.isLand(oppositeHex);
			if(isHexLand == true){
				newLocation = new EdgeLocation(oppositeHex,EdgeDirection.SouthEast);
				road = new EdgeValue(owner, newLocation);
				alreadyHasRoad = this.hasRoadPersonal(road);
				if(alreadyHasRoad == true){
					return true;
				}
				
				newLocation = new EdgeLocation(oppositeHex,EdgeDirection.SouthWest);
				road = new EdgeValue(owner, newLocation);
				alreadyHasRoad = this.hasRoadPersonal(road);
				if(alreadyHasRoad == true){
					return true;
				}
			}
		}
		else if(direction == EdgeDirection.NorthEast){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.North);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return true;
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.SouthEast);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return true;
			}
			HexLocation oppositeHex = this.getOppositeHex(hex, direction);
			Boolean isHexLand = this.isLand(oppositeHex);
			if(isHexLand == true){
				newLocation = new EdgeLocation(oppositeHex,EdgeDirection.South);
				road = new EdgeValue(owner, newLocation);
				alreadyHasRoad = this.hasRoadPersonal(road);
				if(alreadyHasRoad == true){
					return true;
				}
				
				newLocation = new EdgeLocation(oppositeHex,EdgeDirection.NorthWest);
				road = new EdgeValue(owner, newLocation);
				alreadyHasRoad = this.hasRoadPersonal(road);
				if(alreadyHasRoad == true){
					return true;
				}
			}
		}
		else if(direction == EdgeDirection.SouthEast){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.NorthEast);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return true;
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.South);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return true;
			}
			HexLocation oppositeHex = this.getOppositeHex(hex, direction);
			
			Boolean isHexLand = this.isLand(oppositeHex);
			if(isHexLand == true){
				newLocation = new EdgeLocation(oppositeHex,EdgeDirection.SouthWest);
				road = new EdgeValue(owner, newLocation);
				alreadyHasRoad = this.hasRoadPersonal(road);
				if(alreadyHasRoad == true){
					return true;
				}
				
				newLocation = new EdgeLocation(oppositeHex,EdgeDirection.North);
				road = new EdgeValue(owner, newLocation);
				alreadyHasRoad = this.hasRoadPersonal(road);
				if(alreadyHasRoad == true){
					return true;
				}
			}
		}
		else if(direction == EdgeDirection.South){
			EdgeLocation newLocation = new EdgeLocation(hex,EdgeDirection.SouthWest);
			EdgeValue road = new EdgeValue(owner, newLocation);
			Boolean alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return true;
			}
			newLocation = new EdgeLocation(hex,EdgeDirection.SouthEast);
			road = new EdgeValue(owner, newLocation);
			alreadyHasRoad = this.hasRoadPersonal(road);
			if(alreadyHasRoad == true){
				return true;
			}
			HexLocation oppositeHex = this.getOppositeHex(hex, direction);
			
			Boolean isHexLand = this.isLand(oppositeHex);
			if(isHexLand == true){
				newLocation = new EdgeLocation(oppositeHex,EdgeDirection.NorthEast);
				road = new EdgeValue(owner, newLocation);
				alreadyHasRoad = this.hasRoadPersonal(road);
				if(alreadyHasRoad == true){
					return true;
				}
				
				newLocation = new EdgeLocation(oppositeHex,EdgeDirection.NorthWest);
				road = new EdgeValue(owner, newLocation);
				alreadyHasRoad = this.hasRoadPersonal(road);
				if(alreadyHasRoad == true){
					return true;
				}
			}
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
		
			}
		}
		else{
			return true;
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
		this.allPorts = Arrays.asList(this.ports);
	}
	/**
	 * sets all the roads on the map
	 * @param roads
	 */
	public void setRoads(EdgeValue[] roads) throws IllegalArgumentException  {
		this.roads = roads;
		this.allRoads = Arrays.asList(this.roads);
	}
	/**
	 * sets all the settlements on the map
	 * @param settlements
	 */
	public void setSettlements(VertexObject[] settlements) throws IllegalArgumentException  {
		this.settlements = settlements;
		this.allSettlements = Arrays.asList(this.settlements);
	}
	/**
	 * sets all the cities on the map
	 * @param cities
	 */
	public void setCities(VertexObject[] cities) throws IllegalArgumentException  {
		this.cities = cities;
		this.allCities = Arrays.asList(this.cities);
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
	public void buildSettlement(VertexObject settlementToBuild) throws IllegalArgumentException {
		allSettlements.add(settlementToBuild);
		settlements = new VertexObject[allSettlements.size()];
		settlements = allSettlements.toArray(settlements);
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
	 * @param settlementToBuild
	 */
	public boolean laySettlement(VertexObject location){
		int x = location.getLocation().getHexLoc().getX(); 
		int y = location.getLocation().getHexLoc().getY();
		VertexDirection direction = location.getLocation().getDir();
		int owner = location.getOwner();

		//Save the road based on the "home" Hex

		buildSettlement(location);


		//Save the road based on the adjacent Hexes		
		if(direction == VertexDirection.NorthWest){
			//Hex1
			if(isLand(new HexLocation(x-1,y))){
				VertexObject temp = vertexObjectFactory(owner, x-1, y, VertexDirection.East);
				buildSettlement(temp);
			}
			//Hex2
			if(isLand(new HexLocation(x,y-1))){
				VertexObject temp = vertexObjectFactory(owner,x,y-1,VertexDirection.SouthWest);
				buildSettlement(temp);
			}

		}
		else if(direction == VertexDirection.NorthEast){
			//Hex1
			if(isLand(new HexLocation(x,y-1))){
				VertexObject temp = vertexObjectFactory(owner, x, y-1, VertexDirection.SouthEast);
				buildSettlement(temp);
			}
			//Hex2
			if(isLand(new HexLocation(x+1,y-1))){
				VertexObject temp = vertexObjectFactory(owner,x+1,y-1,VertexDirection.West);
				buildSettlement(temp);
			}

		}
		else if(direction == VertexDirection.East){
			//Hex1
			if(isLand(new HexLocation(x+1,y-1))){
				VertexObject temp = vertexObjectFactory(owner, x+1, y-1, VertexDirection.SouthWest);
				buildSettlement(temp);
			}
			//Hex2
			if(isLand(new HexLocation(x+1,y))){
				VertexObject temp = vertexObjectFactory(owner,x+1,y,VertexDirection.NorthWest);
				buildSettlement(temp);
			}
		}
		else if(direction == VertexDirection.SouthEast){
			//Hex1
			if(isLand(new HexLocation(x+1,y))){
				VertexObject temp = vertexObjectFactory(owner, x+1, y, VertexDirection.West);
				buildSettlement(temp);
			}
			//Hex2
			if(isLand(new HexLocation(x,y+1))){
				VertexObject temp = vertexObjectFactory(owner,x,y+1,VertexDirection.NorthEast);
				buildSettlement(temp);
			}
		}
		else if(direction == VertexDirection.SouthWest){
			//Hex1
			if(isLand(new HexLocation(x-1,y+1))){
				VertexObject temp = vertexObjectFactory(owner, x-1, y+1, VertexDirection.East);
				buildSettlement(temp);
			}
			//Hex2
			if(isLand(new HexLocation(x,y+1))){
				VertexObject temp = vertexObjectFactory(owner,x,y+1,VertexDirection.NorthWest);
				buildSettlement(temp);
			}

		}
		else if(direction == VertexDirection.West){
			//Hex1
			if(isLand(new HexLocation(x-1,y+1))){
				VertexObject temp = vertexObjectFactory(owner, x-1, y+1, VertexDirection.NorthEast);
				buildSettlement(temp);
			}
			//Hex2
			if(isLand(new HexLocation(x-1,y))){
				VertexObject temp = vertexObjectFactory(owner,x-1,y,VertexDirection.SouthEast);
				buildSettlement(temp);
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
			
			location1 = new EdgeLocation(homeHex, EdgeDirection.NorthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.SouthWest);
			road2 = new EdgeValue(owner,location2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
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
			
			location1 = new EdgeLocation(oppNorth, EdgeDirection.SouthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorth, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
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
			
			location1 = new EdgeLocation(oppNorthWest, EdgeDirection.NorthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthWest, EdgeDirection.North);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
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
			
			location1 = new EdgeLocation(homeHex, EdgeDirection.NorthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.North);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
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
			
			location1 = new EdgeLocation(oppNorth, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorth, EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
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
			
			location1 = new EdgeLocation(oppNorthEast, EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthEast, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
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
			
			location1 = new EdgeLocation(homeHex, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.South);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
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
			
			location1 = new EdgeLocation(oppNorthEast, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthEast, EdgeDirection.South);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
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
			
			location1 = new EdgeLocation(oppSouthEast, EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthEast, EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
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
			
			location1 = new EdgeLocation(homeHex, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
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
			
			location1 = new EdgeLocation(oppSouthEast, EdgeDirection.SouthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthEast, EdgeDirection.South);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
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
			
			location1 = new EdgeLocation(oppSouth, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouth, EdgeDirection.NorthEast);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
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
			
			location1 = new EdgeLocation(homeHex, EdgeDirection.SouthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
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
			
			location1 = new EdgeLocation(oppSouth, EdgeDirection.SouthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouth, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
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
			
			location1 = new EdgeLocation(oppSouthWest, EdgeDirection.SouthEast);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthWest, EdgeDirection.South);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
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
			
			location1 = new EdgeLocation(homeHex, EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(homeHex, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
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
			
			location1 = new EdgeLocation(oppSouthWest, EdgeDirection.North);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppSouthWest, EdgeDirection.NorthWest);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
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
			
			location1 = new EdgeLocation(oppNorthWest, EdgeDirection.SouthWest);
			road1 = new EdgeValue(owner,location1);
			location2 = new EdgeLocation(oppNorthWest, EdgeDirection.South);
			road2 = new EdgeValue(owner,location2);
			hasRoad1 = this.hasRoadPersonal(road1);
			hasRoad2 = this.hasRoadPersonal(road2);
			if(hasRoad1 == true && hasRoad2 == true){
				return true;
			}
			
		}
		
		return false;
	}
	
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
			if(isHexLand == true){
				property = new VertexLocation(oppositeHex, VertexDirection.West);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
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
			if(isHexLand == true){
				property = new VertexLocation(oppositeHex, VertexDirection.East);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
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
			if(isHexLand == true){
				property = new VertexLocation(oppositeHex, VertexDirection.SouthEast);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
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
			if(isHexLand == true){
				property = new VertexLocation(oppositeHex, VertexDirection.East);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
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
			if(isHexLand == true){
				property = new VertexLocation(oppositeHex, VertexDirection.West);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
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
			if(isHexLand == true){
				property = new VertexLocation(oppositeHex, VertexDirection.NorthWest);
				hasProperty = this.hasMunicipality(property);
				if(hasProperty == true){
					return false;
				}
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
	
	public boolean canBuildCity(VertexObject city) throws IllegalArgumentException{
		return hasSettlement(city);
	}
	
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
}
