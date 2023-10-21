package socialmedia.analyzer.controllers.viewhandler;

/**
 * It will be used by JavaFX Application to help update the UI by controllers
 */
public interface UIUpdator {

	/**
	 * It will extract the file location and set the scene in the application.
	 * 
	 * @param view to set
	 */
	public abstract void setScene(View view);

}
