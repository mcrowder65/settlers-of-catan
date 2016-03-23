package client.domestic;

import shared.definitions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import client.data.GameManager;
import client.data.PlayerInfo;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;
import client.gamestate.PlayingState;
import client.misc.*;
import client.utils.DataUtils;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController, Observer {
	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private GameState currState;
	private HashMap<ResourceType, Integer> send;
	private HashMap<ResourceType, Integer> receive;
	private int bricks = 0;
	private int wood = 0;
	private int sheep = 0;
	private int wheat = 0;
	private int ore = 0;
	private int receiver = -1;
	private Facade facade;
	private boolean playersWereSet = false;
	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
			IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay, Facade facade) {
		
		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		this.currState = new IsNotTurnState(facade);
		initializeMaps();
		this.facade = facade;
		facade.addObserver(this);
		getTradeOverlay().setStateMessage("select the resources you want to trade");
		
	}
	public void findWhichResourcesIHave(){
		ResourceList resources = currState.getPlayerResources();
		bricks = resources.getBrick();
		wood = resources.getWood();
		sheep = resources.getSheep();
		ore = resources.getOre();
		wheat = resources.getWheat();
	}
	public void initializeMaps(){
		send = new HashMap<ResourceType, Integer>();
		receive = new HashMap<ResourceType, Integer>();
	}
	
	public IDomesticTradeView getTradeView() {

		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}
	
	@Override
	public void startTrade() {
		if(!playersWereSet){
			getTradeOverlay().setPlayers(currState.fetchModel().getOtherPlayers(currState.getPlayerId()));
			playersWereSet = true;
		}
		getTradeOverlay().setResourceSelectionEnabled(true);
		findWhichResourcesIHave();
		initializeMaps();
		
		getTradeOverlay().showModal();
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		if(send.containsKey(resource)) {
			send.put(resource, send.get(resource) - 1); //decrease by one
			getTradeOverlay().setResourceAmountChangeEnabled(resource, true, true); //technically a else statement
			if(send.get(resource) == 0)//don't let them receive less than 0
				getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false); 
			
		}
		else if(receive.containsKey(resource)) {  
			receive.put(resource, receive.get(resource) - 1); //decrease by one
			getTradeOverlay().setResourceAmountChangeEnabled(resource, true, true); //technically a else statement
			if(receive.get(resource) == 0) //don't let them receive less than 0
				getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
			
		}
		checkState(); //check if trade is ready to be sent
	}
	/**
	 * checks if the user can send the current resource 
	 * also sets the 
	 * @param resource
	 * @return boolean if they can send it
	 */
	public boolean canSendResource(ResourceType resource){
		boolean can = true;
		if(resource == ResourceType.BRICK){
			if(bricks == 0){
				can = false;
			}
		}
		else if(resource == ResourceType.ORE){
			if(ore == 0){
				can = false;
			}
		}
		else if(resource == ResourceType.WOOD){
			if(wood == 0){
				can = false;
			}
		}
		else if(resource == ResourceType.WHEAT){
			if(wheat == 0){
				can = false;
			}
		}
		else if(resource == ResourceType.SHEEP){
			if(sheep == 0){
				can = false;
			}
		} 
		getTradeOverlay().setResourceAmountChangeEnabled(resource, can, false);
		return can;
	}
	/**
	 * checks if the user has reached the amount total they can send of a certain resource.
	 * @param resource - certain resource
	 * @param amount - the amount they have sent already
	 * @return boolean if they can send
	 */
	public boolean canIncreaseResource(ResourceType resource, int amount){
		boolean can = true;
		if(resource == ResourceType.BRICK){
			if(bricks == amount){
				can = false;
			}
		}
		else if(resource == ResourceType.ORE){
			if(ore == amount){
				can = false;
			}
		}
		else if(resource == ResourceType.WOOD){
			if(wood == amount){
				can = false;
			}
		}
		else if(resource == ResourceType.WHEAT){
			if(wheat == amount){
				can = false;
			}
		}
		else if(resource == ResourceType.SHEEP){
			if(sheep == amount){
				can = false;
			}
		}
		getTradeOverlay().setResourceAmountChangeEnabled(resource, can, true);
		if(!can)
			getTradeOverlay().setResourceAmount(resource, Integer.toString(amount - 1));
		return can;
	}
	@Override
	public void increaseResourceAmount(ResourceType resource) {
		if(send.containsKey(resource)){ //increases by one then checks if they can increase the next time
			send.put(resource, send.get(resource) + 1);
			canIncreaseResource(resource, send.get(resource));
		}
		else if(receive.containsKey(resource)){ //you can always increase receive amount.
			receive.put(resource, receive.get(resource) + 1);
			getTradeOverlay().setResourceAmountChangeEnabled(resource, true, true);
		}
		checkState();
	}
	/**
	 * makes the trade offer in the form the server wants it.
	 * Uses the global variable HashMaps send and receive.
	 * @return the TradeOffer the server wants
	 */
	public TradeOffer constructTradeOffer(){
		int totalBricks = 0;
		int totalOre = 0;
		int totalSheep = 0;
		int totalWood = 0;
		int totalWheat = 0;
		for(ResourceType i : send.keySet()){
			if(i == ResourceType.BRICK)
				totalBricks = send.get(i);
			else if(i == ResourceType.ORE)
				totalOre = send.get(i);
			else if(i == ResourceType.SHEEP)
				totalSheep = send.get(i);
			else if(i == ResourceType.WOOD)
				totalWood = send.get(i);
			else if(i == ResourceType.WHEAT)
				totalWheat = send.get(i);
		}
		for(ResourceType i : receive.keySet()){
			if(i == ResourceType.BRICK)
				totalBricks = -receive.get(i);
			else if(i == ResourceType.ORE)
				totalOre = -receive.get(i);
			else if(i == ResourceType.SHEEP)
				totalSheep = -receive.get(i);
			else if(i == ResourceType.WOOD)
				totalWood = -receive.get(i);
			else if(i == ResourceType.WHEAT)
				totalWheat = -receive.get(i);
		}
		ResourceList resources = new ResourceList(totalBricks, totalOre, totalSheep, totalWheat, totalWood);
		
		return new TradeOffer(currState.getPlayerIndex(), receiver, resources);
	}
	@Override
	public void sendTradeOffer() {
		boolean accepted = currState.offerTrade(constructTradeOffer());
		getTradeOverlay().closeModal();
		getWaitOverlay().showModal();
		receiver = -1;
		clearTrade();
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		receiver = playerIndex;
		
		checkState();
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
		receive.put(resource, 0);
		getTradeOverlay().setResourceAmount(resource, "0");
		if(send.containsKey(resource))
			send.remove(resource);
		
		checkState();
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		if(canSendResource(resource)) send.put(resource, 0);
		getTradeOverlay().setResourceAmount(resource, "0");
		if(receive.containsKey(resource))
			receive.remove(resource);
		
		checkState();
	}

	@Override
	public void unsetResource(ResourceType resource) {
		if(receive.containsKey(resource)) receive.remove(resource);
		if(send.containsKey(resource)) send.remove(resource);
		checkState();
	}
	/**
	 * sets everything back to so nothing gets reused when trying to send a trade again
	 */
	public void clearTrade(){
		for(ResourceType i : send.keySet())
			getTradeOverlay().setResourceAmount(i, "0");
		for(ResourceType i : receive.keySet())
			getTradeOverlay().setResourceAmount(i, "0");
		initializeMaps();
		getTradeOverlay().reset();
	}
	@Override
	public void cancelTrade() {
	
		clearTrade();
		getTradeOverlay().closeModal();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		facade.acceptTrade(willAccept);
		getAcceptOverlay().reset();
		getAcceptOverlay().closeModal();
		
	}
	/**
	 * This checks the total sending resources and total receiving resources
	 * to see if the user is done choosing the resources they want in the trade.
	 * this function is primarily used in the checkState() function
	 * @return boolean if the they're done selecting resources.
	 */
	public boolean doneSelectingResources(){
		int sendAmount = 0;
		for(ResourceType rT : send.keySet()){
			sendAmount += send.get(rT);
		}
		
		int receiveAmount = 0;
		for(ResourceType rT : receive.keySet())
			receiveAmount += receive.get(rT);
		return sendAmount > 0 && receiveAmount > 0 ? true : false;
	}
	/**
	 * Checks if the receiver is set and if the resources are done being selected
	 * If so, it lets the user send the trade.
	 * Called in the checkState() function.
	 */
	public void checkIfReadyToTrade(){
		getTradeOverlay().setTradeEnabled(false);
		if(receiver != -1 && doneSelectingResources()){
			getTradeOverlay().setTradeEnabled(true);
			getTradeOverlay().setStateMessage("Trade!");
		}
	}
	/**
	 * Checks if the user can send the trade. This function is called pretty much everytime something about
	 * a trade is changed.
	 */
	public void checkState(){
		if(!doneSelectingResources())
			getTradeOverlay().setStateMessage("select the resources you want to trade");
		else if(doneSelectingResources() && receiver == -1)
			getTradeOverlay().setStateMessage("choose with whom you want to trade");
		checkIfReadyToTrade();
	}
	/**
	 * This function dissects a TradeOffer into the getAcceptOverlay() view so
	 * the user looking at it sees the correct offer
	 * @param offer a TradeOffer which the function dissects
	 */
	public void getResources(ResourceList offer){
		//BRICK, SHEEP, WHEAT, ORE, WOOD
		//if it's positive, then put it in the addGetResource
		//if it's negative, then put it in the addGiveResource
		if(offer.getBrick() > 0)
			getAcceptOverlay().addGetResource(ResourceType.BRICK, offer.getBrick());
		if(offer.getBrick() < 0)
			getAcceptOverlay().addGiveResource(ResourceType.BRICK, -1 * offer.getBrick());
		
		if(offer.getSheep() > 0)
			getAcceptOverlay().addGetResource(ResourceType.SHEEP, offer.getSheep());
		if(offer.getSheep() < 0)
			getAcceptOverlay().addGiveResource(ResourceType.SHEEP, -1 * offer.getSheep());
		
		if(offer.getWheat() > 0)
			getAcceptOverlay().addGetResource(ResourceType.WHEAT, offer.getWheat());
		if(offer.getWheat() < 0)
			getAcceptOverlay().addGiveResource(ResourceType.WHEAT, -1 * offer.getWheat());
		
		if(offer.getOre() > 0)
			getAcceptOverlay().addGetResource(ResourceType.ORE, offer.getOre());
		if(offer.getOre() < 0)
			getAcceptOverlay().addGiveResource(ResourceType.ORE, -1 * offer.getOre());
		
		if(offer.getWood() > 0)
			getAcceptOverlay().addGetResource(ResourceType.WOOD, offer.getWood());
		if(offer.getWood() < 0)
			getAcceptOverlay().addGiveResource(ResourceType.WOOD, -1 * offer.getWood());
	}
	@Override
	public void update(Observable o, Object arg) {
		if(!facade.isMapSet())
			return;
		GameModel gameModel = (GameModel)arg;
		currState = currState.identifyState(gameModel.getTurnTracker());
		
		if (currState instanceof PlayingState) //only let the person offer a trade if it's his/her turn
			getTradeView().enableDomesticTrade(true);
		else
			getTradeView().enableDomesticTrade(false);
		
		if(getWaitOverlay().isModalShowing()){ //   this disables the wait offer - once a trade is accepted or 
			if(gameModel.getTradeOffer() == null)// rejected then the poller gets a null trade offer, so then
				getWaitOverlay().closeModal();//    it knows to close it.
		}
		else if(!getAcceptOverlay().isModalShowing()){
			
			if(gameModel.getTradeOffer() != null){ 
				//if there's a trade offer, then show it if you're the receiver.
				if(gameModel.getTradeOffer().getReciever() == currState.getPlayerIndex()){
					if(!facade.canAcceptTrade(gameModel.getTradeOffer())){
						//if the user can't accept the trade offer, then disable the accept button
						showTradeModal(false, gameModel);
					}
					else if(currState.getPlayerIndex() == gameModel.getTradeOffer().getReciever()){
						showTradeModal(true, gameModel);
					}
				}
			}
		}
			
		
		
	}
	/**
	 * displays trade modal
	 * @param canAccept boolean that says whether or not the user can accept the trade being offered.
	 * @param gameModel just the game model to be passed.
	 */
	public void showTradeModal(boolean canAccept, GameModel gameModel){
		getAcceptOverlay().setAcceptEnabled(canAccept);
		TradeOffer tradeOffer = gameModel.getTradeOffer();
		ResourceList offer = tradeOffer.getOffer();
		getResources(offer);
		getAcceptOverlay().showModal();
	}
	/**
	 * Simple function to return the current maps... used for debugging.
	 * @return String of the maps.  "System.out.println(outputMaps());"
	 */
	public String outputMaps(){
		StringBuilder string = new StringBuilder("*************************************************");
		
		if(send.containsKey(ResourceType.BRICK))
			string.append("sending " + send.get(ResourceType.BRICK) + " bricks" + '\n');
		if(receive.containsKey(ResourceType.BRICK))
			string.append("receiving " + receive.get(ResourceType.BRICK) + " bricks" + '\n');
		
		if(send.containsKey(ResourceType.WHEAT))
			string.append("sending " + send.get(ResourceType.WHEAT) + " wheat" + '\n');
		if(receive.containsKey(ResourceType.WHEAT))
			string.append("receiving " + receive.get(ResourceType.WHEAT) + " wheat" + '\n');
		
		if(send.containsKey(ResourceType.WOOD))
			string.append("sending " + send.get(ResourceType.WOOD) + " wood" + '\n');
		if(receive.containsKey(ResourceType.WOOD))
			string.append("receiving " + receive.get(ResourceType.WOOD) + " wood" + '\n');
		
		if(send.containsKey(ResourceType.SHEEP))
			string.append("sending " + send.get(ResourceType.SHEEP) + " sheep" + '\n');
		if(receive.containsKey(ResourceType.SHEEP))
			string.append("receiving " + receive.get(ResourceType.SHEEP) + " sheep" + '\n');
		
		if(send.containsKey(ResourceType.ORE))
			string.append("sending " + send.get(ResourceType.ORE) + " ore" + '\n');
		if(receive.containsKey(ResourceType.ORE))
			string.append("receiving " + receive.get(ResourceType.ORE) + " ore" + '\n');
		
		return string.toString();
	}
}

