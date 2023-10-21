package socialmedia.analyzer;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import socialmedia.analyzer.controllers.Controller;
import socialmedia.analyzer.controllers.viewhandler.UIUpdator;
import socialmedia.analyzer.controllers.viewhandler.View;

public class SocialMediaApplication extends Application implements UIUpdator {

	private Stage stage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.stage = primaryStage;
		stage.setTitle("Social Media Analyzer");
		setScene(View.LOGIN);
		stage.show();
		
	}

	@Override
	public void setScene(View view) {
		
		try {
			
			// Loading..
			FXMLLoader loader = new FXMLLoader(getClass().getResource(view.toString()));
			Parent root = loader.load();
			Controller controller = loader.getController();
			controller.setUpdator(this);
			// Setting scene..
			stage.setScene(new Scene(root));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
