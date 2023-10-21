package socialmedia.analyzer.controllers.viewhandler;

public enum View {

	LOGIN("views/login.fxml"),
	DASHBOARD("views/dashboard.fxml"),
	PROFILE("views/profile.fxml");
	
	private String fileLocation;
	
	View (String filename){
		this.fileLocation = filename;
	}
	
	@Override
	public String toString() {
		return fileLocation;
	}
	
}
