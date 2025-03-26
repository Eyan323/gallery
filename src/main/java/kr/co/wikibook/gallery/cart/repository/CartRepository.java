package kr.co.wikibook.gallery.cart.repository;

import kr.co.wikibook.gallery.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    // 장바구니 목록 조회(특정 회원)
    List<Cart> findAllByMemberId(Integer memberId);

    // 장바구니 정보 조회(특정 회원 및 특정 상품)
    Optional<Cart> findByMemberIdAndItemId(Integer memberId, Integer itemId);
    // 조회 데이터가 없을수 있기 때문에 Optional 리턴타입을 지정
    // Optional 이란? Java8부터 값이 없는 경우를 표현하기 위한 용도로 사용된다.
    // 이를 사용하여 치명적인 오류인 NPE(NullPointerException)을 방지할수 있다.

    // 장바구니 삭제(특정 회원)
    void deleteByMemberId(Integer memberId);

    // 장바구니 삭제(특정 회원 및 특정 상품)
    void deleteByMemberIdAndItemId(Integer memberId, Integer itemId);
}