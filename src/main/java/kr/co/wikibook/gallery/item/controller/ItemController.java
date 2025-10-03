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
    // 작업 처리 흐름도는 이렇다. 컨트롤러가 요청 받음 - 서비스(비지니스 로직) - 서비스에서 레포지토리 - 레포지토리에서 엔티티 기반으로 db와 통신
    // 일련의 과정을 거쳐 실행되는게 기본적인 구조라 할수있다.
    // 이떄 서비스 계층에서 dto로 변환해서 데이터를 처리한다. 엔티티로 직접 처리하는건 보안에 심각한 위협이고 리소스 관리면에서도 좋지않다.

    @GetMapping("/api/items")
    public ResponseEntity<?> readAll() {
        List<ItemRead> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
        // 서비스 계층에서 dto로 변환된 item의 목록을 http 200번 신호와 함께 전송한다. 200번은 문제없이 전송함. 의미이다.
    }
}