package spec_02;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ItemRepository {

	private static ItemRepository itemRepository;
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("itemUnit");
	private EntityManager entityManager = factory.createEntityManager();

	private ItemRepository() {

	}

	public static ItemRepository getInstance() {
		if (itemRepository == null) {
			itemRepository = new ItemRepository();
		}
		return itemRepository;
	}

	public List<Item> selectAllItems() {
		return entityManager.createQuery("select i from Item i", Item.class).getResultList();
	}

	public void insertItem(Item item) {
		entityManager.getTransaction().begin();
		item.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 16));
		entityManager.persist(item);
		entityManager.getTransaction().commit();
	}

}
