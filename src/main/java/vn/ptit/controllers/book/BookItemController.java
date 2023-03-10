package vn.ptit.controllers.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.book.BookItem;
import vn.ptit.entities.electronics.ElectronicsItem;
import vn.ptit.repositories.book.BookItemRepository;
import vn.ptit.services.BookService;
import vn.ptit.utils.FilterMap;

@RestController
@RequestMapping("/rest/api/book-item")
public class BookItemController {

	@Autowired
	BookItemRepository bookItemRepository;

	@Autowired
	BookService bookService;

	@PostMapping(value = "/find-by-author")
	public List<BookItem> findByAuthor(@RequestBody List<FilterMap> filterMap, ModelMap model, HttpServletRequest req,
			HttpServletResponse resp) {

		return bookService.findByAuthor(filterMap);
	}

	@PostMapping(value = "/find-by-publisher")
	public List<BookItem> findByPublisher(@RequestBody List<FilterMap> filterMap, ModelMap model,
			HttpServletRequest req, HttpServletResponse resp) {

		return bookService.findByPublisher(filterMap);
	}

	@PostMapping(value = "/insert")
	public BookItem insert(@RequestBody BookItem bookItem, ModelMap model, HttpServletRequest req,
			HttpServletResponse resp) {
		for (int i = 0; i < bookItem.getImgBookItems().size(); i++) {
			bookItem.getImgBookItems().get(i).setBookItem(bookItem);
		}
		return bookItemRepository.save(bookItem);
	}

	@GetMapping(value = "/find-all")
	public List<BookItem> findAll() {
		return bookItemRepository.findAll();
	}

	@GetMapping(value = "/{slug}")
	public BookItem getBookItemBySlug(@PathVariable("slug") String slug, ModelMap model, HttpServletRequest req,
			HttpServletResponse resp) {

		return bookService.getBookItemBySlug(slug).get(0);
	}

	@GetMapping(value = "/same-item/{slug}")
	public List<BookItem> getSameBookItem(@PathVariable("slug") String slug, ModelMap model, HttpServletRequest req,
			HttpServletResponse resp) {

		return bookService.getSameBookItem(slug);
	}

	@GetMapping(value = "/find-by-name/{name}")
	public List<BookItem> BookItem(@PathVariable("name") String name) {
		return bookService.findByName(name);
	}

	@GetMapping("/get-item-in-home")
	public List<BookItem> getItemInHome() {

		return bookService.get8ItemInHome();
	}

	@PostMapping(value = "/find-all-in-category")
	public List<BookItem> findAllInCategory(@RequestBody List<FilterMap> filterMap, ModelMap model,
			HttpServletRequest req, HttpServletResponse resp) {

		return bookService.filterByCategory(filterMap);
	}

	@GetMapping(value = "/find-by-code/{code}")
	public BookItem findByCode(@PathVariable("code") String code) {
		return bookItemRepository.findById(code).get();
	}

	@PostMapping(value = "/update")
	public BookItem update(@RequestBody BookItem bookItem, ModelMap model, HttpServletRequest req,
			HttpServletResponse resp) {
		return bookItemRepository.save(bookItem);
	}
	
	@GetMapping(value = "/delete-by-code/{code}")
	public Integer deleteByCode(@PathVariable("code") String code) {
		bookItemRepository.deleteById(code);
		return 1;
	}

}
