package spec_ctx01.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spec_ctx01.entity.Item;

@Repository
public class ItemRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Item> rowMapper = (rs, rowNum) -> {
		Item item = new Item();
		item.setId(rs.getString(1));
		item.setValue(rs.getString(2));
		return item;
	};

	public List<Item> selectAllItems() {
		return jdbcTemplate.query("select * from items order by value", rowMapper);
	}

	public int insertItem(Item item) {
		String id = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
		return jdbcTemplate.update("insert into items values (?, ?)", 
				id, item.getValue());
	}

	public int updateItem(String id, Item item) {
		return jdbcTemplate.update("update items set value = ? where id = ?", 
				item.getValue(), id);
	}
	
	public int deleteItem(String id) {
		return jdbcTemplate.update("delete from items where id = ?", 
				id);
	}

}
