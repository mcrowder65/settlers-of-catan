package client.gamestate;

import client.controller.Facade;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class PlayingState extends GameState {

	public PlayingState(){
		
	}
	
	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{

		return facade.canBuildRoad(edgeLoc);
	}
	
	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) throws IllegalArgumentException{

		return facade.canBuildSettlement(vertLoc);
	}

	@Override
	public boolean canPlaceCity(VertexLocation vertLoc) throws IllegalArgumentException{

		return facade.canBuildCity(vertLoc);
	}

	@Override
	public boolean canPlaceRobber(HexLocation hexLoc) throws IllegalArgumentException{

		return facade.canPlaceRobber(hexLoc);
	}

	@Override
	public void placeRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		facade.buildRoad(edgeLoc,true);
	}

	@Override
	public void placeSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		facade.buildSettlement(vertLoc,true);
	}

	@Override
	public void placeCity(VertexLocation vertLoc) throws IllegalArgumentException{
		facade.buildCity(vertLoc);
	}


}
