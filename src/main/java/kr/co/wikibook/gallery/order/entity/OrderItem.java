package kr.co.wikibook.gallery.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

//create table order_items (
//    id       int auto_increment comment '아이디' primary key,
//    order_id int                                  not null comment '주문 아이디',
//    item_id  int                                  not null comment '주문 상품 아이디',
//    created  datetime default current_timestamp() not null comment '생성 일시'
//) comment '주문 상품';

@Getter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer orderId;

    @Column(nullable = false)
    private Integer itemId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    public OrderItem() {
    }

    public OrderItem(Integer orderId, Integer itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }
}