package kr.co.wikibook.gallery.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Base64;

public class HashUtils {

    // 솔트 생성
    public static String generateSalt(int size) { // ①
        char[] resultArr = new char[size];
        Random random = new Random();
        // 솔트란? 단순히 데이터를 해싱하면 결과값은 같아진다. a1234를 동일하게 패스워드로 사용하는 사용자가 3명이라면 3명다 같은 값이 나온다.
        // 이를 방지하기 위해 원본 데이터에 랜덤한 문자열을 추가하는것을 솔트라고 한다.
        // 주로 16바이트 이상의 긴 솔트 문자열을 추가하여 해싱하기 때문에 원본 값을 찾는건 사실상 불가능에 가깝다.

        // 랜덤한 문자열을 만들기 위한 문자열
        String options = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "!@#$%^&*()";

        for (int i = 0; i < resultArr.length; i += 1) {
            resultArr[i] = options.charAt(random.nextInt(options.length()));
        }

        return new String(resultArr);
    }

    // 해시 데이터 생성
    public static String generateHash(String value, String salt) { // ②
        try {
            // SHA-256 알고리즘 사용
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // SHA-256 해시 함수는 어떤 길이의 값을 입력하더라도 256비트의 고정된 결과값을 출력.
            // 일반적으로 입력값이 조금만 변동하여도 출력값이 완전히 달라지기 때문에 출력값을 토대로 입력값을 유추하는 것은 거의 불가능함.

            // 원본 값과 솔트 합치기
            String passwordSalted = value + salt;

            // 문자열 데이터 해싱
            byte[] hashBytes = md.digest(passwordSalted.getBytes(StandardCharsets.UTF_8));

            // 바이트 배열을 Base64로 인코딩해서 반환
            return Base64.getEncoder().encodeToString(hashBytes);
            // Base64 이진 데이터를 텍스트 데이터로 인코딩 할 때 사용하는 방식.
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("해싱 중 오류가 발생했습니다.");
        }
    }
}