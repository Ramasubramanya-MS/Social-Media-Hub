package socialmedia.analyzer.models;

import java.util.HashMap;

public class User {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private HashMap<Integer, Post> posts;
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 */
	public User(String firstName, String lastName, String username, String password) {
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.posts = new HashMap<>();
	
	}

	/**
	 * Getter Methods for firstName
	 * 
	 * @return the firstName field
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Update the value of firstName
	 *
	 * @param firstName updated value
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter Methods for lastName
	 * 
	 * @return the lastName field
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Update the value of lastName
	 *
	 * @param lastName updated value
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter Methods for username
	 * 
	 * @return the username field
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Update the value of username
	 *
	 * @param username updated value
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter Methods for password
	 * 
	 * @return the password field
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Update the value of password
	 *
	 * @param password updated value
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter Methods for posts
	 * 
	 * @return the posts field
	 */
	public HashMap<Integer, Post> getPosts() {
		return posts;
	}

	/**
	 * Update the value of posts
	 *
	 * @param posts updated value
	 */
	public void setPosts(HashMap<Integer, Post> posts) {
		this.posts = posts;
	}
	
}
