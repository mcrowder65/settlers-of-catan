package shared.definitions;

import shared.locations.*;
/**
 * Class to deal with trading
 * @author Brennen
 *
 */
public class TradeOffer {

	private int sender;
	private int receiver; //player index that is recieving the trade
	private ResourceList offer;

	/**
	 * Constructor for TradeOffer
	 * Sets all attributes 
	 * @param sender
	 * The index of the sender
	 * @param receiver
	 * The index of the receiver
	 * @param offer
	 * The resources to trade
	 * @throws IllegalArgumentException Throws if the arguments are invalid (sender or receiver < 0 || > 3, offer is null)
	 * 
	 */
	public TradeOffer(int sender, int receiver, ResourceList offer) throws IllegalArgumentException {
		
		this.sender = sender;
		this.receiver = receiver;
		this.offer = offer;
	}
	
	public TradeOffer(){
		
	}

	public void setSender(int sender){
		this.sender = sender;
	}
	
	/**
	 * retrieves the player index of the sender of the trade
	 * @return
	 */
	public int getSender() {
		return sender;
	}

	public void setReceiver(int receiver){
		this.receiver = receiver;
	}

	/**
	 * retrieves the player index of the receiver of the trade
	 * @return receiver
	 */
	public int getReciever() {
		return receiver;
	}

	public void setOffer(ResourceList offer){
		this.offer = offer;
	}
	
	/**
	 * retrieves the offer which is in the form of a ResourceList
	 * @return offer
	 */
	public ResourceList getOffer() {
		return offer;
	}

	@Override
	public String toString() {
		return "TradeOffer [sender=" + sender + ", receiver=" + receiver + ", offer=" + offer + "]";
	}

	





}
