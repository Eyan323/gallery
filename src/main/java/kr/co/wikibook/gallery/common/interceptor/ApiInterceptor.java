package kr.co.wikibook.gallery.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import lombok.RequiredArgsConstructor;
import kr.co.wikibook.gallery.account.helper.AccountHelper;

// 스프링 인터셉터는 클라이언트와 서버가 HTTP 통신을 할 때 중간에 내용을 가로채서 특정 작업을 수행하는 기능.

@Component
@RequiredArgsConstructor
public class ApiInterceptor implements HandlerInterceptor {

    private final AccountHelper accountHelper;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        // 로그인 회원 아이디가 없으면
        // URL 통한 잘못된 접근을 인터셉터로 미리 차단한다.
        if (accountHelper.getMemberId(req) == null) {
            res.setStatus(401);
            // HTTP401: Unauthorized(인증되지 않음)
            return false;
            // 컨트롤러로의 요청 중단
        }

        return true;
    }
}