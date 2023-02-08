package hhsof3as3.bookstore.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
	
	@RequestMapping("/index")
	public String Bookstore() {
		return "bookstore"; 
	}
}
