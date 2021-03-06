package client.data;

import shared.definitions.Player;

/**
 * Used to pass player information into the rob view<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique player ID</li>
 * <li>PlayerIndex: Player's order in the game [0-3]</li>
 * <li>Name: Player's name (non-empty string)</li>
 * <li>Color: Player's color (cannot be null)</li>
 *
 * </ul>
 * 
 */
public class RobPlayerInfo extends PlayerInfo
{
	
	private int numCards;
	
	public RobPlayerInfo()
	{
		super();
	}
	
	public RobPlayerInfo(PlayerInfo info) {
		super();
		this.setColor(info.getColor());
		this.setId(info.getId());
		this.setName(info.getName());
		this.setPlayerIndex(info.getPlayerIndex());
	}
	public RobPlayerInfo(Player player) {
		super(player);
		setNumCards(player.getNumOfCards());
	}
	
	public int getNumCards()
	{
		return numCards;
	}
	
	public void setNumCards(int numCards)
	{
		this.numCards = numCards;
	}
	
}

