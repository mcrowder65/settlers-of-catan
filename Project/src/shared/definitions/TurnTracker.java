package shared.definitions;
/**
 * Class to keep track of the turns in the game
 * @author Brennen
 *
 */
public class TurnTracker {

	
	/**
	 * Who's turn it is (0-3)
	 */
	protected int currentTurn; 
	/**
	 * ['Rolling' or 'Robbing' or 'Playing' or 'Discarding' or 'FirstRound' or 'SecondRound']: What's happening now
	 */
	protected String status; 
	/**
	 * The index of who has the longest road, -1 if no one has it
	 */
	protected int longestRoad; 
	/**
	 * //The index of who has the longest army, -1 if no one has it
	 */
	protected int largestArmy; 
	
	/**
	 * Constructor for TurnTracker
	 * Sets all attributes
	 * 
	 * @param currentTurn
	 * @param status
	 * @param longestRoad
	 * @param largestArmy
	 * @throws IllegalArgumentException
	 * 
	 */
	public TurnTracker(int currentTurn, String status, int longestRoad,
			int largestArmy)  throws IllegalArgumentException {
		super();
		this.currentTurn = currentTurn;
		this.status = status;
		this.longestRoad = longestRoad;
		this.largestArmy = largestArmy;
	}
	
	public TurnTracker(){
		
	}

	/**
	 * retrieves whos turn it is
	 * @return currentTurn
	 */
	public int getCurrentTurn() {
		return currentTurn;
	}

	/**
	 * retrieves the satus of what the player is doing
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * retrieves who has the longest road
	 * @return longestRoad
	 */
	public int getLongestRoad() {
		return longestRoad;
	}

	/**
	 * retrieves who has the longest army
	 * @return largestArmy
	 */
	public int getlargestArmy() {
		return largestArmy;
	}

	/**
	 * Sets the player whose current turn it is
	 * @param currentTurn
	 * @throws IllegalArgumentException
	 * throws if the currentTurn is invalid
	 */
	public void setCurrentTurn(int currentTurn)  throws IllegalArgumentException {
		this.currentTurn = currentTurn;
	}

	/**
	 * sets the status of the player
	 * @param status
	 * @throws IllegalArgumentException
	 * Throws if the status is invalid or unrecognized
	 */
	public void setStatus(String status) throws IllegalArgumentException {
		this.status = status;
	}

	/**
	 * sets the player who has the longestRoad
	 * @param longestRoad
	 * @throws IllegalArgumentException
	 * Throws if the longestRoad value is invalid
	 */
	public void setLongestRoad(int longestRoad) throws IllegalArgumentException {
		this.longestRoad = longestRoad;
	}

	/**
	 * Sets the player who has the largestArmy
	 * @param largestArmy
	 * @throws IllegalArgumentException
	 * Throws if the largestArmy value is invalid
	 */
	public void setLargestArmy(int largestArmy) throws IllegalArgumentException  {
		this.largestArmy = largestArmy;
	}
	/**
	 * advances turn to next player
	 */
	public void advanceTurn(){
		//Advances the Turn to the next player, if the current player's turn is the
		//last one in the cycle, it gets reset to the 1st player
			this.currentTurn++;
			
		if(this.currentTurn == 4){
			this.currentTurn = 0;
		}
		
	}
	
	public void decreaseTurn(){
			this.currentTurn--;
			
		if(this.currentTurn == -1){
			this.currentTurn = 3;
		}
		
	}
	
	/**
	 * Determines if the playerIndex is the current turn
	 * @param playerIndex
	 * @return
	 */
	public boolean isMyTurn(int playerIndex){
		if(this.currentTurn == playerIndex){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentTurn;
		result = prime * result + largestArmy;
		result = prime * result + longestRoad;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurnTracker other = (TurnTracker) obj;
		if (currentTurn != other.currentTurn)
			return false;
		if (largestArmy != other.largestArmy)
			return false;
		if (longestRoad != other.longestRoad)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
	
	
	
}
