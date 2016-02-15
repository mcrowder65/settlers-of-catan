package client.gamestate;

import client.controller.Facade;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class SecondRoundState extends GameState {
	
	public SecondRoundState(){
		
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
	public boolean placeRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.buildRoad(edgeLoc,true);
	}
	
	@Override
	public boolean placeSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildSettlement(vertLoc,true);
	}
}
