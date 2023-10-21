package socialmedia.analyzer.controllers;

import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import socialmedia.analyzer.controllers.viewhandler.UIUpdator;

/**
 * Abstract Controller with UIUpdator and few operations related to JavaFX.
 */
public abstract class Controller {

	// Attributes..
	private UIUpdator updator;

	/**
	 * Default Constructor
	 */
	public Controller() {

	}

	/**
	 * It will firstly called when the UI will be displayed.
	 */
	public abstract void update();

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

	/**
	 * It will generate the alert according to the type and the message.
	 * 
	 * @param type    of alert type
	 * @param message to be displayed
	 */
	public void alert(AlertType type, String message) {
		Alert alert = new Alert(type);
		alert.setTitle("Social Media Analyzer");
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * It will display the error message related to the database.
	 * 
	 * @param exception SQL exception
	 */
	public void sqlError(SQLException exception) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Social Media Analyzer");
		alert.setHeaderText("Database Error");
		alert.setContentText(exception.toString());
		alert.showAndWait();
	}

}
