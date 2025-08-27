package kr.co.wikibook.gallery.item.repository;

import kr.co.wikibook.gallery.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    // JpaRepository를 상속 받아서 기본적인 crud 메서드를 사용가능.
    // JpaRepository<Item, Integer> 이경우는 item 엔티티로 item 테이블에 대응한다.
    // Integer는 pk의 자료형 보통 이런 형식으로 JpaRepository를 구현한다.

    // 여러 아이디로 상품을 조회
    List<Item> findAllByIdIn(List<Integer> ids);
    // 이름 끝 부분 In은 매개변수의 값이 배열이나 리스트 타입일 때 해당 값을 모두 포함하는 조건을 의미한다.
    // 간단히 말해 포함된 값은 전부 찾아본다.
}