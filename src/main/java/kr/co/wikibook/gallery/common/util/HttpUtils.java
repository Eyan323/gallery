package kr.co.wikibook.gallery.common.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

public class HttpUtils {

    // 세션 입력
    public static void setSession(HttpServletRequest req, String key, Object value) {
        req.getSession().setAttribute(key, value);
        // 세션 생성 메서드, 매개변수로 HTTP 요청객체, 세션키, 세션의 값을 받는다.
        // 로그인에 성공한 사용자의 정보를 저장할때 사용한다.
    }

    // 세션 값 조회
    public static Object getSessionValue(HttpServletRequest req, String key) {
        return req.getSession().getAttribute(key);
        // 세션의 특정 값을 조회하는 메서드
        // 로그인 사용자 회원 아이디를 조회할 목적으로 사용
    }

    // 세션 삭제
    public static void removeSession(HttpServletRequest req, String key) {
        req.getSession().removeAttribute(key);
        // 세션의 값을 삭제하는 메서드
        // 로그아웃을 요청한 사용자 세션을 삭제하는 목적
    }

    // 쿠키 입력
    public static void setCookie(HttpServletResponse res, String name, String value, int expSeconds) {
        Cookie cookie = new Cookie(name, value); // 이름, 값으로 쿠키 생성
        cookie.setHttpOnly(true); // HttpOnly 참으로 설정하여 해당 쿠키는 서버에서만 접근가능
        cookie.setPath("/");

        if (expSeconds > 0) {
            cookie.setMaxAge(expSeconds);
            // 매개변수로 받은 유효 시간의 값이 0 이하면 유효 시간을 지정하지 않음.
            // 이 방법으로 웹 브라우저가 종료시 쿠키도 삭제
        }

        res.addCookie(cookie);
    }

    // 쿠키 값 조회
    public static String getCookieValue(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    // 쿠키 삭제
    public static void removeCookie(HttpServletResponse res, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

    // 토큰 조회
    public static String getBearerToken(HttpServletRequest req) {
        String authorization = req.getHeader("Authorization");

        if (authorization != null) {
            return authorization.replace("Bearer ", "").trim(); // Bearer 값 조회
        }

        return null;
    }
}