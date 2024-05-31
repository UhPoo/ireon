package com.uhpoo.ireon.domain.member.repository;

import com.uhpoo.ireon.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> getMemberByNickname(String nickname);
}
