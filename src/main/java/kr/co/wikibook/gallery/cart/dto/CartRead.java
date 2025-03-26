package kr.co.wikibook.gallery.cart.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartRead {

    private Integer id; // ③
    private Integer itemId; // ④
}