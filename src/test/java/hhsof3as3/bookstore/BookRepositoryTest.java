package hhsof3as3.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hhsof3as3.bookstore.domain.Book;
import hhsof3as3.bookstore.domain.BookRepository;
import hhsof3as3.bookstore.domain.Category;
import hhsof3as3.bookstore.domain.CategoryRepository;

//@RunWith(SpringRunner.class)  //JUnit4
@ExtendWith(SpringExtension.class)  // JUnit5 eli Jupiter
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
    private TestEntityManager em;
	
	@Test // testataan BookRepon findByTitle()-metodin toimivuutta
	public void findByTitleShouldReturnTitle() {
		List<Book> books =bookRepository.findByTitle("Opi ajamaan");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Heino Heinänen");
		}

	@Test // testataan BookRepon save-metodin toimivuutta
	public void createNewBook() {
		Book book = new Book("Opi rakentamaan 1", "Heino Heinänen", 2020, 4589775, 45, null);
		bookRepository.save(book); // sql-insert
		assertThat(book.getId()).isNotNull();
	}
	
	@Test // testataan BookRepon poisto-metodin toimivuutta
	public void DeleteBookTest() {
		Book book = new Book("Opi rakentamaan 1", "Heino Heinänen", 2020, 4589775, 45, null);
		final Long id = em.persistAndGetId(book, Long.class);
        bookRepository.deleteById(id);
        em.flush();
        Book after = em.find(Book.class, id);
		bookRepository.deleteById(book.getId());
		assertThat(after).isNull();
	}
	
	@Test // testataan CategoryRepon findByName-metodin toimivuutta
	public void findByNameShouldReturnName() {
		List<Category> categorys = categoryRepository.findByName("Self help");
		
		assertThat(categorys).hasSize(1);
		assertThat(categorys.get(0).getName()).isEqualTo("Self help");
		}
	
	@Test // testataan CategoryRepon save-metodin toimivuutta
	public void createNewCategory() {
		Category category = new Category();
		categoryRepository.save(category); // sql-insert
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	@Test // testataan CategoryRepon poisto-metodin toimivuutta
	public void DeleteCategoryTest() {
		Category category = new Category();
		final Long id = em.persistAndGetId(category, Long.class);
        categoryRepository.deleteById(id);
        em.flush();
        Category after = em.find(Category.class, id);
		categoryRepository.deleteById(category.getCategoryid());
		assertThat(after).isNull();
	}
}
