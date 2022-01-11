package jpabook.jpashop;

import jpabook.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            Order order = em.find(Order.class, 1L);

            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
