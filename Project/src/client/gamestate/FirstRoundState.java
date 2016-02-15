package client.gamestate;

import client.controller.Facade;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class FirstRoundState extends GameState {
	
	public FirstRoundState(){
		
	}
	
	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.canBuildRoad(edgeLoc);
	}

	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.canBuildSettlement(vertLoc);
	}
}
