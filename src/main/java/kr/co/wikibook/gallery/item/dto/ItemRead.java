package kr.co.wikibook.gallery.item.dto;

import lombok.Builder;
import lombok.Getter;

// Entity를 직접 사용하지 않고 Dto를 왜 사용하는가
// 엔티티 구조변경시 API 스펙을 변경해야함, 이는 불필요한 추가작업을 야기한다.
// 필요한 데이터만 전송하기 어렵다. 엔티티를 직접 반환하면 모든 데이터를 반환해야한다. 이는 트래픽 증가와 불필요한 성능을 요구한다.
// 보안 문제도 존재한다. 엔티티는 테이블 구조를 그대로 노출시는것이므로 이는 중대한 보안 결점이 된다.

@Getter
@Builder
public class ItemRead {

    private Integer id;
    private String name;
    private String imgPath;
    private Integer price;
    private Integer discountPer;
}