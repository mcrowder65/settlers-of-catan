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
	private boolean playersWereSet = false;
	private int receiver = -1;
	private Facade facade;
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
		this.currState = new IsNotTurnState(facade); //TODO how should we handle this..?
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
		if(send.containsKey(resource)){
			send.put(resource, send.get(resource) - 1);
			getTradeOverlay().setResourceAmountChangeEnabled(resource, true, true); //technically a else statement
			if(send.get(resource) == 0)
				getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
			
		}
		else if(receive.containsKey(resource)){
			receive.put(resource, receive.get(resource) - 1);
			getTradeOverlay().setResourceAmountChangeEnabled(resource, true, true); //technically a else statement
			if(receive.get(resource) == 0)
				getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
			
		}
		checkState();
	}
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
		if(send.containsKey(resource)){
			send.put(resource, send.get(resource) + 1);
			canIncreaseResource(resource, send.get(resource));
		}
		else if(receive.containsKey(resource)){
			receive.put(resource, receive.get(resource) + 1);
			getTradeOverlay().setResourceAmountChangeEnabled(resource, true, true);
		}
		checkState();
	}
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
		//TODO sometimes the send trade shows upa  little earlier than expected.
		boolean accepted = currState.offerTrade(constructTradeOffer());
		getTradeOverlay().closeModal();
		getWaitOverlay().showModal(); //TODO configure this.
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
	public void checkIfReadyToTrade(){
		getTradeOverlay().setTradeEnabled(false);
		if(receiver != -1 && doneSelectingResources()){
			getTradeOverlay().setTradeEnabled(true);
			getTradeOverlay().setStateMessage("Trade!");
		}
	}
	public void checkState(){
		if(!doneSelectingResources())
			getTradeOverlay().setStateMessage("select the resources you want to trade");
		else if(doneSelectingResources() && receiver == -1)
			getTradeOverlay().setStateMessage("choose with whom you want to trade");
		checkIfReadyToTrade();
	}
	public void getResources(ResourceList offer){
		//BRICK, SHEEP, WHEAT, ORE, WOOD
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
		GameModel gameModel = (GameModel)arg;
		currState = currState.identifyState(gameModel.getTurnTracker());
		
		if (currState instanceof PlayingState)
			getTradeView().enableDomesticTrade(true);
		else
			getTradeView().enableDomesticTrade(false);
		
		if(!getAcceptOverlay().isModalShowing()){
			if(gameModel.getTradeOffer() != null){
				System.out.println(gameModel.getTradeOffer().toString());	
				if(!facade.canAcceptTrade(gameModel.getTradeOffer())){
					getAcceptOverlay().setAcceptEnabled(false);
					TradeOffer tradeOffer = gameModel.getTradeOffer();
					ResourceList offer = tradeOffer.getOffer();
					getResources(offer);
					getAcceptOverlay().showModal();
					return; 
				}
				if(currState.getPlayerIndex() == gameModel.getTradeOffer().getReciever()){
					TradeOffer tradeOffer = gameModel.getTradeOffer();
					ResourceList offer = tradeOffer.getOffer();
					getResources(offer);
					getAcceptOverlay().showModal();
				}
			}
		}
			
		if(getWaitOverlay().isModalShowing()){
			if(gameModel.getTradeOffer() == null)
				getWaitOverlay().closeModal();
		}
		
	}
	public void outputMaps(){
		System.out.println("*************************************************");
		if(send.containsKey(ResourceType.BRICK))
			System.out.println("sending " + send.get(ResourceType.BRICK) + " bricks");
		if(receive.containsKey(ResourceType.BRICK))
			System.out.println("receiving " + receive.get(ResourceType.BRICK) + " bricks");
		
		if(send.containsKey(ResourceType.WHEAT))
			System.out.println("sending " + send.get(ResourceType.WHEAT) + " wheat");
		if(receive.containsKey(ResourceType.WHEAT))
			System.out.println("receiving " + receive.get(ResourceType.WHEAT) + " wheat");
		
		if(send.containsKey(ResourceType.WOOD))
			System.out.println("sending " + send.get(ResourceType.WOOD) + " wood");
		if(receive.containsKey(ResourceType.WOOD))
			System.out.println("receiving " + receive.get(ResourceType.WOOD) + " wood");
		
		if(send.containsKey(ResourceType.SHEEP))
			System.out.println("sending " + send.get(ResourceType.SHEEP) + " sheep");
		if(receive.containsKey(ResourceType.SHEEP))
			System.out.println("receiving " + receive.get(ResourceType.SHEEP) + " sheep");
		
		if(send.containsKey(ResourceType.ORE))
			System.out.println("sending " + send.get(ResourceType.ORE) + " ore");
		if(receive.containsKey(ResourceType.ORE))
			System.out.println("receiving " + receive.get(ResourceType.ORE) + " ore");
	}
}

