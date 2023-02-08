package hhsof3as3.bookstore.webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hhsof3as3.bookstore.domain.Book;
import hhsof3as3.bookstore.domain.BookRepository;

@Controller
public class BookController {
@Autowired
	
	private BookRepository bookRepository;
	
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
}
