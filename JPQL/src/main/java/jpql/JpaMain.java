package jpql;

import javax.persistence.*;
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

                Member member = new Member();
                member.setUsername("teamA");
                member.setAge(10);
                member.setTeam(team);
                em.persist(member);



            em.flush();
            em.clear();

            String query= "select m from Member m left join Team t on m.username = t.name ";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();



            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }finally {
            em.clear();
        }
        emf.close();

    }

}
