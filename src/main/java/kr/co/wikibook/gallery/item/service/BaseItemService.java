package kr.co.wikibook.gallery.item.service;

import kr.co.wikibook.gallery.item.entity.Item;
import kr.co.wikibook.gallery.item.dto.ItemRead;
import kr.co.wikibook.gallery.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseItemService implements ItemService {

    // 서비스에서 인터페이스와 클래스를 구분하는 이유는, 객체간 의존성을 약하게 하고 느슨한 결합성을 유지하기 위해서이다.

    private final ItemRepository itemRepository;

    // 전체 상품 목록 조회
    @Override
    public List<ItemRead> findAll() {
        return itemRepository.findAll().stream().map(Item::toRead).toList();
        // stream: 기존의 배열에 사용하는 for, foreach 같은 반복문은 하나씩 핸들링 하는 방식이기에 로직이 복잡할수록 코드양이 많아진다.
        // 이에 자바8부터 지원하는 간단하게 람다식으로 코드를 표현하기 위한 방법
        // 하나의 작업을 둘 이상 작업으로 잘게 쪼개어 동시처리, 스레드를 이용하여 많은 요소를 빠르게 처리한다.

    }

    // 상품 목록 조회(특정 아이디 리스트로 조회)
    @Override
    public List<ItemRead> findAll(List<Integer> ids) {
        return itemRepository.findAllByIdIn(ids).stream().map(Item::toRead).toList();
    }
}