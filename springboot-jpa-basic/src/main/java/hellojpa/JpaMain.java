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
           Team team = new Team();
           team.setName("teamA");

           em.persist(team);
           Member member1 = new Member();
           member1.setUsername("member1");
           member1.setTeam(team);
           em.persist(member1);

           em.flush();
           em.clear();

            //Member m = em.find(Member.class, member1.getId());
            List<Member> members = em.createQuery("select m from Member m", Member.class)
                    .getResultList();


            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            ;
        } finally {

            em.close();
        }
        emf.close();


    }
    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);
        Team team = member.getTeam();
        System.out.println("team.getName() = " + team.getName());
    }
}
