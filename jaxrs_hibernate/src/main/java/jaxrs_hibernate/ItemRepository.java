package jaxrs_hibernate;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ItemRepository {
	
	private StandardServiceRegistry registry;
	private SessionFactory sessionFactory;
	
	{
		registry = new StandardServiceRegistryBuilder().configure().build();
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	public List<Item> selectAllItems() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("select i from Item i", Item.class).list();
		}
	}
	
	public Item selectItem(String id) {
		try (Session session = sessionFactory.openSession()) {
			return session.find(Item.class, id);
		}
	}
	
	public int insertItem(Item item) {
		item.setId(UUID.randomUUID().toString());
		item.setOrigin("hb");
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(item);
			session.getTransaction().commit();
		}
		return 1;
	}
	
	public int updateItem(String id, Item item) {
		try (Session session = sessionFactory.openSession()) {
			Item i = session.find(Item.class, id);
			
			i.setContent(item.getContent());
			
			session.beginTransaction();
			session.update(i);
			session.getTransaction().commit();
		}
		return 1;
	}
	
	public int deleteItem(String id) {
		try (Session session = sessionFactory.openSession()) {
			Item i = session.find(Item.class, id);
			
			session.beginTransaction();
			session.delete(i);
			session.getTransaction().commit();
		}
		return 1;
	}

}
