package kr.co.wikibook.gallery.item.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

import kr.co.wikibook.gallery.item.dto.ItemRead; // ①

//create table items (
//    id           int auto_increment comment '아이디' primary key,
//    name         varchar(50)                          not null comment '상품 이름',
//    img_path     varchar(100)                         not null comment '상품 사진 경로',
//    price        int                                  not null comment '상품 가격',
//    discount_per int                                  not null comment '상품 할인율',
//    created      datetime default current_timestamp() not null comment '생성 일시'
//) comment '상품';
// 상품 테이블

// Entity는 JPA에서 테이블과 매핑되는 객체를 말한다.

@Getter
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 아이디필드. @ID 애너테이션을 통해 해당 필드가 기본키이며, 테이블의 기본기 칼럼과 매핑된다.
    // GenerationType.IDENTITY 전략은 DB에 기본키 생성을 일임하는것이다.

    @Column(length = 50, nullable = false)
    private String name;
    // 상품의 이름 필드. @Column 애너테이션을 지정하여 이 필드가 테이블 칼럼과 매핑된다.
    // 설정 값의 최대 길이는 50, null을 허용하지 않는다.

    @Column(length = 100, nullable = false)
    private String imgPath;
    // 상품의 사진 경로 필드.
    // 설정 값의 최대 길이는 100, null을 허용하지 않는다.

    @Column(nullable = false)
    private Integer price;
    // 상품의 가격 필드.
    // null을 허용하지 않는다.

    @Column(nullable = false)
    private Integer discountPer;
    // 상품의 할인율 필드.
    // null을 허용하지 않는다.

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    // 상품 조회 DTO로 변환
    public ItemRead toRead() { // ②
        return ItemRead.builder()
                .id(id)
                .name(name)
                .imgPath(imgPath)
                .price(price)
                .discountPer(discountPer)
                .build();
    }
}