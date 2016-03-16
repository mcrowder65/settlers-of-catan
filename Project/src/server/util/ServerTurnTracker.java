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
	
	public void updateStatus(String status, int gameId) {
	    this.status = status;
	    
	    ServerPlayer turnPlayer = Game.instance().getGameId(gameId).getServerPlayers()[this.currentTurn];
	    
	    //Player is not an AI, ignore
	    if (turnPlayer.getPlayerID() >= 0)
	    	return;
	    
	    AIBase ai;
	    
	    
	    
	    switch (status) {
	    case "Rolling":
	    	 //Player is not an AI, ignore
		    if (turnPlayer.getPlayerID() >= 0)
		    	return;
		    ai = (AIBase)turnPlayer;
	    	ai.roll();
	    	break;
	    case "FirstRound":
	    case "SecondRound":
	    	 //Player is not an AI, ignore
		    if (turnPlayer.getPlayerID() >= 0)
		    	return;
		    
	    	ai = (AIBase)turnPlayer;
	    	ai.playSetup();
	    	break;
	    case "Playing":
	    	 //Player is not an AI, ignore
		    if (turnPlayer.getPlayerID() >= 0)
		    	return;
		    
	    	ai = (AIBase)turnPlayer;
	    	ai.play();
	    	break;
	    case "Robbing":
	    	 //Player is not an AI, ignore
		    if (turnPlayer.getPlayerID() >= 0)
		    	return;
		    
	    	ai = (AIBase)turnPlayer;
	    	ai.rob(false);
	    	break;
	    case "Discarding":
	    	for (ServerPlayer player : Game.instance().getGameId(gameId).getServerPlayers()) {
	    		 //Player is not an AI, ignore
			    if (player.getPlayerID() >= 0)
			    	continue;
	    		
			    ai = (AIBase)player;
			    ai.discard();
	    	}

	    	break;
	    
	    }
	}
}
