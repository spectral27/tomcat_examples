package spec_ctx01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spec_ctx01.entity.Item;
import spec_ctx01.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping
	public List<Item> selectAllItems() {
		return itemRepository.selectAllItems();
	}
	
	@PostMapping
	public int insertItem(@RequestBody Item item) {
		return itemRepository.insertItem(item);
	}
	
	@PutMapping("/{id}")
	public int updateItem(@PathVariable("id") String id, @RequestBody Item item) {
		return itemRepository.updateItem(id, item);
	}
	
	@DeleteMapping("/{id}")
	public int deleteItem(@PathVariable("id") String id) {
		return itemRepository.deleteItem(id);
	}

}
