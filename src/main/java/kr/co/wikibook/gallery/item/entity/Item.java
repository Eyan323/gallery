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


@Getter
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String imgPath;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer discountPer;

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