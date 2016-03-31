package server.util;

import shared.definitions.GameMap;
import shared.definitions.Hex;
import shared.definitions.Port;
import shared.locations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import client.data.RobPlayerInfo;

/**
 * Class to represent the entire map
 * Contains Hexes, ports, roads, settlemetns, cities, a radius, and a robber
 * @author Brennen
 *
 */
public class ServerGameMap extends GameMap {

	/**
	 * constructor for ServerGameMap - uses the constructor for GameMap
	 * @param hexes
	 * @param ports
	 * @param roads
	 * @param settlements
	 * @param cities
	 * @param radius
	 * @param robber
	 * @throws IllegalArgumentException
	 */
	public ServerGameMap(Hex[] hexes, Port[] ports, EdgeValue[] roads,
			VertexObject[] settlements, VertexObject[] cities, int radius,
			HexLocation robber) throws IllegalArgumentException  {
		
		super(hexes, ports, roads,settlements, cities, radius,robber);
	}
	
	/**
	 * blank constructor for testing
	 */
	public ServerGameMap() {
		
	}

	/**
	 * checks to see if you can build a road during the first two rounds
	 * @param index
	 * @param loc
	 * @return boolean
	 */
	public boolean canBuildRoadSetup(int index, EdgeLocation loc){
		EdgeValue road = new EdgeValue(index,loc);
		HexLocation location = loc.getHexLoc();
		HexLocation oppositeHex = getOppositeHex(location,loc.getDir());
		//checks to see if you are trying to build a road on water
		if(isLand(location) == false && isLand(oppositeHex) == false){
			return false;
		}
		//checks to see if there is already a road there
		if(hasRoadAllPlayers(loc) == true){
			return false;
		}
		return canLayRoadFirstRounds(road);
	}
	/**
	 * checks to see if you can build a road during normal play - no roadbuilder card
	 * @param index
	 * @param loc
	 * @return boolean
	 */
	public boolean canBuildRoadNormal(int index, EdgeLocation loc){
		EdgeValue road = new EdgeValue(index,loc);
		HexLocation location = loc.getHexLoc();
		HexLocation oppositeHex = getOppositeHex(location,loc.getDir());
		//checks to see if you are trying to build a road on water
		if(isLand(location) == false && isLand(oppositeHex) == false){
			return false;
		}
		return canLayRoad(road);
	}
	
	/**
	 * gets all the cities or settlements on a particular hex
	 * @param loc
	 * @return List<VertexObject>
	 */
	public List<VertexObject> getMunicipalityOnHex(HexLocation loc){
 		List<VertexObject>municipalities = new ArrayList<VertexObject>();
 		VertexObject[] settlements = getSettlements();
 		VertexObject[] cities = getCities();
 		//going through all the settlements
 		if(this.allSettlements.size()>0){
	 		for(int i=0; i<settlements.length; i++){
	 			VertexObject settlement = settlements[i];
	 			if(settlement.getLocation().getHexLoc().equals(loc) == true){
	 				municipalities.add(settlement);
	 			}
	 		}
 		}
 		//going through all the cities
 		if(this.allCities.size() >0){
	 		for(int x=0; x<cities.length; x++){
	 			VertexObject city = cities[x];
	 			if(city.getLocation().getHexLoc().equals(loc) == true){
	 				municipalities.add(city);
	 			}
	 		}
 		}
 		
 		//returning cities and settlements together
 		return municipalities;
 		
 	}
	
	/**
	 * gets all the cities on a particular hex
	 * @param loc
	 * @return List<VertexObject>
	 */
	public List<VertexObject> getCityOnHex(HexLocation loc){
 		List<VertexObject>municipalities = new ArrayList<VertexObject>();
 		VertexObject[] cities = getCities();
 		
 		//makes an arrayList with all the cities on a particular hex
 		if(allCities.size()>0){
	 		for(int x=0; x<cities.length; x++){
	 			VertexObject city = cities[x];
	 			if(city.getLocation().getHexLoc().equals(loc) == true){
	 				municipalities.add(city);
	 			}
	 		}
 		}
 		return municipalities;
 	}
	
	/**
	 * gets all the settlements on a particular hex
	 * @param loc
	 * @return List<VertexObject>
	 */
	public List<VertexObject> getSettlementOnHex(HexLocation loc){
 		List<VertexObject>municipalities = new ArrayList<VertexObject>();
 		VertexObject[] settlements = getSettlements();
 		
 		//makes an arrayList with all the settlements on a particular hex
 		if(allSettlements.size()>0){
	 		for(int i=0; i<settlements.length; i++){
	 			VertexObject settlement = settlements[i];
	 			VertexLocation vLoc = settlement.getLocation();
	 			if (vLoc == null)
	 				System.out.println("error loc was null");
	 			HexLocation hLoc = vLoc.getHexLoc();
	 			if (hLoc == null)
	 				System.out.println("error hex was null");
	 			if(hLoc.equals(loc) == true){
	 				municipalities.add(settlement);
	 			}
	 		}
 		}
 		
 		return municipalities;
 		
 	}
	
	
	public List<Hex> getSurroundingHexes(VertexLocation loc) {
		List<Hex> surrounding = new ArrayList<Hex>();
	
		for (Hex hex : this.getHexes()) {
			if (hex.getLocation().getBorderingVertex(VertexDirection.West).getNormalizedLocation().equals(loc.getNormalizedLocation()))
				surrounding.add(hex);
			else if (hex.getLocation().getBorderingVertex(VertexDirection.NorthWest).getNormalizedLocation().equals(loc.getNormalizedLocation()))
				surrounding.add(hex);
			else if (hex.getLocation().getBorderingVertex(VertexDirection.NorthEast).getNormalizedLocation().equals(loc.getNormalizedLocation()))
				surrounding.add(hex);
			else if (hex.getLocation().getBorderingVertex(VertexDirection.SouthWest).getNormalizedLocation().equals(loc.getNormalizedLocation()))
				surrounding.add(hex);
			else if (hex.getLocation().getBorderingVertex(VertexDirection.SouthEast).getNormalizedLocation().equals(loc.getNormalizedLocation()))
				surrounding.add(hex);
			else if (hex.getLocation().getBorderingVertex(VertexDirection.East).getNormalizedLocation().equals(loc.getNormalizedLocation()))
				surrounding.add(hex);
		}
		
		
		return surrounding;
	}
	
	/**
	 * checks to see if the player can lay two roads using his roadBuilder
	 * @param index
	 * @param loc1
	 * @param loc2
	 * @return boolean
	 */
	public boolean canUseRoadBuilder(int index, EdgeLocation loc1, EdgeLocation loc2){
		EdgeValue road = new EdgeValue(index,loc1);
		EdgeValue road2 = new EdgeValue(index,loc2);
		HexLocation location = loc1.getHexLoc();
		HexLocation oppositeHex = getOppositeHex(location,loc1.getDir());
		
		//checking to make sure the first road is on land
		if(isLand(location)==false && isLand(oppositeHex)==false){
			return false;
		}
		
		//checking to make sure the second road is on land
		location = loc2.getHexLoc();
		oppositeHex = getOppositeHex(location,loc2.getDir());
		if(isLand(location)==false && isLand(oppositeHex)==false){
			return false;
		}
		
		//can lay first road
		boolean canLayFirstRoad = canLayRoad(road);
		if(canLayFirstRoad == false){
			return false;
		}
		
		//lay first road
		buildRoad(road);
		boolean canLaySecondRoad = canLayRoad(road2);
		deleteRoad(loc1); //delete the fist road we built
	
		return canLaySecondRoad;
	}

	
	public HashSet<EdgeValue> getAllPossibleRoadLocations(int playerIndex, boolean free) {
		HashSet<EdgeValue> locs = new HashSet<EdgeValue>();
		
		for (Hex hex : hexes){
			EdgeValue newLoc;
			EdgeLocation edgeLoc;
			
		    edgeLoc = new EdgeLocation(hex.getLocation(), EdgeDirection.North).getNormalizedLocation();
		    newLoc = new EdgeValue(playerIndex, edgeLoc);
		    
		    
		    if (free && this.canLayRoadFirstRounds(newLoc))
		    	locs.add(newLoc);
		    else if (!free && this.canLayRoad(newLoc))
		    	locs.add(newLoc);
		    
		    edgeLoc = new EdgeLocation(hex.getLocation(), EdgeDirection.NorthWest).getNormalizedLocation();
		    newLoc = new EdgeValue(playerIndex, edgeLoc);
		    if (free && this.canLayRoadFirstRounds(newLoc))
		    	locs.add(newLoc);
		    else if (!free && this.canLayRoad(newLoc))
		    	locs.add(newLoc);
		    
		    edgeLoc = new EdgeLocation(hex.getLocation(), EdgeDirection.NorthEast).getNormalizedLocation();
		    newLoc = new EdgeValue(playerIndex, edgeLoc);
		    if (free && this.canLayRoadFirstRounds(newLoc))
		    	locs.add(newLoc);
		    else if (!free && this.canLayRoad(newLoc))
		    	locs.add(newLoc);
		    
		}
		
		return locs;
	}
	
	
	public HashSet<VertexObject> getAllPossibleSettlementLocations(int playerIndex, boolean free) {
		HashSet<VertexObject> locs = new HashSet<VertexObject>();
		
		for (Hex hex : hexes){
			VertexLocation vertLoc = new VertexLocation(hex.getLocation(), VertexDirection.NorthWest);
			VertexObject newLoc = new VertexObject(playerIndex, vertLoc);
			
			
			if (free && this.canBuildSettlementFirstRound(newLoc))
				locs.add(newLoc);
			else if (!free && this.canBuildSettlement(newLoc))
				locs.add(newLoc);
			
			 vertLoc = new VertexLocation(hex.getLocation(), VertexDirection.NorthEast);
			 newLoc = new VertexObject(playerIndex, vertLoc);
			
			if (free && this.canBuildSettlementFirstRound(newLoc))
				locs.add(newLoc);
			else if (!free && this.canBuildSettlement(newLoc))
				locs.add(newLoc);
			
			 vertLoc = new VertexLocation(hex.getLocation(), VertexDirection.SouthWest);
			 newLoc = new VertexObject(playerIndex, vertLoc);
			
			if (free && this.canBuildSettlementFirstRound(newLoc))
				locs.add(newLoc);
			else if (!free && this.canBuildSettlement(newLoc))
				locs.add(newLoc);
			
			 vertLoc = new VertexLocation(hex.getLocation(), VertexDirection.SouthEast);
			 newLoc = new VertexObject(playerIndex, vertLoc);
			
			if (free && this.canBuildSettlementFirstRound(newLoc))
				locs.add(newLoc);
			else if (!free && this.canBuildSettlement(newLoc))
				locs.add(newLoc);
			
			 vertLoc = new VertexLocation(hex.getLocation(), VertexDirection.East);
			 newLoc = new VertexObject(playerIndex, vertLoc);
			
			if (free && this.canBuildSettlementFirstRound(newLoc))
				locs.add(newLoc);
			else if (!free && this.canBuildSettlement(newLoc))
				locs.add(newLoc);
			
			 vertLoc = new VertexLocation(hex.getLocation(), VertexDirection.West);
			 newLoc = new VertexObject(playerIndex, vertLoc);
			
			if (free && this.canBuildSettlementFirstRound(newLoc))
				locs.add(newLoc);
			else if (!free && this.canBuildSettlement(newLoc))
				locs.add(newLoc);
		}
		
		return locs;
	}
}





















