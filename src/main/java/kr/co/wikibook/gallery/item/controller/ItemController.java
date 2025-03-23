package kr.co.wikibook.gallery.item.controller;

import kr.co.wikibook.gallery.item.dto.ItemRead;
import kr.co.wikibook.gallery.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1") // 모든 HTTP 요청을 매핑하기 위한 애너테이션, 경로값은 버전을 의미하는 v1이다.
@RequiredArgsConstructor // 스프링 컨테이너가 관리하는 빈에게 간편하게 의존성 주입(생성자)를 해주는 애너테이션.
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/items")
    public ResponseEntity<?> readAll() {
        List<ItemRead> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}