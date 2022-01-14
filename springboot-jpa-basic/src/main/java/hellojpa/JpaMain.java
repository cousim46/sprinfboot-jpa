package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
           Movie movie = new Movie();
           movie.setDirector("aaa");
           movie.setActor("bbb");
           movie.setName("바람");
           movie.setPrice(10000);
           em.persist(movie);

           em.flush();
           em.clear();
            Movie movie1 = em.find(Movie.class, movie.getId());
            System.out.println("movie1 = " + movie1);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            ;
        } finally {

            em.close();
        }
        emf.close();


    }
}
