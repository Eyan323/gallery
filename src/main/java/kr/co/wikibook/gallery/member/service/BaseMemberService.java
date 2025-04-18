package kr.co.wikibook.gallery.member.service;

import kr.co.wikibook.gallery.member.entity.Member;
import kr.co.wikibook.gallery.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import kr.co.wikibook.gallery.common.util.HashUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseMemberService implements MemberService {

    private final MemberRepository memberRepository;

    // 회원 데이터 저장
    @Override
    // @Override 애너테이션은 부모 혹은 인터페이스의 메소드를 정확히 재정의 하는지 컴파일러가 확인하는 애너테이션
    // 반드시 작성해야 하는건 아니지만 작성이 권장된다.
    // 오타나 잘못된 작성을 컴파일 타임에 발견할수 있고, 다른사람이 코드를 읽을때 재정의 메서드라고 분명하게 명시함.
    
    public void save(String name, String loginId, String loginPw) {
        // 솔트 생성
        String loginPwSalt = HashUtils.generateSalt(16);

        // 입력 패스워드에 솔트를 적용
        String loginPwSalted = HashUtils.generateHash(loginPw, loginPwSalt);

        // 회원 데이터 저장
        memberRepository.save(new Member(name, loginId, loginPwSalted, loginPwSalt));
    }

    // 회원 데이터 조회
    @Override
    public Member find(String loginId, String loginPw) {
        // 로그인 아이디로 회원 조회
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);

        // 회원 데이터가 있으면
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            // 솔트 조회
            String loginPwSalt = memberOptional.get().getLoginPwSalt();

            // 입력 패스워드에 솔트를 적용
            String loginPwSalted = HashUtils.generateHash(loginPw, loginPwSalt);

            // 저장된 회원 로그인 패스워드와 일치한다면
            if (member.getLoginPw().equals(loginPwSalted)) {
                return member;
            }
        }
        // 비밀번호는 직접 검증하지 않고 해시값만으로 판단함.

        return null;
    }

    @Override
    public Member find(String loginId) {
        // 회원 데이터가 있으면 해당 값 리턴
        return memberRepository.findByLoginId(loginId).orElse(null);
    }
}