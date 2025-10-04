package kr.co.wikibook.gallery.account.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.wikibook.gallery.account.dto.AccountJoinRequest;
import kr.co.wikibook.gallery.account.dto.AccountLoginRequest;

public interface AccountHelper {

    // 회원가입
    void join(AccountJoinRequest joinReq);

    // 로그인
    String login(AccountLoginRequest loginReq, HttpServletRequest req, HttpServletResponse res);
    // HttpServletRequest: 클라이언트가 서버로 보낸 요청을 담고 있는 객체
    // 데이터, HTTP 헤더, 세션, 쿠키 등의 정보가 담겨있다.
    // HttpServletResponse: 서버가 클라이언트에게 응답을 담는 객체
    // 본문(body), HTTP 상태코드, 헤더, 리다이렉트 등

    // 그렇다면 Servlet이란 무엇인가? 서버에서 실행되는 자바 프로그램으로
    // HTTP 요청과 응답을 처리하기 위해 만들어진 객체이다.
    // 직접 처리하는 경우도 있지만, 스프링 프레임워크에서 더 쓰기 쉽게 추상화 하는 경우가 많다.

    // 회원 아이디 조회
    Integer getMemberId(HttpServletRequest req);

    // 로그인 여부 확인
    boolean isLoggedIn(HttpServletRequest req);

    // 로그아웃
    void logout(HttpServletRequest req, HttpServletResponse res);
}