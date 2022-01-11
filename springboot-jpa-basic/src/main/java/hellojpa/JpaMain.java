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
//            Member member = new Member();
//            member.setName("HelloJPA");
//            member.setId(101L);

            //영속
//            System.out.println("==== BEFORE=====");
//            em.persist(member);
//            System.out.println("==== AFTER=====");

//            Member findMember1 = em.find(Member.class, 100L);
//            Member findMember12 = em.find(Member.class, 100L);
            /*System.out.println("findMember.getName() = " + findMember.getName());
            System.out.println("findMember.getId() = " + findMember.getId());
*/
            //  Member member = em.find(Member.class, 1L);
      /*   List<Member> result =  em.createQuery("select m from Member as m",Member.class)
                 .setFirstResult(1)
                 .setMaxResults(10)
                 .getResultList();
            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }
*/
//            Member member = em.find(Member.class, 150L);
//            member.setName("zzzzz");

           /* Member member = new Member(201L, "member200");
            em.persist(member);
            em.flush();
            Member member1 = em.find(Member.class, 201L);

            Member member = em.find(Member.class, 150L);
            member.setName("AAAA");

            em.detach(member);


            Member member = new Member();
            member.setUsername("c");

            em.persist(member);
*/
            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            // member.setTeamId(team.getId());
            em.persist(member);
           // team.getMembers().add(member);
            em.flush();
            em.clear();
            System.out.println("=================================");
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("=================================");
            List<Member> members = findMember.getTeam().getMembers();
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
            // Team findTeam = findMember.getTeam();


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
