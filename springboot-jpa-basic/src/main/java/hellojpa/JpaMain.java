package hellojpa;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
           //CirteriaBuilder 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            em.createQuery(cq).getResultList();


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
