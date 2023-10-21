package socialmedia.analyzer.controllers;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert.AlertType;
import socialmedia.analyzer.controllers.viewhandler.View;
import socialmedia.analyzer.database.SQLiteDatabase;
import socialmedia.analyzer.models.Post;

/**
 * The controller for statistics.fxml file
 */
public class StatisticsController extends Controller {

	// Attributes..
	@FXML
	private PieChart pieChart;

	/**
	 * It will extract the posts from the database.
	 * 
	 * It will count the number of posts which will be categorized in 3 parts from 0
	 * to 99, 100 to 999 and then remaining counts.
	 * 
	 * It will generate the Pie Chart for it.
	 */
	@Override
	public void update() {
		int length = 0;
		int[] count = new int[3];
		String[] titles = { "(0-99)", "(100-999)", "1000+" };
		try {
			SQLiteDatabase db = SQLiteDatabase.getInstance();
			int[] start = { 0, 100, 1000 };
			int[] end = { 99, 999, Integer.MAX_VALUE };
			for (Post each : db.getNPosts(db.getLoggedInUser().getUuid(), Integer.MAX_VALUE).values()) {
				for (int i = 0; i < count.length; i++) {
					if (each.getShares() >= start[i] && each.getShares() <= end[i]) {
						count[i]++;
						length++;
					}
				}
			}
		} catch (SQLException e) {
			super.sqlError(e);
		}
		ObservableList<PieChart.Data> list = pieChart.getData();
		list.clear();
		for (int i = 0; i < count.length; i++) {
			list.add(new PieChart.Data("Shares " + titles[i], count[i]));
		}
		if (length == 0) {
			alert(AlertType.INFORMATION, "There is no any posts to be displayed!");
		}
	}

	/**
	 * It will move back to the dashboard.
	 * 
	 * @param event for action
	 */
	@FXML
	public void back(ActionEvent event) {
		super.getUpdator().setScene(View.DASHBOARD);
	}

}
