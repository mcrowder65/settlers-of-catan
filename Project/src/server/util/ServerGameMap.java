package server.util;

import shared.definitions.GameMap;
import shared.definitions.Hex;
import shared.definitions.Port;
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
	 * checks to see if you can build a road during the first two rounds
	 * @param index
	 * @param loc
	 * @return
	 */
	public boolean canBuildRoadSetup(int index, EdgeLocation loc){
		EdgeValue road = new EdgeValue(index,loc);
		HexLocation location = loc.getHexLoc();
		HexLocation oppositeHex = getOppositeHex(location,loc.getDir());
		//checks to see if you are trying to build a road on water
		if(isLand(location) == false || isLand(oppositeHex) == false){
			return false;
		}
		//checks to see if there is already a road there
		if(hasRoadAllPlayers(loc) == true){
			return false;
		}
		return canLayRoadFirstRounds(road);
	}
	
}
