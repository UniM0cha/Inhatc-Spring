package kr.inhatc.spring.solstice_shop.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/item")
public class ItemController {

    @GetMapping("/new")
    public String itemForm() {
        return "item/itemForm";
    }

}
