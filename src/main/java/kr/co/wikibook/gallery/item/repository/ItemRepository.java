package kr.co.wikibook.gallery.item.repository;

import kr.co.wikibook.gallery.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    // 여러 아이디로 상품을 조회
    List<Item> findAllByIdIn(List<Integer> ids);
    // 이름 끝 부분 In은 매개변수의 값이 배열이나 리스트 타입일 때 해당 값을 모두 포함하는 조건을 의미한다.
}