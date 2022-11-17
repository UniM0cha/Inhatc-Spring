package kr.inhatc.spring.solstice_shop.item.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import kr.inhatc.spring.solstice_shop.item.constant.ItemSellStatus;
import kr.inhatc.spring.solstice_shop.item.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemFormDto {
    private Long id;

    @NotBlank(message = "상품명은 필수 항목 입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 항목 입니다.")
    private int price;

    @NotNull(message = "가격은 필수 항목 입니다.")
    private int stockNumber;

    private ItemSellStatus itemSellStatus;

    @NotBlank(message = "상품설명은 필수 항목 입니다.")
    private String itemDetail;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itmeImgIdList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item creaItem() {
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }
}
