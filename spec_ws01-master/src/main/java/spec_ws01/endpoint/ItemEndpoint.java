package spec_ws01.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import spec_ws01.entity.Item;
import spec_ws01.entity.Items;
import spec_ws01.repository.ItemRepository;

@WebService
@SOAPBinding(style = Style.RPC)
public class ItemEndpoint {
	
	private ItemRepository itemRepository = ItemRepository.getInstance();
	
	@WebMethod
	public Items selectAllItems() {
		return new Items(itemRepository.selectAllItems());
	}
	
	@WebMethod
	public int insertItem(Item item) {
		return itemRepository.insertItem(item);
	}
	
	@WebMethod
	public int updateItem(String id, Item item) {
		return itemRepository.updateItem(id, item);
	}
	
	@WebMethod
	public int deleteItem(String id) {
		return itemRepository.deleteItem(id);
	}

}
