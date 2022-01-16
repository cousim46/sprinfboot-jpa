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

            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username,m.age) from Member m ", MemberDTO.class)
                    .getResultList();

            MemberDTO memberDTO = result.get(0);
            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getUsername() = " + memberDTO.getAge());


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
