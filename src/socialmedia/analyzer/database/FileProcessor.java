package socialmedia.analyzer.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.stage.FileChooser;
import socialmedia.analyzer.models.Post;

/**
 * It contains the operations related to the files.
 */
public class FileProcessor {

	/**
	 * It will write the posts into the text file after selecting a choosen file by
	 * the user.
	 * 
	 * @param post map of the posts linked with id
	 * @throws IOException if there is any IO related issues.
	 */
	public static void writePosts(HashMap<Integer, Post> post) throws IOException {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save Posts");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
		File file = chooser.showSaveDialog(null);
		if (file != null) {
			PrintWriter writer = new PrintWriter(new FileWriter(file));
			writer.println(Post.getCSVHeaders());
			for (Post each : post.values()) {
				writer.println(each.getCSVWritableLine());
			}
			writer.flush();
			writer.close();
		}
	}

	/**
	 * It will choose the CSV file, and read all of the lines out of it, it will
	 * parse the lines into the Post objects and add them into the post lists.
	 * 
	 * @return list of posts
	 * @throws IOException if there is any IO related issues.
	 */
	public static ArrayList<Post> readPosts() throws IOException {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Import Posts");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
		File file = chooser.showOpenDialog(null);
		ArrayList<Post> posts = new ArrayList<>();
		if (file != null) {
			List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
			if (!lines.isEmpty()) {
				lines.remove(0);
				for (String each : lines) {
					String[] tokens = each.split(",");
					if (tokens == null || tokens.length != 6) {
						continue;
					}
					Post post = null;
					try {
						post = parsePost(each);
					} catch (NumberFormatException nfe) {
						post = null;
					}
					if (post != null) {
						posts.add(post);
					}
				}
			}
		}
		return posts;
	}

	/*
	 * Helper Methods..
	 */
	/**
	 * It will parse the Post object from the String line.
	 * 
	 * @param line to be processed
	 * @return parsed post
	 */
	private static Post parsePost(String line) {
		String[] tokens = line.split(",");
		if (tokens == null || tokens.length != 6) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(tokens[5], formatter);
		return new Post(tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), dateTime);
	}

}
