package eshop.test.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import eshop.model.Produit;

public class Test {
	
	public static void main(String[] args) {
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("eshop");
	
	EntityManager em = null;
	
	EntityTransaction tx = null;
	
	try {
		em = emf.createEntityManager();
		tx = em.getTransaction();
		List<Produit> myProduits = em.createQuery("select p from Produit p", Produit.class).getResultList();
	//...
	//On oublie pas de fermer EntityManager et EntityManagerFactory
		for (Produit produit : myProduits) {
			System.out.println(produit.getLibelle());
		}
		tx.commit();
	} catch (Exception e) {
		e.printStackTrace();
		if (tx!=null){
			tx.rollback();
		}
	} finally {
		if (em!=null) {
			em.close();
		}
	}
	
	
	emf.close();

	}
	
}
