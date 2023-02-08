package hhsof3as3.bookstore;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hhsof3as3.bookstore.domain.Book;
import hhsof3as3.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demoData(BookRepository bookRepository) {
		return (args) ->{
			Book book1 = new Book("Synkkä yö", "Kirsi Kirjailija", 2020, 1224, 15.5);
			Book book2 = new Book("Aamurusko", "Niina Näpyttäjä", 2010, 2345, 20.5);
			bookRepository.save(book1); // SQL Insert
			bookRepository.save(book2); // SQL Insert
			
			//haetaan tietokannasta autot
			List<Book> books = (List<Book>) bookRepository.findAll();
			for (Book book : books) {
				System.out.println(book.toString());
			}
		};
		
	}

}
