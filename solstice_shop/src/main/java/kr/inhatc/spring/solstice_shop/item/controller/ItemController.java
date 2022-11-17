package kr.inhatc.spring.solstice_shop.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.inhatc.spring.solstice_shop.item.dto.ItemFormDto;

@Controller
public class ItemController {

    @GetMapping("/admin/item/new")
    public String itemForm(Model model) {
        // 빈 dto를 날림
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }

}
