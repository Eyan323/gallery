package kr.co.wikibook.gallery.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

import kr.co.wikibook.gallery.order.dto.OrderRead;

//create table orders (
//    id          int auto_increment comment '아이디' primary key,
//    member_id   int                                 not null comment '주문자 회원 아이디',
//    name        varchar(50)                         not null comment '주문자명',
//    address     varchar(500)                        not null comment '배송 주소',
//    payment     varchar(10)                         not null comment '결제 수단',
//    card_number varchar(16)                         null comment '카드 번호',
//    amount      bigint                              not null comment '최종 결제 금액',
//    created     datetime default current_timestamp() not null comment '생성 일시'
//) comment '주문';

@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer memberId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String address;

    @Column(length = 10, nullable = false)
    private String payment;

    @Column(length = 50)
    private String cardNumber;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    public Order() {
    }

    public Order(Integer memberId, String name, String address, String payment, String cardNumber, Long amount) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.payment = payment;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    // 주문 조회 DTO로 변환
    public OrderRead toRead() {
        return OrderRead.builder()
                .id(id)
                .name(name)
                .address(address)
                .payment(payment)
                .amount(amount)
                .created(created)
                .build();
    }
}