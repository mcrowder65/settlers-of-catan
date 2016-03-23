package client.points;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import shared.definitions.GameModel;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController, Observer {

	private IGameFinishedView finishedView;
	private Facade facade;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView, Facade facade) {
		
		super(view);
		
		setFinishedView(finishedView);
		
		this.facade = facade;
		initFromModel(2);
	}
	
	public IPointsView getPointsView() {
		
		return (IPointsView)super.getView();
	}
	
	public IGameFinishedView getFinishedView() {
		return finishedView;
	}
	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

	private void initFromModel(int points) {	
		getPointsView().setPoints(points);
	
	}
	
	/**
	 * This method is in charge of finishing the game. Once the server declares
	 * there's a winner, this method displays the winner Modal, stops the poller, and
	 * takes the player back to the lobby.
	 * @param model
	 */
	private void finishGame(GameModel model) {
		int winner = model.getWinner();
		String name = model.getPlayers()[winner].getName();

		boolean isLocalPlayer = false;
		if(model.getPlayers()[winner].getPlayerID() == facade.getPlayerId()) isLocalPlayer = true;
		
		finishedView.setWinner(name, isLocalPlayer);
		if(!finishedView.isModalShowing()){
			finishedView.showModal();
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		
		//This calls the method when the server has declared that there's a winner.
		int winner = model.getWinner();
		if(winner != -1) {
			finishGame(model);
		}
		
		//This calls the method to keep the victory points display updated. 
		int playerId = facade.getPlayerId();
		int points = model.getLocalPlayer(playerId).getVictoryPoints();
		initFromModel(points);
		
	}
	

	public void enterGame() {
		this.facade.addObserver(this);
		
	}
	public void leaveGame() {
		this.facade.deleteObserver(this);
		
	}
	
}

