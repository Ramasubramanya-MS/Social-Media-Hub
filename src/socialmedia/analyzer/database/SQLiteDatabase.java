package socialmedia.analyzer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

import socialmedia.analyzer.models.Post;
import socialmedia.analyzer.models.User;

/**
 * SQLite Database Handler.
 * 
 * It uses the singleton model.
 * 
 * It will help in connecting to the database, and all of the operations related
 * to database, such as adding, deleting, updating or extracting the data from
 * the database.
 */
public class SQLiteDatabase {

	// Attributes
	private Connection connection;
	private static final String DATABASE_FILE = "data/social_media_database.db";
	private static User loggedInUser;

	/*
	 * Singleton Pattern
	 */
	private static SQLiteDatabase database;

	/**
	 * Singleton Pattern to return the single instance of the current class.
	 * 
	 * @return SQLiteDatabase single instance.
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public static SQLiteDatabase getInstance() throws SQLException {
		if (database == null) {
			database = new SQLiteDatabase();
		}
		return database;
	}

	/**
	 * Initialize the constructor.
	 * 
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public SQLiteDatabase() throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE);
		loggedInUser = null;
	}

	/**
	 * @return the currently logged in user.
	 */
	public User getLoggedInUser() {
		return loggedInUser;
	}

	/**
	 * It will add the passed user into the database.
	 * 
	 * @param user to be added
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public void addUser(User user) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into users " + "values(?, ?, ?, ?, ?, ?)");
		statement.setString(1, user.getUuid());
		statement.setString(2, user.getFirstName());
		statement.setString(3, user.getLastName());
		statement.setString(4, user.getUsername());
		statement.setString(5, user.getPassword());
		statement.setBoolean(6, user.getVip());
		statement.executeUpdate();
	}

	/**
	 * It will update the user details into the database.
	 * 
	 * @param user to be added
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public void updateUser(User user) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(
				"update users set firstName = ?, lastName = ?, username = ?, " + "password = ? where uuid = ?");
		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setString(3, user.getUsername());
		statement.setString(4, user.getPassword());
		statement.setString(5, user.getUuid());
		statement.executeUpdate();
		loggedInUser = user;
	}

	/**
	 * It will make the current user as the VIP.
	 * 
	 * @param user to be marked vip
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public void makeVIP(User user) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("update users set vip = 1 where uuid = ?");
		statement.setString(1, user.getUuid());
		statement.executeUpdate();
	}

	/**
	 * It will add the Post into the database.
	 * 
	 * @param user the user which is linked to the post.
	 * @param post the post to be added
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public void addPost(String user, Post post) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into posts "
				+ "(content, author, likes, shares, dateTime, user_uuid) values (?, ?, ?, ?, ?, ?)");
		statement.setString(1, post.getContent());
		statement.setString(2, post.getAuthor());
		statement.setInt(3, post.getLikes());
		statement.setInt(4, post.getShares());
		statement.setString(5, post.getDateTime().toString());
		statement.setString(6, user);
		statement.executeUpdate();
	}

	/**
	 * It will validate the username and password to check if it exists into the
	 * database then it should return true else returns false.
	 * 
	 * @param username to be checked
	 * @param password to be checked
	 * @return true if exists else false
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public boolean login(String username, String password) throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement("select * from users " + "where username = ? and password = ?");
		statement.setString(1, username);
		statement.setString(2, password);
		boolean result = statement.executeQuery().next();
		if (result) {
			statement = connection.prepareStatement("select * from users where username = ?");
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				loggedInUser = new User(resultSet.getString("uuid"), resultSet.getString("firstName"),
						resultSet.getString("lastName"), resultSet.getString("username"),
						resultSet.getString("password"));
				loggedInUser.setVip(resultSet.getBoolean("vip"));
			}
		}
		return result;
	}

	/**
	 * It will check if the username already exists into the database or not.
	 * 
	 * @param username to be checked
	 * @return true if username exists else false
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public boolean hasUsername(String username) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from users where username = ?");
		return statement.executeQuery().next();
	}

	/**
	 * It will delete the post from the database according to the username and post
	 * id.
	 * 
	 * @param userId to be checked
	 * @param id     post id to be deleted
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public void deletePost(String userId, int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("delete from posts where id = ? and user_uuid = ?");
		statement.setInt(1, id);
		statement.setString(2, userId);
		statement.executeUpdate();
	}

	/**
	 * It will parse the post from the result set and return it.
	 * 
	 * @param result result set from the database
	 * @return parsed post
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	private Post parsePost(ResultSet result) throws SQLException {
		if (result != null) {
			return new Post(result.getInt("id"), result.getString("content"), result.getString("author"),
					result.getInt("likes"), result.getInt("shares"), LocalDateTime.parse(result.getString("dateTime")));
		}
		return null;
	}

	/**
	 * It will find the post using the username and post id and return the post
	 * object.
	 * 
	 * @param userId to be checked
	 * @param id     post id to find
	 * @return found post
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public Post findPost(String userId, int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("select * from posts where id = ? and user_uuid = ?");
		statement.setInt(1, id);
		statement.setString(2, userId);
		return parsePost(statement.executeQuery());
	}

	/**
	 * It will extract the posts in the descending order of the post id and return
	 * the no. of posts according to the limit.
	 * 
	 * @param userId user id linked with posts
	 * @param N      post limit
	 * @return map of posts linked with post id.
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public HashMap<Integer, Post> getNPosts(String userId, int N) throws SQLException {
		HashMap<Integer, Post> posts = new HashMap<>();
		PreparedStatement statement = connection
				.prepareStatement("select * from posts where user_uuid = ? order by likes desc");
		statement.setString(1, userId);
		ResultSet result = statement.executeQuery();
		int count = 0;
		while (result.next()) {
			if (count >= N) {
				break;
			}
			Post post = parsePost(result);
			posts.put(post.getId(), post);
			count++;
		}
		return posts;
	}

	/**
	 * It will close the connection.
	 * 
	 * @throws SQLException if there is some issue with executing SQL query
	 */
	public void close() throws SQLException {
		connection.close();
	}

}
