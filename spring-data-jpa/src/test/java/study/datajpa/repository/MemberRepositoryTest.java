package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void testMember() throws Exception {
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD() throws Exception {

        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        Assertions.assertThat(findMember1).isEqualTo(member1);
        Assertions.assertThat(findMember2).isEqualTo(member2);
        // ????????????
        //    findMember1.setUsername("update!!!");

        // ????????? ?????? ??????
        List<Member> all = memberRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
        // ????????? ??????
        long count = memberRepository.count();
        Assertions.assertThat(count).isEqualTo(2);

        // ?????? ??????
        memberRepository.delete(member1);
        memberRepository.delete(member2);
        long deleteCount = memberRepository.count();
        Assertions.assertThat(deleteCount).isEqualTo(0);


    }

    @Test
    public void findByUsernameAndAgeGraterThen() throws Exception {
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("AAA", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        Assertions.assertThat(result.get(0).getAge()).isEqualTo(20);
        Assertions.assertThat(result.size()).isEqualTo(1);


    }

    @Test
    public void namedQuery() throws Exception {
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("AAA", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        List<Member> result = memberRepository.findByUsername("AAA");
        Member member = result.get(0);
        Assertions.assertThat(member).isEqualTo(member1);
    }

    @Test
    public void testQuery() throws Exception {
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("AAA", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        List<Member> result = memberRepository.findUser("AAA", 10);
        Member findMember = result.get(0);
        Assertions.assertThat(findMember.getAge()).isEqualTo(10);
        Assertions.assertThat(findMember.getUsername()).isEqualTo("AAA");
    }

    @Test
    public void findUsernameList() throws Exception {
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        List<String> usernameList = memberRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("sName = " + s);
        }
    }

    @Test
    public void findMemberDto() throws Exception {
        Team team = new Team("teamA");
        teamRepository.save(team);
        Member member1 = new Member("AAA", 10);
        member1.setTeam(team);
        memberRepository.save(member1);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void findByNames() throws Exception {
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        List<Member> usernameList = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : usernameList) {
            System.out.println("member = " + member);
        }
    }

    @Test
    public void returnType() throws Exception {
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        /*List<Member> aaa = memberRepository.findListByUsername("AAA");
        for (Member member : aaa) {
            System.out.println("member = " + member);
        }*/
      /*  Member member = memberRepository.findMemberByUsername("AAA");
        System.out.println("member = " + member);*/


    }

    @Test
    public void paging() throws Exception {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));
        memberRepository.save(new Member("member6", 10));
        memberRepository.save(new Member("member7", 10));

        int age = 10;
        int offset = 0;
        int limit = 3;

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);
        page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
        /// Slice<Member> page = memberRepository.findByAge(age,pageRequest);


        //then
        List<Member> content = page.getContent();


        long totalElements = page.getTotalElements();

        Assertions.assertThat(content.size()).isEqualTo(3);
        Assertions.assertThat(page.getTotalElements()).isEqualTo(7);
        Assertions.assertThat(page.getTotalPages()).isEqualTo(3);
        Assertions.assertThat(page.isFirst()).isTrue();
        Assertions.assertThat(page.hasNext()).isTrue();


    }

    @Test
    public void bulkUpdate() throws Exception {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));

        // when
        int resultCount = memberRepository.bulkAgePlus(20);

        // em.clear();
        List<Member> result = memberRepository.findByUsername("member5");
        Member member5 = result.get(0);
        System.out.println("member5 = " + member5);
        Assertions.assertThat(resultCount).isEqualTo(3);

    }

    @Test
    public void findMemberLazy() throws Exception {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);
        em.flush();
        em.clear();
        // when
        // List<Member> members = memberRepository.findAll();
        List<Member> members = memberRepository.findEntityGraphByUsername("member1");
        // List<Member> members = memberRepository.findMemberFetchJoin();
        System.out.println("====================================");

        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member.team = " + member.getTeam().getName());
        }
        // then
    }

    @Test
    public void queryHint() throws Exception {
        //given
        Member member = new Member("member1", 10);
        memberRepository.save(member);
        em.flush();
        em.clear();

        //when
        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2");
        em.flush();
    }

    @Test
    public void lock() throws Exception {
        Member member = new Member("member1", 10);
        memberRepository.save(member);
        em.flush();
        em.clear();

        //when
        List<Member> result = memberRepository.findLockByUsername("member1");

    }

    @Test
    public void callCustom() throws Exception {
        // given
        List<Member> memberCustom = memberRepository.findMemberCustom();

        // when

        // then
    }

    @Test
    public void specBasic() throws Exception {
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member member1 = new Member("m1", 0, teamA);
        Member member2 = new Member("m2", 0, teamA);

        em.persist(member1);
        em.persist(member2);
        em.flush();
        em.clear();

        memberRepository.findAll();
    }

}
