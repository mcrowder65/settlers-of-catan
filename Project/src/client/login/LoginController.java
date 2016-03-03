package client.login;

import client.base.*;
import client.communication.HTTPProxy;
import client.controller.Facade;
import client.misc.*;

import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


/**
 * Implementation for the login controller
 */
@SuppressWarnings("unused")
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	private Facade facade;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView, Facade facade) {

		super(view);
		
		this.messageView = messageView;
		this.facade = facade;
		
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	/**
	 * pops up the modal for the log in
	 */
	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		//gets the username and password from the view
		String userName = this.getLoginView().getLoginUsername();
		String password = this.getLoginView().getLoginPassword();
		boolean success = facade.login(userName,password);
		
		// If log in succeeded
		if(success == true){
			getLoginView().closeModal();
			loginAction.execute();
		} else {
			showLoginFail();
		}
	}
	
	/**
	 * registers the user on the server
	 */
	@Override
	public void register() {
		
		if (!getLoginView().isRegisterFieldsValid()){
			showRegisterFail();
			return;	
		}
		
		//gets the username and password from the view
		String username = getLoginView().getRegisterUsername();
		String password = getLoginView().getRegisterPassword();
		String encoded = null;
		try {
			encoded = URLEncoder.encode(username, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(!encoded.equals(password)){
			showRegisterFail();
			return;
		}
		if(!password.equals(getLoginView().getRegisterPasswordRepeat())){
			showRegisterFail();
			return;
		}
		//sends the username and password to the facade which then goes to the server
		boolean success = false;
	    success = facade.register(username,password);
		if(!success) {
			showRegisterFail();
			return;
		}
		success = facade.login(username,password);
		if(success){
			// If register succeeded
			getLoginView().closeModal();
			loginAction.execute();
		} else {
			//if register failed
			showRegisterFail();
			return;
		}
	
	}
	/**
	 * message to show if the login failed
	 */
	private void showLoginFail() {
		messageView.setTitle("Error!");
		messageView.setMessage("Sign in failed.");
		messageView.showModal();
	}
	/**
	 * message to show if the registering failed
	 */
	private void showRegisterFail() {
		messageView.setTitle("Warning!");
		messageView.setMessage("Invalid username or password.");
		messageView.showModal();
	}

}

