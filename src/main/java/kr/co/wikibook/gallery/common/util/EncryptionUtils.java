package kr.co.wikibook.gallery.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtils {

    private static final String ALGORITHM = "AES"; // 대칭키 알고리즘 사용
    private static final String SECRET_KEY = "a72ha@61@16213iu"; // 대칭키에 사용되는 비밀키 실제 서비스시 보안에 매우 주의해야함

    public static String encrypt(String value) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            // 바이트 배열로 가져와, AES로 암호화 객체 생성
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Cipher 인스턴스 생성
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 암호화 모드 초기화, 사용할 키 설정
            byte[] encryptedValue = cipher.doFinal(value.getBytes());
            // 암호화 수행후, 바이트 배열로 반환
            return Base64.getEncoder().encodeToString(encryptedValue);
            // 암호화 바이트 배열을 문자열로 반환, 이 작업이 없으면 깨진 문자가 출력됨
        } catch (Exception ex) {
            throw new RuntimeException("데이터 암호화 중 오류가 발생했습니다.");
        }
    }

    public static String decrypt(String value) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 복호화 모드로 초기화
            byte[] decryptedValue = Base64.getDecoder().decode(value);
            // 문자열을 디코딩해서 바이트 배열로 복원
            return new String(cipher.doFinal(decryptedValue));
            // 복호화 수행후, 문자열로 변환 후 반환
        } catch (Exception ex) {
            throw new RuntimeException("데이터 복호화 중 오류가 발생했습니다.");
        }
    }
}