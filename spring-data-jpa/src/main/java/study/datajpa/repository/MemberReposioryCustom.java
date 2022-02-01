package study.datajpa.repository;

import study.datajpa.entity.Member;

import java.util.List;

public interface MemberReposioryCustom {
    List<Member> findMemberCustom();
}
