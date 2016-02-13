package client.gamestate;

import client.controller.Facade;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class SecondRoundState extends GameState {
	
	public SecondRoundState(){
		
	}
	
	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc, Facade facade) throws IllegalArgumentException{
		return facade.canBuildRoad(edgeLoc);
	}

	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc, Facade facade) throws IllegalArgumentException{
		return facade.canBuildSettlement(vertLoc);
	}
}
