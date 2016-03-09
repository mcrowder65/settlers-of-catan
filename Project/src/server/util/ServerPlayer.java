package server.util;

import client.utils.Translator;
import shared.definitions.CatanColor;
import shared.definitions.DevCardList;
import shared.definitions.Player;
import shared.definitions.ResourceList;
import shared.definitions.TradeOffer;
import shared.locations.*;
/**
 * class that holds all the attributes of a player
 * @author Brennen
 *
 */
public class ServerPlayer extends Player {

	/**
	 * constructor for ServerPlayer
	 * @param name
	 * @param color
	 * @param playerID
	 * @param playerIndex
	 * @throws IllegalArgumentException
	 */
	public ServerPlayer(String name, CatanColor color, int playerID, int playerIndex) throws IllegalArgumentException {
		super(name,color, playerID, playerIndex);
	
	}
	
}
