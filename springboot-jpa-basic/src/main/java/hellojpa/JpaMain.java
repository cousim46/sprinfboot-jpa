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
            //비 영속성
            Member member = new Member();
            member.setName("HelloJPA");
            member.setId(100L);

            //영속
            System.out.println("==== BEFORE=====");
            em.persist(member);
            System.out.println("==== AFTER=====");

          //  Member member = em.find(Member.class, 1L);
      /*   List<Member> result =  em.createQuery("select m from Member as m",Member.class)
                 .setFirstResult(1)
                 .setMaxResults(10)
                 .getResultList();
            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }
*/
         transaction.commit();
        }catch(Exception e) {
            transaction.rollback();;
        }finally {

        em.close();
        }
        emf.close();


    }
}
