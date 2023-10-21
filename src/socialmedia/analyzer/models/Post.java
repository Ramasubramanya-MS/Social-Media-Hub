package socialmedia.analyzer.models;

import java.time.LocalDateTime;

/**
 * Post Model
 */
public class Post {

	// Attributes..
	private int id;
	private String content;
	private String author;
	private int likes;
	private int shares;
	private LocalDateTime dateTime;

	/**
	 * Constructor for the post.
	 * 
	 * @param content  field of post
	 * @param author   field of post
	 * @param likes    field of post
	 * @param shares   field of post
	 * @param dateTime field of post
	 */
	public Post(String content, String author, int likes, int shares, LocalDateTime dateTime) {
		this(0, content, author, likes, shares, dateTime);
	}

	/**
	 * Constructor for the post.
	 * 
	 * @param id       field of post
	 * @param content  field of post
	 * @param author   field of post
	 * @param likes    field of post
	 * @param shares   field of post
	 * @param dateTime field of post
	 */
	public Post(int id, String content, String author, int likes, int shares, LocalDateTime dateTime) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.likes = likes;
		this.shares = shares;
		this.dateTime = dateTime;
	}

	/**
	 * @return text form of the post attributes
	 */
	@Override
	public String toString() {
		return "Post # " + id + "\n" + "Content: " + content + "\n" + "Author: " + author + "\n" + "Likes: " + likes
				+ "\n" + "Shares: " + shares + "\n" + "Date Time: " + dateTime + "\n";

	}

	/**
	 * @return text form of the headers for the CSV
	 */
	public static String getCSVHeaders() {
		return "\"ID\",\"Content\",\"Author\",\"Likes\",\"Shares\",\"Date Time\"";
	}

	/**
	 * @return text form of the post to be written into the CSV.
	 */
	public String getCSVWritableLine() {
		return "\"" + id + "\"," + "\"" + content.replace("\"", "\"\"") + "\"," + "\"" + author + "\"," + "\"" + likes
				+ "\"," + "\"" + shares + "\"," + "\"" + dateTime + "\"";
	}

	/**
	 * Getter Methods for id
	 * 
	 * @return the id field
	 */
	public int getId() {
		return id;
	}

	/**
	 * Update the value of id
	 *
	 * @param id updated value
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter Methods for content
	 * 
	 * @return the content field
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Update the value of content
	 *
	 * @param content updated value
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Getter Methods for author
	 * 
	 * @return the author field
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Update the value of author
	 *
	 * @param author updated value
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Getter Methods for likes
	 * 
	 * @return the likes field
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * Update the value of likes
	 *
	 * @param likes updated value
	 */
	public void setLikes(int likes) {
		this.likes = likes;
	}

	/**
	 * Getter Methods for shares
	 * 
	 * @return the shares field
	 */
	public int getShares() {
		return shares;
	}

	/**
	 * Update the value of shares
	 *
	 * @param shares updated value
	 */
	public void setShares(int shares) {
		this.shares = shares;
	}

	/**
	 * Getter Methods for dateTime
	 * 
	 * @return the dateTime field
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	/**
	 * Update the value of dateTime
	 *
	 * @param dateTime updated value
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

}
