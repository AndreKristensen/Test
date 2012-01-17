package no.ask.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.ask.domain.Customer;
import no.ask.domain.Orders;

public class OrderJPA {

	private final String PERSISTENCE_UNIT = "ask";

	private EntityManagerFactory factory;
	private EntityManager em;

	public OrderJPA() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = factory.createEntityManager();

	}

	public void creatCustomer(Customer c) {
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();

	}

	public void creatOrder(Orders order) {
		em.getTransaction().begin();
		em.persist(order);
		em.getTransaction().commit();
	}

	public List<Orders> getAllOrders() {
		return em.createQuery("select o from Orders o")
				.getResultList();

	}

	public List<Customer> getAllCustomer() {
		return em.createQuery("select c from Customer c").getResultList();
	}
	
	public List<Orders> getAllOrdersFromACustomer(String name) {

		String sql = "select o from Orders o join o.customer c where c.name=:name";

		TypedQuery<Orders> query = em.createQuery(sql, Orders.class);

		query.setParameter("name", name);

		return query.getResultList();

	}

	public boolean updateOrder(Orders old) {

		Orders temp = em.find(Orders.class, old.getId());
		if (temp == null) {
			return false;
		} else {
			em.getTransaction().begin();
			temp.setAddress(old.getAddress());
			em.merge(temp);
			em.getTransaction().commit();
		}

		return true;
	}

	public void close() {
		em.close();
	}
}
