package vn.ptit.controllers.shoes;

import java.util.ArrayList;
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

import vn.ptit.entities.shoes.*;
import vn.ptit.repositories.shoes.ShoesItemRepository;
import vn.ptit.services.ShoesService;
import vn.ptit.utils.FilterMap;

@RestController
@RequestMapping("/rest/api/shoes-item")
public class ShoesItemController {

	@Autowired
	ShoesItemRepository shoesItemRepository;

	@Autowired
	ShoesService shoesService;

	@GetMapping(value = "/find-all")
	public List<ShoesItem> findAll(ModelMap model, HttpServletRequest req, HttpServletResponse resp) {
		return shoesItemRepository.findAll();
	}

	@GetMapping(value = "/{slug}")
	public ShoesItem getShoesItemBySlug1(@PathVariable("slug") String slug) {
		return shoesService.getShoesItemBySlug(slug).get(0);
	}

	@GetMapping(value = "/get-4-shoes-item/{slug}")
	public List<ShoesItem> get4ShoesItemBySlug(@PathVariable("slug") String slug) {
		return shoesService.get4CShoesItemBySlug(slug);
	}

	@GetMapping(value = "/get-8-shoes-item")
	public List<ShoesItem> get8ShoesItemInHome() {
		return shoesService.get8ShoesItemInHome();
	}

	@GetMapping(value = "/find-by-name/{name}")
	public List<ShoesItem> getShoesItemByName(@PathVariable("name") String name) {
		return shoesService.findByName(name);
	}

	@PostMapping(value = "/insert")
	public ShoesItem insert(@RequestBody ShoesItem shoesItem, ModelMap model, HttpServletRequest req,
			HttpServletResponse resp) {
		for (int i = 0; i < shoesItem.getImgShoesItems().size(); i++) {
			shoesItem.getImgShoesItems().get(i).setShoesItem(shoesItem);
		}
		return shoesItemRepository.save(shoesItem);
	}

	@PostMapping(value = "/find-all-in-category")
	public List<ShoesItem> findAllInCategory(@RequestBody List<FilterMap> filterMap, ModelMap model,
			HttpServletRequest req, HttpServletResponse resp) {

		return shoesService.findByCategory(filterMap);
	}

	@PostMapping(value = "/find-by-category")
	public List<ShoesItem> filterByCategory(@RequestBody List<FilterMap> filterMap) {
		List<ShoesItem> shoesItems = shoesService.findItemByCategory(filterMap);
		List<ShoesItem> itemByCategory = new ArrayList<>();
		if (filterMap.get(0).getValue().equalsIgnoreCase("sneaker")) {
			for (ShoesItem shoesItem : shoesItems) {
				if (shoesItem.getShoes() instanceof Sneaker) {
					itemByCategory.add(shoesItem);
				}
			}
		} else if (filterMap.get(0).getValue().equalsIgnoreCase("highheels")) {
			for (ShoesItem shoesItem : shoesItems) {
				if (shoesItem.getShoes() instanceof HighHeels) {
					itemByCategory.add(shoesItem);
				}
			}
		} else if (filterMap.get(0).getValue().equalsIgnoreCase("boots")) {
			for (ShoesItem shoesItem : shoesItems) {
				if (shoesItem.getShoes() instanceof Boots) {
					itemByCategory.add(shoesItem);
				}
			}
		}
		return itemByCategory;
	}

	@GetMapping(value = "/delete-by-code/{code}")
	public Integer deleteByCode(@PathVariable("code") String code) {
		shoesItemRepository.deleteById(code);
		return 1;
	}

	@GetMapping(value = "/find-by-code/{code}")
	public ShoesItem findByCode(@PathVariable("code") String code) {
		return shoesItemRepository.findById(code).get();

	}

	@PostMapping(value = "/update")
	public ShoesItem update(@RequestBody ShoesItem shoesItem, ModelMap model, HttpServletRequest req,
			HttpServletResponse resp) {

		return shoesItemRepository.save(shoesItem);
	}
}
