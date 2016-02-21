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
	 * 
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

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		
		//System.out.println("in signIn");

		String userName = this.getLoginView().getLoginUsername();
		String password = this.getLoginView().getLoginPassword();
		boolean success = facade.login(userName,password);
		
		/*
		if(success == true){
			System.out.println("Worked");
		}
		else{
			System.out.println("Failed");
		}
		*/
		
		// If log in succeeded
		if(success == true){
			getLoginView().closeModal();
			loginAction.execute();
		} else {
			showLoginFail();
		}
	}

	@Override
	public void register() {
		
		if (!getLoginView().isRegisterFieldsValid()){
			showRegisterFail();
			return;	
		}
		
		String username = getLoginView().getRegisterUsername();
		String password = getLoginView().getRegisterPassword();
		
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
			showRegisterFail();
			return;
		}
	
	}
	
	private void showLoginFail() {
		messageView.setTitle("Error!");
		messageView.setMessage("Sign in failed.");
		messageView.showModal();
	}
	private void showRegisterFail() {
		messageView.setTitle("Warning!");
		messageView.setMessage("Invalid username or password.");
		messageView.showModal();
	}

}

