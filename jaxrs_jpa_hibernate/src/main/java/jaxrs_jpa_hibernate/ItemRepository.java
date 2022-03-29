package jaxrs_jpa_hibernate;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ItemRepository {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("itemUnit");
	private EntityManager entityManager = factory.createEntityManager();

	public List<Item> selectAllItems() {
		return entityManager.createQuery("select i from Item i", Item.class).getResultList();
	}

	public Item selectItem(String id) {
		return entityManager.find(Item.class, id);
	}

	public int insertItem(Item item) {
		item.setId(UUID.randomUUID().toString());
		item.setOrigin("hb");
		entityManager.getTransaction().begin();
		entityManager.persist(item);
		entityManager.getTransaction().commit();
		return 1;
	}

	public int updateItem(String id, Item item) {
		Item i = entityManager.find(Item.class, id);
		i.setContent(item.getContent());
		entityManager.getTransaction().begin();
		entityManager.merge(i);
		entityManager.getTransaction().commit();
		return 1;
	}

	public int deleteItem(String id) {
		Item i = entityManager.find(Item.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(i);
		entityManager.getTransaction().commit();
		return 1;
	}

}
