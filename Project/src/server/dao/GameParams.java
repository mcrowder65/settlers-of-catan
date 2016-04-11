package server.dao;

import server.util.ServerGameModel;
/**
 * wrapper class to hold all the of the info needed when storing a game
 * @author Brennen
 *
 */
public class GameParams {

	private int id; //game id
	private ServerGameModel model; 
	private String title; //title of game
	 
	/**
	 * constuctor for GameParms
	 * @param id
	 * @param model
	 * @param title
	 */
	public GameParams(int id, ServerGameModel model, String title){
		this.id = id;
		this.model = model;
		this.title = title;
	}

	/**
	 * gets id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * gets gameModel
	 * @return ServerGameModel
	 */
	public ServerGameModel getModel() {
		return model;
	}

	/**
	 * gets the title of the game
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets the game id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * sets the model
	 * @param model
	 */
	public void setModel(ServerGameModel model) {
		this.model = model;
	}

	/**
	 * sets the title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
