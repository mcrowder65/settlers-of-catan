package client.maritime;

import shared.definitions.*;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import client.data.GameManager;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;


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
		getTradeOverlay().hideGiveOptions();
		if(currState.getPlayerId() != -1)
			getTradeOverlay().showGiveOptions(currState.getPlayerResources().getGiveableResources());
	}

	@Override
	public void makeTrade() {
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().hideGiveOptions();
		getTradeOverlay().closeModal();
		boolean tradeSuccess = facade.offerMaritimeTrade(4, give, get);
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

	@Override
	public void setGiveResource(ResourceType resource) {
		getTradeOverlay().selectGiveOption(resource, 4);
		
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
		getTradeOverlay().showGiveOptions(currState.getPlayerResources().getGiveableResources()); 
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
	}
	
}

