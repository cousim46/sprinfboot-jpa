package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            Address address = new Address("city", "street", "zipcode");
            Member member1 = new Member();
            member1.setUsername("hello");
            member1.setHomeAddress(address);
            em.persist(member1);

            Address newAddress = new Address("newCity", address.getStreet(), address.getZipcode());
            member1.setHomeAddress(newAddress);



            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            ;
        } finally {

            em.close();
        }
        emf.close();


    }
   /* private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);
        Team team = member.getTeam();
        System.out.println("team.getName() = " + team.getName());
    }*/
}
