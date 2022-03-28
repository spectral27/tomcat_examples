package jaxrs_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemRepository {

	public List<Item> selectAllItems() {
		try (Connection connection = JdbcConnection.get()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from items");
			List<Item> items = new ArrayList<Item>();
			while (resultSet.next()) {
				Item item = new Item();
				item.setId(resultSet.getString(1));
				item.setContent(resultSet.getString(2));
				item.setOrigin(resultSet.getString(3));
				items.add(item);
			}
			return items;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Item selectItem(String id) {
		try (Connection connection = JdbcConnection.get()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from items where id = \"" + id + "\"");
			Item item = new Item();
			if (resultSet.next()) {
				item.setId(resultSet.getString(1));
				item.setContent(resultSet.getString(2));
				item.setOrigin(resultSet.getString(3));
			}
			return item;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int insertItem(Item item) {
		try (Connection connection = JdbcConnection.get()) {
			PreparedStatement preparedStatement = 
					connection.prepareStatement("insert into items values (?, ?, ?)");
			preparedStatement.setString(1, UUID.randomUUID().toString());
			preparedStatement.setString(2, item.getContent());
			preparedStatement.setString(3, "jaxrs_jdbc");
			preparedStatement.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int updateItem(String id, Item item) {
		try (Connection connection = JdbcConnection.get()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update items set content = ? where id = ?");
			preparedStatement.setString(1, item.getContent());
			preparedStatement.setString(2, id);
			preparedStatement.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int deleteItem(String id) {
		try (Connection connection = JdbcConnection.get()) {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from items where id = ?");
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
			connection.close();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
