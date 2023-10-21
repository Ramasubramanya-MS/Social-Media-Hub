package socialmedia.analyzer.database;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import socialmedia.analyzer.models.Post;

public class FileDatabase {

	public static void writePosts(String filename, HashMap<String, Post> post) throws IOException {
		PrintWriter writer = new PrintWriter(new FileWriter(filename));
		for (Post each : post.values()) {
			writer.println(each.getCSVWritableLine());
		}
		writer.flush();
		writer.close();
	}

	public static HashMap<Integer, Post> readPosts(String filename, String userUUID) throws IOException {
		HashMap<Integer, Post> posts = new HashMap<>();
		List<String> lines = Files.readAllLines(Paths.get(filename));
		for (String each : lines) {
			String[] tokens = getTokens(each);
			if (tokens == null || tokens.length != 6) {
				continue;
			}
			if (tokens[0].equals(userUUID)) {
				Post post = null;
				try {
					post = parsePost(each);
				} catch (NumberFormatException nfe) {
					post = null;
				}
				if (post != null) {
					posts.put(post.getId(), post);
				}
			}
		}
		return posts;
	}

	/*
	 * Helper Methods..
	 */
	private static Post parsePost(String line) {
		String[] tokens = getTokens(line);
		if (tokens == null || tokens.length != 6) {
			return null;
		}
		return new Post(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Integer.parseInt(tokens[4]),
				Integer.parseInt(tokens[5]), LocalDateTime.parse(tokens[6]));
	}

	private static String[] getTokens(String line) {
		String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].replaceAll("^\"|\"$", "").replace("\"\"", "\"");
		}
		return tokens;
	}

}
