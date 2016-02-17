package client.points;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController, Observer {

	private IGameFinishedView finishedView;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView, Facade facade) {
		
		super(view);
		
		setFinishedView(finishedView);
		
		facade.addObserver(this);
		
		initFromModel();
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

	private void initFromModel() {	
		getPointsView().setPoints(0);
	
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}

