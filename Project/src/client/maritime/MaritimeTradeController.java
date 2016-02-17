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

	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay,
			Facade facade) {

		super(tradeView);

		setTradeOverlay(tradeOverlay);
		this.currState = new IsNotTurnState(facade);
		facade.addObserver(this);
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

	private void setState(String state){

		this.currState =  currState.identifyState(state);
	}

	@Override
	public void startTrade() {

		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {

		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade() {

		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {

	}

	@Override
	public void setGiveResource(ResourceType resource) {

	}

	@Override
	public void unsetGetValue() {

	}

	@Override
	public void unsetGiveValue() {

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}

