package socialmedia.analyzer.controllers;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import socialmedia.analyzer.controllers.viewhandler.View;
import socialmedia.analyzer.database.SQLiteDatabase;
import socialmedia.analyzer.models.User;

/**
 * Controller for profile.fxml
 *
 */
public class ProfileController extends Controller {

	// Attributes..
	@FXML
	private TextField profileFirstname, profileLastname, profileUsername, profilePassword;
	private User currentUser;

	/**
	 * Update the text fields with the current logged in user details.
	 */
	@Override
	public void update() {
		try {
			currentUser = SQLiteDatabase.getInstance().getLoggedInUser();
			profileFirstname.setText(currentUser.getFirstName());
			profileLastname.setText(currentUser.getLastName());
			profileUsername.setText(currentUser.getUsername());
			profilePassword.setText(currentUser.getPassword());
		} catch (SQLException e) {
			super.sqlError(e);
		}
	}

	/**
	 * It will take the user details from the fields, it will validate the data and
	 * check if username does not already exists, and it will update the details of
	 * the user in the database.
	 * 
	 * @param event for action
	 */
	@FXML
	public void updateProfile(ActionEvent event) {
		String first = profileFirstname.getText();
		String last = profileLastname.getText();
		String username = profileUsername.getText();
		String password = profilePassword.getText();
		if (first.isEmpty() || last.isEmpty() || username.isEmpty() || password.isEmpty()) {
			alert(AlertType.ERROR, "No any empty fields are allowed");
		} else {
			try {
				if (!username.equals(currentUser.getUsername()) && SQLiteDatabase.getInstance().hasUsername(username)) {
					alert(AlertType.ERROR, "Already some other user taken that username!");
				} else {
					User user = new User(first, last, username, password);
					user.setVip(currentUser.isVip());
					user.setUuid(currentUser.getUuid());
					SQLiteDatabase.getInstance().updateUser(user);
					alert(AlertType.INFORMATION, "User details has been updated");
				}
			} catch (SQLException e) {
				sqlError(e);
			}
		}
	}

	/**
	 * It will move back to the dashboard view.
	 * 
	 * @param event for action
	 */
	@FXML
	public void back(ActionEvent event) {
		super.getUpdator().setScene(View.DASHBOARD);
	}

}
