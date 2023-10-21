package socialmedia.analyzer.controllers;

import socialmedia.analyzer.controllers.viewhandler.UIUpdator;

public abstract class Controller {

	private UIUpdator updator;
	
	public Controller() {
		
	}

	/**
	 * Getter Methods for updator
	 * 
	 * @return the updator field
	 */
	public UIUpdator getUpdator() {
		return updator;
	}

	/**
	 * Update the value of updator
	 *
	 * @param updator updated value
	 */
	public void setUpdator(UIUpdator updator) {
		this.updator = updator;
	}

}
