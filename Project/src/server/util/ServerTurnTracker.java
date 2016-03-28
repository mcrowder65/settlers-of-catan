package server.util;

import server.Game;
import server.ai.AIBase;
import shared.definitions.TurnTracker;

/**
 * Class to keep track of the turns in the game
 * @author Brennen
 *
 */
public class ServerTurnTracker extends TurnTracker{
	
	public ServerTurnTracker(int i, String string, int j, int k) {
		super(i, string, j, k);
	}
	

	public boolean handleAITurn(int gameId) {
		return handleAITurn(gameId, -1);
	}
	public boolean handleAITurn(int gameId, int aiIndex) {
	  	
		
	    ServerPlayer aiPlayer;
	    if (aiIndex > -1) //Index was specified, get for that player
	    	aiPlayer = Game.instance().getGameId(gameId).getServerPlayers()[aiIndex];
	    else 
	    	//Else get current turn player
	    	aiPlayer = Game.instance().getGameId(gameId).getServerPlayers()[this.currentTurn];
	  
	    
	    //Player is not an AI, ignore
	    if (aiPlayer.getPlayerID() >= 0)
	    	return false;
	    
	    AIBase ai;
		if (this.currentTurn != aiIndex && !status.equals("Discarding")) {
    		return false;
    	}
	    
	    
	    switch (status) {
	    case "Rolling":
	    	
		    ai = (AIBase)aiPlayer;
	    	ai.roll();
	    	if (status.equals("Robbing")) {
	    		ai.rob(false);
	    		ai.play();
	    	} else if (status.equals("Playing")) {
	    		ai.play();
	    	}
	    	break;
	    case "FirstRound":
	    case "SecondRound":
	    
	    	ai = (AIBase)aiPlayer;
	    	ai.playSetup();
	    	break;
	    case "Playing":
	    
	    	ai = (AIBase)aiPlayer;
	    	ai.play();
	    	break;
	    case "Robbing":
	    	
	    	ai = (AIBase)aiPlayer;
	    	ai.rob(false);
	    	ai.play();
	    	
	    	break;
	    case "Discarding":
	    	
			ai = (AIBase)aiPlayer;
			ai.discard();
	    	

	    	break;
	    
	    }
	    
	    return true;
	    
	}
}
