package hhsof3as3.bookstore;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hhsof3as3.bookstore.domain.Book;
import hhsof3as3.bookstore.domain.BookRepository;
import hhsof3as3.bookstore.domain.Category;
import hhsof3as3.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demoData(BookRepository bookRepository, CategoryRepository categoryrepository) {
		return (args) ->{
			log.info("save some sample categories");
			Category category1 = new Category("Scifi");
			categoryrepository.save(category1);
			Category category2 = new Category("Comic");
			categoryrepository.save(category2);
			Category category3 = new Category("Horror");
			categoryrepository.save(category3);
			Category category4 = new Category("Drama");
			categoryrepository.save(category4);
			Category category5 = new Category("Self help");
			categoryrepository.save(category5);
			
			bookRepository.save(new Book(null, "Opi ajamaan", "Heino Heinänen", 2005, 8795, 30, category5));
			Book book1 = new Book(null, "Synkkä yö", "Kirsi Kirjailija", 2020, 1224, 15.5, category1);
			Book book2 = new Book("Aamurusko", "Niina Näpyttäjä", 2010, 2345, 20.5);
			bookRepository.save(book1); // SQL Insert
			bookRepository.save(book2); // SQL Insert
			
			//haetaan tietokannasta autot
			List<Book> books = (List<Book>) bookRepository.findAll();
			log.info("save some sample books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString2());
			}
			/*for (Book book : books) {
				System.out.println(book.toString());
			}*/
		};
		
	}
}


