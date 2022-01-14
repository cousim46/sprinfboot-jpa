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
            Child child1 = new Child();
            Child child2 = new Child();
            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
           /* em.persist(child1);
            em.persist(child2);
*/
            em.flush();
            em.clear();
            Parent parent1 = em.find(Parent.class, parent.getId());
            parent1.getChildren().remove(0);
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
