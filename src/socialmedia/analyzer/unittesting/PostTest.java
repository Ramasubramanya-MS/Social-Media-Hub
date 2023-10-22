package socialmedia.analyzer.unittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import socialmedia.analyzer.models.Post;

/**
 * Test the Post class
 */
public class PostTest {

	private Post dummyPost;
	private LocalDateTime dummyDate;

	@BeforeEach
	public void setUp() {
		dummyDate = LocalDateTime.of(2023, 10, 21, 07, 25);
		dummyPost = new Post("Test Content", "Test Author", 10, 5, dummyDate);
	}

	@AfterEach
	public void tearDown() throws Exception {
		dummyDate = null;
		dummyPost = null;
	}

	@Test
	public void testConstructor() {
		assertEquals("Test Content", dummyPost.getContent());
		assertEquals("Test Author", dummyPost.getAuthor());
		assertEquals(10, dummyPost.getLikes());
		assertEquals(5, dummyPost.getShares());
		assertEquals(dummyDate, dummyPost.getDateTime());
	}

	@Test
	public void testAttributesGS() {
		assertEquals("Test Content", dummyPost.getContent());
		dummyPost.setContent("Updated Content");
		assertEquals("Updated Content", dummyPost.getContent());
		assertEquals("Test Author", dummyPost.getAuthor());
		dummyPost.setAuthor("Updated Author");
		assertEquals("Updated Author", dummyPost.getAuthor());
		assertEquals(10, dummyPost.getLikes());
		dummyPost.setLikes(20);
		assertEquals(20, dummyPost.getLikes());
		assertEquals(5, dummyPost.getShares());
		dummyPost.setShares(10);
		assertEquals(10, dummyPost.getShares());
		assertEquals(dummyDate, dummyPost.getDateTime());
		LocalDateTime updatedDate = LocalDateTime.of(2023, 10, 22, 8, 35);
		dummyPost.setDateTime(updatedDate);
		assertEquals(updatedDate, dummyPost.getDateTime());
	}

	@Test
	public void testGetCSVWritableLine() {
		String expected = "\"0\",\"Test Content\",\"Test Author\",\"10\",\"5\",\"2023-10-21T07:25\"";
		assertEquals(expected, dummyPost.getCSVWritableLine());
	}

}
