package socialmedia.analyzer.models;

import java.util.HashMap;
import java.util.UUID;

/**
 * Model for the User
 */
public class User {

	// Attributes..
	private String uuid;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean vip;
	private HashMap<Integer, Post> posts;

	/**
	 * Constructor for the User.
	 * 
	 * @param firstName field of user
	 * @param lastName  field of user
	 * @param username  field of user
	 * @param password  field of user
	 */
	public User(String firstName, String lastName, String username, String password) {

		this(UUID.randomUUID().toString(), firstName, lastName, username, password);

	}

	/**
	 * Constructor for the User.
	 * 
	 * @param uuid      field of user
	 * @param firstName field of user
	 * @param lastName  field of user
	 * @param username  field of user
	 * @param password  field of user
	 */
	public User(String uuid, String firstName, String lastName, String username, String password) {

		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.posts = new HashMap<>();
		this.vip = false;

	}
	
//	public User(String uuid, String firstName, String lastName, String username, String password, Boolean vip) {
//
//		this.uuid = uuid;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.username = username;
//		this.password = password;
//		this.posts = new HashMap<>();
//		this.vip = vip;
//
//	}
	
	/**
	 * @return the text form of CSV to be written.
	 */
	public String getCSVWritableLine() {
		return "\"" + uuid + "\"," + "\"" + firstName + "\"," + "\"" + lastName + "\"," + "\"" + username + "\"," + "\""
				+ password + "\"";
	}

	/**
	 * Getter Methods for uuid
	 * 
	 * @return the uuid field
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Update the value of uuid
	 *
	 * @param uuid updated value
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	/**
	 * Getter Methods for vip
	 * 
	 * @return the vip field
	 */
	public boolean isVip() {
		return vip;
	}

	/**
	 * Update the value of vip
	 *
	 * @param vip updated value
	 */
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	public boolean getVip() {
		return vip;
	}

}
