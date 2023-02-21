package hhsof3as3.bookstore.webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hhsof3as3.bookstore.domain.Book;
import hhsof3as3.bookstore.domain.Category;
import hhsof3as3.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {
@Autowired

	private CategoryRepository categoryRepository;
	
	/*@RequestMapping("/index")
	public String Categorylist() {
		return "categorylist";
	}*/

	//kategorialistaus
		@RequestMapping(value = "/categorylist", method = RequestMethod.GET) 
		public String categoyList(Model model) {
			List<Category> categorys = (List<Category>) categoryRepository.findAll(); // haetaan tietokannasta
			model.addAttribute("categorys", categorys); // välitetään kategorialista templatelle model-olion avulla
			//model.addAttribute("categorys", categoryRepository.findAll());
			return "categorylist"; // .html
		}
	
	//kategorian lisääminen
	@RequestMapping(value = "/addcategory")
	public String createCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	// tallenna
		@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
		public String saveCategory(Category category) {
			categoryRepository.save(category);
			return "redirect:categorylist";
		}

}
