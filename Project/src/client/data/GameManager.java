package client.data;


import client.communication.IProxy;
import client.communication.Poller;
import shared.definitions.GameModel;

/**
 * Serves as an wrapper for the GameModel class
 * and integrates it with the Server API.
 */
public class GameManager {

	/**
	 * The model of the current game.
	 */
	private GameModel model;
	/**
	 * The poller created by the GameManager
	 * that will be used to periodically
	 * check for the newest version
	 * as defined by the proxy.
	 */
	private Poller poller;
	/**
	 * The Proxy that will be periodically polled
	 * for the latest version of the model.
	 */
	private IProxy proxy;
	
	/**
	 * Creates a new instance of the GameManager class.
	 */
	public GameManager(IProxy proxy, int pollingInterval) 
	{
		setProxy(proxy);
		poller = new Poller(this, pollingInterval);
		
	}
	
	public GameModel getModel() { return model;}
	public IProxy getProxy() { return proxy;}
	
	public void setProxy(IProxy proxy) throws IllegalArgumentException {
		if (proxy == null) throw new IllegalArgumentException("Proxy cannot be null.");
		this.proxy = proxy;
	
	}
	/**
	 * Gets the latest version of the GameModel and updates
	 * the local copy of the GameModel.
	 * @return
	 * Returns true if update was successful.
	 */
	public boolean updateModel()  {
		proxy.getModel();
		return false;
	}
	
}
