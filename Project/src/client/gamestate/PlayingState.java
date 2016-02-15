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
	public boolean placeRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.buildRoad(edgeLoc,true);
	}

	@Override
	public boolean placeSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildSettlement(vertLoc,true);
	}

	@Override
	public boolean placeCity(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildCity(vertLoc);
	}


}
