package hhsof3as3.bookstore.webController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hhsof3as3.bookstore.domain.Book;
import hhsof3as3.bookstore.domain.BookRepository;
import hhsof3as3.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping("/index")
	public String Bookstore() {
		return "bookstore"; 
	}
	//kirjalistaus
	@RequestMapping(value = "/booklist", method = RequestMethod.GET) 
	public String bookList(Model model) {
		List<Book> books = (List<Book>) bookRepository.findAll(); // haetaan tietokannasta
		model.addAttribute("books", books); // välitetään kirjalista templatelle model-olion avulla
		//model.addAttribute("books", bookRepository.findAll());
		return "booklist"; // .html
	}
	
	//REST-metodi get all books
	@GetMapping("/books")
	public @ResponseBody List<Book> getBooks(){
		return (List<Book>) bookRepository.findAll();
	}
	
	// REST-metodi, get book by id
	@GetMapping(value="/books/{id}")
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
		return bookRepository.findById(bookId);
	}
	
	// lisäys
	@RequestMapping(value = "/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", categoryRepository.findAll());
		return "addbook"; // html
	}
	
	// tallenna
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}
	
	
	// kirjan poisto
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	// kirjan muokkaus
	@RequestMapping(value = "edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));//Book bookToedit = rep.findbyid(bookid.get()
		//bookRepository.save(book);							model.addattr("book", bookToedit)
		return "editbook";
		
	}
		
}
