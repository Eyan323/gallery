package kr.co.wikibook.gallery.item.dto;

import lombok.Builder;
import lombok.Getter;

// Entity를 직접 사용하지 않고 Dto를 왜 사용하는가
// 엔티티 구조변경시 API 스펙을 변경해야함, 이는 불필요한 추가작업을 야기한다.
// 필요한 데이터만 전송하기 어렵다. 엔티티를 직접 반환하면 모든 데이터를 반환해야한다. 이는 트래픽 증가와 불필요한 성능을 요구한다.
// 보안 문제도 존재한다. 엔티티는 테이블 구조를 그대로 노출시는것이므로 이는 중대한 보안 결점이 된다.
// 단순하게 데이터를 옮기기 위한 클래스로써 JPA가 관리하지 않는다.
// 비즈니스 로직역시 포함되지 않는다 단순히 컨트롤러 - 서비스 - 프론트엔드(화면)간 데이터 이동을 위해 사용되는 경우가 다수다.

@Getter
@Builder
public class ItemRead {

    private Integer id;
    private String name;
    private String imgPath;
    private Integer price;
    private Integer discountPer;
}