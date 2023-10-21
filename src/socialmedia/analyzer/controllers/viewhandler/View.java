package socialmedia.analyzer.controllers.viewhandler;

/**
 * Views, so that they can be selected to be viewed on the window.
 */
public enum View {

	LOGIN("views/login.fxml"), DASHBOARD("views/dashboard.fxml"), PROFILE("views/profile.fxml"),
	STATISTICS("views/statistics.fxml");

	// Attributes.
	private String fileLocation;

	/**
	 * Constructor to initialize.
	 * 
	 * @param filename for enum
	 */
	View(String filename) {
		this.fileLocation = filename;
	}

	/**
	 * @return file location
	 */
	@Override
	public String toString() {
		return fileLocation;
	}

}
