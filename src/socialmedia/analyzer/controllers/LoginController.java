package socialmedia.analyzer.controllers;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import socialmedia.analyzer.controllers.viewhandler.View;
import socialmedia.analyzer.database.SQLiteDatabase;
import socialmedia.analyzer.models.User;

/**
 * Controller for the login.fxml file
 */
public class LoginController extends Controller {

	// Attributes.
	@FXML
	private TextField loginUsername, registerFirstname, registerLastname, registerUsername;
	@FXML
	private PasswordField loginPassword, registerPassword;

	@Override
	public void update() {
		// Not Required.
	}

	/**
	 * When user enter the details, it will validate the data and check if there is
	 * some user exists in the database with the given username and password, then
	 * it will move to the dashboard UI.
	 * 
	 * @param event for action
	 */
	@FXML
	private void login(ActionEvent event) {

		String username = loginUsername.getText();
		String password = loginPassword.getText();
		if (username.isEmpty() || password.isEmpty()) {
			alert(AlertType.ERROR, "No any empty fields are allowed");
		} else {
			try {
				if (SQLiteDatabase.getInstance().login(username, password)) {
					loginUsername.setText("");
					loginPassword.setText("");
					getUpdator().setScene(View.DASHBOARD);
				} else {
					alert(AlertType.ERROR, "Username or password is wrong!");
				}
			} catch (SQLException e) {
				sqlError(e);
			}
		}

	}

	/**
	 * It will take all necessary details from the user, t will validate complete
	 * data and if username does not already exists, then it will register a new
	 * user into the database.
	 * 
	 * @param event for action.
	 */
	@FXML
	private void register(ActionEvent event) {

		String first = registerFirstname.getText();
		String last = registerLastname.getText();
		String username = registerUsername.getText();
		String password = registerPassword.getText();
		Boolean vip  = false;
		if (first.isEmpty() || last.isEmpty() || username.isEmpty() || password.isEmpty()) {
			alert(AlertType.ERROR, "No any empty fields are allowed");
		} else {
			try {
				if (SQLiteDatabase.getInstance().hasUsername(username)) {
					alert(AlertType.ERROR, "Username already exists!");
				} else {
					SQLiteDatabase.getInstance().addUser(new User(first, last, username, password));
					registerFirstname.setText("");
					registerLastname.setText("");
					registerUsername.setText("");
					registerPassword.setText("");
					alert(AlertType.INFORMATION, "User is registered!");
				}
			} catch (SQLException e) {
				System.out.println(e);
				sqlError(e);
			}
		}

	}

}
