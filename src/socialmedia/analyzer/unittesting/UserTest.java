package socialmedia.analyzer.unittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import socialmedia.analyzer.models.Post;
import socialmedia.analyzer.models.User;

/**
 * Tests for the User.
 */
public class UserTest {

	private User dummyUser;

	@BeforeEach
	public void setUp() {
		dummyUser = new User("Lilly", "Jackson", "lillyjack", "jackjack");
	}

	@AfterEach
	public void tearDown() throws Exception {
		dummyUser = null;
	}

	@Test
	public void testConstructor() {
		assertEquals("Lilly", dummyUser.getFirstName());
		assertEquals("Jackson", dummyUser.getLastName());
		assertEquals("lillyjack", dummyUser.getUsername());
		assertEquals("jackjack", dummyUser.getPassword());
		assertFalse(dummyUser.isVip());
	}

	@Test
	public void testAttributesGS() {
		assertEquals("Lilly", dummyUser.getFirstName());
		dummyUser.setFirstName("John");
		assertEquals("John", dummyUser.getFirstName());
		assertEquals("Jackson", dummyUser.getLastName());
		dummyUser.setLastName("Wick");
		assertEquals("Wick", dummyUser.getLastName());
		assertEquals("lillyjack", dummyUser.getUsername());
		dummyUser.setUsername("jwick");
		assertEquals("jwick", dummyUser.getUsername());
		assertEquals("jackjack", dummyUser.getPassword());
		dummyUser.setPassword("wick23");
		assertEquals("wick23", dummyUser.getPassword());
		assertFalse(dummyUser.isVip());
		dummyUser.setVip(true);
		assertTrue(dummyUser.isVip());
		HashMap<Integer, Post> posts = new HashMap<>();
		posts.put(1, new Post("Test Content", "Test Author", 10, 5, LocalDateTime.now()));
		dummyUser.setPosts(posts);
		assertFalse(dummyUser.getPosts().isEmpty());
	}

	@Test
	public void testGetCSVWritableLine() {
		assertEquals("\"" + dummyUser.getUuid() + "\",\"Lilly\",\"Jackson\",\"lillyjack\",\"jackjack\"",
				dummyUser.getCSVWritableLine());
	}

}
