package kr.co.wikibook.gallery.common.util;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//create table blocks (
//    id      int auto_increment comment '아이디' primary key,
//    token   varchar(250)                         not null comment '차단 토큰',
//    created datetime default current_timestamp() not null comment '생성 일시'
//) comment '토큰 차단';

// JWT는 유저를 인증하고 식별하기 위한 토큰(Token) 기반 인증.
// 토큰 자체에 사용자의 권한 정보나 서비스를 사용하기 위한 정보가 포함.
// RESTful과 같은 무상태(Stateless)인 환경에서 사용자 데이터를 주고받을 수 있게 됩니다.
// 토큰은 서버가 아닌 클라이언트에 저장, 서버의 부담을 줄이며, 서버가 재시작되어도 클라이언트 상태 유지가 가능.

public class TokenUtils {

    private static final Key signKey;

    static {
        // 외부에 노출되면 안 되는 중요한 보안 키(32바이트 이상)
        String secretKey = "SECURITY_KEY_2023042319572107_!!";
        byte[] secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        signKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    // 토큰 발급
    public static String generate(String subject, String name, Object value, int expMinutes) { // ③
        // 만료 시간 설정
        Date expTime = new Date();

        // 분(minute)을 밀리초(millisecond)로 변환해 입력
        expTime.setTime(expTime.getTime() + 1000L * 60 * expMinutes);

        // 기본 정보 입력
        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        // 클레임 입력
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(name, value);

        // 토큰 발급
        JwtBuilder builder = Jwts.builder()
                .setHeader(headerMap)
                .setSubject(subject)
                .setExpiration(expTime)
                .addClaims(claims)
                .signWith(signKey, SignatureAlgorithm.HS256);

        return builder.compact();
    }

    public static boolean isValid(String token) {
        // 토큰 값이 있다면
        if (StringUtils.hasLength(token)) {
            try {
                Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token);
                return true;
            } catch (ExpiredJwtException e) { // 만료됨
            } catch (JwtException e) { // 유효하지 않음
            }
        }

        return false;
    }

    // 토큰 값 추출
    public static Map<String, Object> getBody(String token) {
        return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
    }
}