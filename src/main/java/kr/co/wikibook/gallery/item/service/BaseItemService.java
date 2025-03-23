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
    public List<ItemRead> findAll() { // ④
        return itemRepository.findAll().stream().map(Item::toRead).toList();
    }

    // 상품 목록 조회(특정 아이디 리스트로 조회)
    @Override
    public List<ItemRead> findAll(List<Integer> ids) { // ④
        return itemRepository.findAllByIdIn(ids).stream().map(Item::toRead).toList();
    }
}