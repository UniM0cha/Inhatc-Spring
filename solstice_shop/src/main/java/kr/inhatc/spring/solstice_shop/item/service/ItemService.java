package kr.inhatc.spring.solstice_shop.item.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.inhatc.spring.solstice_shop.item.dto.ItemFormDto;
import kr.inhatc.spring.solstice_shop.item.entity.Item;
import kr.inhatc.spring.solstice_shop.item.entity.ItemImg;
import kr.inhatc.spring.solstice_shop.item.repository.ItemImgRepository;
import kr.inhatc.spring.solstice_shop.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws IOException {

        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            // item은 이미 영속상태에 있으므로 id값이 채워져있다. savedItem을 받아올 필요가 없다.
            itemImg.setItem(item);

            // 대표이미지 여부를 설정
            if (i == 0) {
                itemImg.setRepImgYn("Y");
            } else {
                itemImg.setRepImgYn("N");
            }
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }

}
