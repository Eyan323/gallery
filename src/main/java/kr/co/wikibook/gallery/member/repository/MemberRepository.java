package kr.co.wikibook.gallery.member.repository;

import kr.co.wikibook.gallery.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    // 아이디와 패스워드로 회원 정보를 조회
    Optional<Member> findByLoginIdAndLoginPw(String loginId, String loginPw);

    // 아이디로 회원 정보 조회
    Optional<Member> findByLoginId(String loginId);
    // 조회된 회원 데이터가 없을수 있기 때문에 리턴 타입을 Optional 지정
    // Optional 사용시 null을 안전하게 처리하고 명시적으로 값이 없음을 나타낼 수 있다.
}