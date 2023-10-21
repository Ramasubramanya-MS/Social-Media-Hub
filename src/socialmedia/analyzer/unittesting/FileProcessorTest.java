package socialmedia.analyzer.unittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import socialmedia.analyzer.database.FileProcessor;
import socialmedia.analyzer.models.Post;

/**
 * Tests for the FileProcessor class.
 */
public class FileProcessorTest {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testParsePost() {
		String line = "1,Test Content,Test Author,10,5,10/10/2023 12:00";
		Post post = FileProcessor.parsePost(line);
		assertNotNull(post);
		assertEquals("Test Content", post.getContent());
		assertEquals("Test Author", post.getAuthor());
		assertEquals(10, post.getLikes());
		assertEquals(5, post.getShares());
		assertEquals(LocalDateTime.of(2023, 10, 10, 12, 0), post.getDateTime());
	}
}
