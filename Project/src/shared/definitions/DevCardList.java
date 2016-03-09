package shared.definitions;
/**
 * Class to wrap the different development cards
 * It holds the different types of development cards and how many there are 
 * @author Brennen
 *
 */
public class DevCardList {

	/**
	 * number of monopoly cards
	 */
	private int monopoly;
	/**
	 * Number of Monument cards
	 */
	private int monument;
	/**
	 * Number of roadBuilding dev cards
	 */
	private int roadBuilding;
	/**
	 * Number of soldier cards
	 */
	private int soldier;
	/**
	 * Number of yearOfPlenty Cards
	 */
	private int yearOfPlenty;
	
	/**
	 * Constructor for DevCardList
	 * Sets monopoly,monument,roadBuilding,soldier,yearOfPlenty attributes
	 * @param monopoly
	 * @param monument
	 * @param roadBuilding
	 * @param soldier
	 * @param yearOfPlenty
	 */
	public DevCardList(int monopoly, int monument, int roadBuilding,
			int soldier, int yearOfPlenty) throws IllegalArgumentException {
		
		this.monopoly = monopoly;
		this.monument = monument;
		this.roadBuilding = roadBuilding;
		this.soldier = soldier;
		this.yearOfPlenty = yearOfPlenty;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + monopoly;
		result = prime * result + monument;
		result = prime * result + roadBuilding;
		result = prime * result + soldier;
		result = prime * result + yearOfPlenty;
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
		DevCardList other = (DevCardList) obj;
		if (monopoly != other.monopoly)
			return false;
		if (monument != other.monument)
			return false;
		if (roadBuilding != other.roadBuilding)
			return false;
		if (soldier != other.soldier)
			return false;
		if (yearOfPlenty != other.yearOfPlenty)
			return false;
		return true;
	}
	public DevCardList() {
	}

	/**
	 * retrieves number of monopoly cards
	 * @return monopoly
	 */
	public int getMonopoly() {
		return monopoly;
	}

	/**
	 * retrieves number of monument cards
	 * @return monument
	 */
	public int getMonument() {
		return monument;
	}

	/**
	 * retrieves number of roadBuilding cards
	 * @return roadBuilding
	 */
	public int getRoadBuilding() {
		return roadBuilding;
	}

	/**
	 * retrieves number of soldier cards
	 * @return soldier
	 */
	public int getSoldier() {
		return soldier;
	}

	/**
	 * retrieves number of yearOfPlenty cards
	 * @return yearOfPlenty
	 */
	public int getYearOfPlenty() {
		return yearOfPlenty;
	}

	/**
	 * sets the number of monopoly Cards
	 * @param monopoly
	 */
	public void setMonopoly(int monopoly) throws IllegalArgumentException  {
		this.monopoly = monopoly;
	}
	
	/**
	 * Sets the num of Monument Cards
	 * @param monument
	 */
	public void setMonument(int monument) throws IllegalArgumentException  {
		this.monument = monument;
	}

	/**
	 * Sets the number of roadBuilding Cards
	 * @param roadBuilding
	 */
	public void setRoadBuilding(int roadBuilding) throws IllegalArgumentException  {
		this.roadBuilding = roadBuilding;
	}

	/**
	 * Sets the number of solider cards
	 * @param soldier
	 */
	public void setSoldier(int soldier) throws IllegalArgumentException  {
		this.soldier = soldier;
	}

	/**
	 * sets the number of YearOfPlenty Cards
	 * @param yearOfPlenty
	 */
	public void setYearOfPlenty(int yearOfPlenty) throws IllegalArgumentException  {
		this.yearOfPlenty = yearOfPlenty;
	}
	
	/**
	 * Determines if the DevCard List is empty
	 * @return True if the DevCard List is empty
	 */
	public boolean isEmpty() {
		if(monopoly > 0) return false;
		else if(monument > 0) return false;
		else if(roadBuilding > 0) return false;
		else if(soldier > 0) return false;
		else if(yearOfPlenty > 0) return false;
		
		return true;
	}
	
}
