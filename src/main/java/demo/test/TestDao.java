package demo.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class TestDao  {

	@PersistenceContext
	protected EntityManager entityManager;

	public void create(Message data) {
		entityManager.persist(data);
	}


	
}
