package br.com.lojaonline.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaFactory {

	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	public static EntityManager getEntityManager() {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("loja");
		}
		if(em == null) {
			em = factory.createEntityManager();
		}
		return em;
	}
}
