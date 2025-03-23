package kr.co.wikibook.gallery.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

//create table members (
//    id       int auto_increment comment '아이디' primary key,
//    name     varchar(50)                          not null comment '회원명',
//    login_id varchar(50)                          not null comment '로그인 아이디',
//    login_pw varchar(100)                         not null comment '로그인 패스워드',
//    created  datetime default current_timestamp() not null comment '생성 일시',
//    constraint members_uk unique (login_id)
//) comment '회원';

@Getter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String loginId;
    // 아이디 중복 생성 방지를 위해, 고유 제약 조건(unique)설정

    @Column(length = 64, nullable = false)
    private String loginPw;

    @Column(length = 16, nullable = false)
    private String loginPwSalt;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;
    // 최초 입력 값 후에 수정불가(updatable = false)
    // @CreationTimestamp 애너테이션으로 데이터 삽입 시에 값이 없으면 현재 시각이 들어가게 한다.

    public Member(){
    }

    public Member(String name, String loginId, String loginPw, String loginPwSalt) {
        this.name = name;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.loginPwSalt = loginPwSalt;
    }
}