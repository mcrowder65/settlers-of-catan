package client.maritime;

import shared.definitions.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import client.data.GameManager;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;
import client.gamestate.PlayingState;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController,Observer {

	private IMaritimeTradeOverlay tradeOverlay;
	private GameState currState;
	private ResourceType[] types;
	private Facade facade;
	ResourceType give;
	ResourceType get;
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay,
			Facade facade) {

		super(tradeView);
		setTradeOverlay(tradeOverlay);
		this.currState = new IsNotTurnState(facade);
		facade.addObserver(this);
		this.facade = facade;
		
		types = new ResourceType[5];
		types[0] = ResourceType.SHEEP;
		types[1] = ResourceType.BRICK;
		types[2] = ResourceType.WOOD;
		types[3] = ResourceType.WHEAT;
		types[4] = ResourceType.ORE;
	}

	public IMaritimeTradeView getTradeView() {

		return (IMaritimeTradeView)super.getView();
	}

	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	

	@Override
	public void startTrade() {

		getTradeOverlay().showModal();
		ResourceType[] giveableResources = currState.getPlayerResources().getGiveableResources((ArrayList<Port>) currState.getPersonalPorts());
		if(giveableResources.length == 0)
			getTradeOverlay().setTradeEnabled(false);
		getTradeOverlay().hideGiveOptions();
		if(currState.getPlayerId() != -1)
			getTradeOverlay().showGiveOptions(giveableResources);
	}

	@Override
	public void makeTrade() {
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().hideGiveOptions();
		getTradeOverlay().closeModal();
		boolean tradeSuccess = facade.offerMaritimeTrade(ratio, give, get);
		if(!tradeSuccess)
			System.out.println("SOMEHOW THE MARITIME TRADE BROKE... THIS SHOULD NOT BE APPEARING!!!!!!");
	}

	@Override
	public void cancelTrade() {
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().hideGiveOptions();
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		getTradeOverlay().selectGetOption(resource, 1);
		get = resource;
	}
	private int ratio = 0;
	@Override
	public void setGiveResource(ResourceType resource) {
		ArrayList<Port> playerPorts = (ArrayList<Port>) currState.getPersonalPorts();
		getTradeOverlay().selectGiveOption(resource, 4);
		ratio = 4;
		for(int i = 0; i < playerPorts.size(); i++){
			if(playerPorts.get(i).getRatio() == 3){
				getTradeOverlay().selectGiveOption(resource, 3);
				ratio = 3;
			}
		}//CHECK IF THEY HAVE A 3 TO 1 RATIO FIRST
		for(Port i : playerPorts){
			if(i.getResource().equals(resource)){
				getTradeOverlay().selectGiveOption(resource, i.getRatio());
				ratio = i.getRatio();
			}
		}//NOW CHECK IF THEY HAVE A SPECIFIC RESOURCE SO THEN IT OVERWRITES THE 3 TO 1
		getTradeOverlay().showGetOptions(types);
		give = resource;
	}

	@Override
	public void unsetGetValue() {		
		getTradeOverlay().showGetOptions(types);
	}

	@Override
	public void unsetGiveValue() {
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().showGiveOptions(currState.getPlayerResources().getGiveableResources((ArrayList<Port>) currState.getPersonalPorts())); 
	}

	@Override
	public void update(Observable o, Object arg) {
		
		GameModel gameModel = (GameModel)arg;
		currState = currState.identifyState(gameModel.getTurnTracker());
		if (currState instanceof PlayingState)
			getTradeView().enableMaritimeTrade(true);
		else
			getTradeView().enableMaritimeTrade(false);
	}
	
}

