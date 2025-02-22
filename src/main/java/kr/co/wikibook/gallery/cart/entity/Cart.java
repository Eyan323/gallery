package kr.co.wikibook.gallery.cart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

import kr.co.wikibook.gallery.cart.dto.CartRead; // ①

//create table carts (
//    id        int auto_increment comment '아이디' primary key,
//    member_id int                                  not null comment '회원 아이디',
//    item_id   int                                  not null comment '상품 아이디',
//    created   datetime default current_timestamp() not null comment '생성 일시'
//) comment '장바구니';

@Getter
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer memberId;

    @Column(nullable = false)
    private Integer itemId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    public Cart() {
    }

    public Cart(Integer memberId, Integer itemId) {
        this.memberId = memberId;
        this.itemId = itemId;
    }

    // 장바구니 조회 DTO로 변환
    public CartRead toRead() { // ②
        return CartRead.builder()
                .id(id)
                .itemId(itemId)
                .build();
    }
}