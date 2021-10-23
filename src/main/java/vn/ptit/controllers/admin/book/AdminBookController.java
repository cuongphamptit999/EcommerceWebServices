package vn.ptit.controllers.admin.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.book.Book;
import vn.ptit.repositories.book.BookRepository;

@RestController
@RequestMapping("/rest/api/book")
public class AdminBookController {
	@Autowired BookRepository bookRepository;
	
	@PostMapping(value = "/insert")
	public Book insert(@RequestBody Book book, ModelMap model, HttpServletRequest req, HttpServletResponse resp) {
		return bookRepository.save(book);
	}
}