package usantatecla.mastermind.controllers;

import usantatecla.mastermind.models.Session;
import usantatecla.mastermind.models.StateValue;

public abstract class Controller {
	
	protected Session session;

	Controller(Session session) {
		this.session = session;
	}
	
	public int getWidth() {
		return this.session.getWidth();
	}
	
	public StateValue getStateValue() {
		return this.session.getValueState();
	}

}