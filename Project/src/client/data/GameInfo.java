
package client.data;

import java.util.*;

import shared.definitions.CatanColor;

/**
 * Used to pass game information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique game ID</li>
 * <li>Title: Game title (non-empty string)</li>
 * <li>Players: List of players who have joined the game (can be empty)</li>
 * </ul>
 * 
 */
public class GameInfo
{
	private int id;
	private String title;
	private List<PlayerInfo> players;
	
	public GameInfo()
	{
		setId(-1);
		setTitle("");
		players = new ArrayList<PlayerInfo>();
	}
	public GameInfo(int id, String title, List<PlayerInfo> players){
		this.id = id;
		this.title = title;
		this.players = players;
	}
	public void setPlayerIndex(int index){
		PlayerInfo player = players.get(index);
		player.setPlayerIndex(index);
		players.set(index, player);
	}
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void addPlayer(PlayerInfo newPlayer)
	{
		players.add(newPlayer);
	}
	/**
	 * this will set a player - used for joining a game your player is already in.
	 * @param index of player
	 * @param color new CatanColor
	 */
	public void setPlayer(int index, int id, CatanColor color){
		String name = players.get(index).getName();
		players.set(index, new PlayerInfo(id, name, color));
		
	}
	public PlayerInfo getPlayer(int id){
		return players.get(id);
	}
	/**
	 * checks in the players array to see if the player trying to join is already in there
	 * @param name of player
	 * @return boolean
	 */
	public boolean hasPlayer(String name){
		for(PlayerInfo i : players){
			if(i.getName().equals(name))
				return true;
		}
		return false;
	}
	public List<PlayerInfo> getPlayers()
	{
		return Collections.unmodifiableList(players);
	}
}