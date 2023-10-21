package socialmedia.analyzer;

import javafx.application.Application;

/*

* Main.java
*
* version 1.0.0
*
* created at 25/07/2023
*
* A simple social media analyser program that is able to do the following:
* - Load posts from csv file (default is "posts.csv")
* - Add new post to posts list
* - Delete existing post from posts list
* - Get post by its ID then print its details
* - Display the most N liked posts
* - Display the most N shared posts
*/

/**
 * Entry point of the Social Media Analyser program
 *
 * @author AA
 * @version 1.0.0
 */
public class Main {
	
	/**
	 * Main method to execute the program.
	 * 
	 * @param args console based arguments.
	 */
	public static void main(String[] args) {
		Application.launch(SocialMediaApplication.class);
	}
}
