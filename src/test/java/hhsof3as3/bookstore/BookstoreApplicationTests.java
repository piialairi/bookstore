package hhsof3as3.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hhsof3as3.bookstore.webController.BookController;

//@RunWith(SpringRunner.class) // JUnit4
@ExtendWith(SpringExtension.class) //JUnit 5 eli Jupiter
@SpringBootTest
class BookstoreApplicationTests {
	@Autowired
	private BookController bookController;

	@Test
	void contextLoads()throws Exception {
		assertThat(bookController).isNotNull();
	}

}
