package client.join;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import client.data.GameManager;
import client.data.PlayerInfo;
import shared.definitions.AIType;
import shared.definitions.GameModel;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, Observer {

	private Facade facade;
	private PlayerInfo[] players = new PlayerInfo[4];
	private IAction allPlayersEnteredAction;
	private int localCount = 0;
	
	public IAction getAllPlayersEnteredAction() {
		return allPlayersEnteredAction;
	}

	public void setAllPlayersEnteredAction(IAction allPlayersEnteredAction) {
		this.allPlayersEnteredAction = allPlayersEnteredAction;
	}

	public PlayerWaitingController(IPlayerWaitingView view, Facade facade) {

		super(view);
		this.facade = facade;
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {

		facade.addObserver(this);
		getView().showModal();
		List<AIType> aiTypes = facade.listAI();
		String[] typeStrings = new String[aiTypes.size()];
		for (int n = 0; n < aiTypes.size(); n++) {
			typeStrings[n] = aiTypes.get(n).toString();
		}
		getView().setAIChoices(typeStrings);
		
	}

	@Override
	public void addAI() {

		boolean success = facade.addAI(getView().getSelectedAI());
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		GameModel model = (GameModel)arg1;
		PlayerInfo[] playersLite = model.getPlayersLite();
		
		if (playersLite.length != localCount) {
			localCount = playersLite.length;
			getView().closeModal();
			getView().setPlayers(playersLite);
			getView().showModal();
		}
		
		
		if (playersLite.length == 4) {
			facade.deleteObserver(this);
			getView().closeModal();
			if (allPlayersEnteredAction != null)
				allPlayersEnteredAction.execute();
			else 
				System.out.println("WARNING! allPlayersEnteredAction was not set");
		}
		
	}

}

