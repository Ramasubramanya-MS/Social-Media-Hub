package socialmedia.analyzer.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import socialmedia.analyzer.controllers.viewhandler.View;
import socialmedia.analyzer.database.FileProcessor;
import socialmedia.analyzer.database.SQLiteDatabase;
import socialmedia.analyzer.models.Post;
import socialmedia.analyzer.models.User;

/**
 * Controller for the dasboard.fxml view.
 */
public class DashboardController extends Controller {

	// Attributes..
	@FXML
	private TextField postID, postAuthor, postLikes, postShares, findID, likesField;
	@FXML
	private Button addPost, find, delete, retreivePost, logout, importBTN, upgradeBTN, statisticsBTN;
	@FXML
	private TextArea output, postContent;
	@FXML
	private Label userProfilename;
	@FXML
	private DatePicker postDate;

	/**
	 * It will update and make the UI ready to be displayed.
	 */
	@Override
	public void update() {
		try {
			User user = SQLiteDatabase.getInstance().getLoggedInUser();
			upgradeBTN.setVisible(!user.isVip());
			importBTN.setVisible(user.isVip());
			statisticsBTN.setVisible(user.isVip());
			userProfilename.setText(user.getFirstName() + " " + user.getLastName());
		} catch (SQLException e) {
			super.sqlError(e);
		}
	}

	/**
	 * It will open the profile UI settings.
	 * 
	 * @param event for mouse
	 */
	@FXML
	private void showProfileSettings(MouseEvent event) {
		super.getUpdator().setScene(View.PROFILE);
	}

	/**
	 * It will do all of the validation including checking if any field is empty, or
	 * integers are incorrectly entered. It will add the post into the database.
	 * 
	 * @param event for action
	 */
	@FXML
	private void addPost(ActionEvent event) {
		String content = postContent.getText().trim();
		String author = postAuthor.getText().trim();
		if (content.isEmpty() || author.isEmpty()) {
			alert(AlertType.ERROR, "No any field should be empty!");
		} else {
			int likes, shares;
			try {
				likes = Integer.parseInt(postLikes.getText().trim());
				shares = Integer.parseInt(postShares.getText().trim());
				if (likes < 0 || shares < 0) {
					alert(AlertType.ERROR, "Likes & Shares should be positive!");
				} else {
					SQLiteDatabase.getInstance().addPost(SQLiteDatabase.getInstance().getLoggedInUser().getUuid(),
							new Post(content, author, likes, shares, postDate.getValue().atStartOfDay()));
					alert(AlertType.INFORMATION, "Post is added Successfully!");
				}
			} catch (NumberFormatException e) {
				alert(AlertType.ERROR, "Likes & Shares should be valid integers.");
			} catch (SQLException e) {
				super.sqlError(e);
			}
		}
	}

	/**
	 * Take the id from the user and validate it.
	 * 
	 * If post is found, then display the details in the output, else display the
	 * error message.
	 * 
	 * @param event for action
	 */
	@FXML
	private void find(ActionEvent event) {
		try {
			int id = Integer.parseInt(findID.getText().toString());
			Post post = SQLiteDatabase.getInstance().findPost(SQLiteDatabase.getInstance().getLoggedInUser().getUuid(),
					id);
			if (post != null) {
				output.setText(post.toString());
			} else {
				alert(AlertType.ERROR, id + " is not associated with any post or user!");
			}
		} catch (NumberFormatException e) {
			alert(AlertType.ERROR, "ID should be a valid integer.");
		} catch (SQLException e) {
			super.sqlError(e);
		}
	}

	/**
	 * Take the id from the user and validate it.
	 * 
	 * If post exists, then delete the post , else display the error message.
	 * 
	 * @param event for action
	 */
	@FXML
	private void delete(ActionEvent event) {
		try {
			int id = Integer.parseInt(findID.getText().toString());
			Post post = SQLiteDatabase.getInstance().findPost(SQLiteDatabase.getInstance().getLoggedInUser().getUuid(),
					id);
			if (post != null) {
				SQLiteDatabase.getInstance().deletePost(SQLiteDatabase.getInstance().getLoggedInUser().getUuid(), id);
				alert(AlertType.INFORMATION, "Post is deleted successfully!");
			} else {
				alert(AlertType.ERROR, id + " is not associated with any post or user!");
			}
		} catch (NumberFormatException e) {
			alert(AlertType.ERROR, "ID should be a valid integer.");
		} catch (SQLException e) {
			super.sqlError(e);
		}
	}

	/**
	 * Take the number of posts from the user and validate it.
	 * 
	 * It will iterate through all of the posts which are linked to the current user
	 * id. If there are posts with the maximum limit according to the user entered
	 * value, it will be displayed in the output section.
	 * 
	 * @param event for action
	 */
	@FXML
	private void retreivePost(ActionEvent event) {
		try {
			int N = Integer.parseInt(likesField.getText().toString());
			HashMap<Integer, Post> posts = SQLiteDatabase.getInstance()
					.getNPosts(SQLiteDatabase.getInstance().getLoggedInUser().getUuid(), N);
			if (!posts.isEmpty()) {
				String text = "";
				for (Post each : posts.values()) {
					text += each.toString() + "\n";
				}
				output.setText(text);
			} else {
				alert(AlertType.ERROR, "There is no any post in this account!");
			}
		} catch (NumberFormatException e) {
			alert(AlertType.ERROR, "ID should be a valid integer.");
		} catch (SQLException e) {
			super.sqlError(e);
		}
	}

	/**
	 * It will logout the user and move to the login screen.
	 * 
	 * @param event for action
	 */
	@FXML
	private void logout(ActionEvent event) {
		super.getUpdator().setScene(View.LOGIN);
	}

	/**
	 * It will take all of the posts from the current user and write them into the
	 * file location where user wants to.
	 * 
	 * @param event for action
	 */
	@FXML
	private void exportPost(ActionEvent event) {
		try {
			HashMap<Integer, Post> posts = SQLiteDatabase.getInstance()
					.getNPosts(SQLiteDatabase.getInstance().getLoggedInUser().getUuid(), Integer.MAX_VALUE);
			FileProcessor.writePosts(posts);
			alert(AlertType.INFORMATION, "Posts are exported successfully!");
		} catch (SQLException e) {
			super.sqlError(e);
		} catch (IOException e) {
			alert(AlertType.ERROR, "Unable to write the file: " + e.toString());
		}
	}

	/**
	 * It will take the file from the user and read all of the posts and add them
	 * into the database.
	 * 
	 * @param event for action
	 */
	@FXML
	private void importPost(ActionEvent event) {
		try {
			for (Post each : FileProcessor.readPosts()) {
				SQLiteDatabase.getInstance().addPost(SQLiteDatabase.getInstance().getLoggedInUser().getUuid(), each);
			}
			alert(AlertType.INFORMATION, "All posts are imported successfully!");
		} catch (IOException e) {
			alert(AlertType.ERROR, "Unable to read data from CSV.");
		} catch (SQLException e) {
			super.sqlError(e);
		}
	}

	/**
	 * It will open the UI screen for the statistics.
	 * 
	 * @param event for action
	 */
	@FXML
	private void statistics(ActionEvent event) {
		super.getUpdator().setScene(View.STATISTICS);
	}

	/**
	 * It will ask for the confirmation from the user, if he wants to upgrade his
	 * account for free, if he agreens, the VIP attribute will be marked as true in
	 * the database.
	 * 
	 * @param event for action
	 */
	@FXML
	private void upgrade(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Social Media Analyzer");
		alert.setHeaderText("Upgrade Membership");
		alert.setContentText("Would you like to subscribe to" + " the application for a monthly fee of $0?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			try {
				SQLiteDatabase.getInstance().makeVIP(SQLiteDatabase.getInstance().getLoggedInUser());
				upgradeBTN.setVisible(false);
				alert(AlertType.INFORMATION, "Please logout and log in again" + " to access VIP functionalities.");
			} catch (SQLException e) {
				super.sqlError(e);
			}
		}
	}
}
