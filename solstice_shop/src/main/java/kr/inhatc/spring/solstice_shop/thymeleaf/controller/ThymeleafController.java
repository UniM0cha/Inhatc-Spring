package kr.inhatc.spring.solstice_shop.thymeleaf.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.inhatc.spring.solstice_shop.thymeleaf.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/thymeleaf")
public class ThymeleafController {

  @GetMapping("/ex1")
  public String ex1(Model model) {
    model.addAttribute("data", "아싸뵤");
    return "thymeleaf/ex1";
  }

  @GetMapping("/ex2")
  public String ex2(Model model) {

    ItemDto itemDto = new ItemDto();
    itemDto.setItemDetail("상품 상세 설명");
    itemDto.setItemNm("테스트 상품 1");
    itemDto.setPrice(10000);
    itemDto.setRegTime(LocalDateTime.now());

    model.addAttribute("itemDto", itemDto);

    return "thymeleaf/ex2";
  }

  @GetMapping(value = { "/ex3", "/ex4" })
  public void ex3(Model model) {

    List<ItemDto> itemDtos = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      ItemDto itemDto = new ItemDto();
      itemDto.setItemDetail("상품 상세 설명");
      itemDto.setItemNm("테스트 상품 1");
      itemDto.setPrice(10000);
      itemDto.setRegTime(LocalDateTime.now());

      itemDtos.add(itemDto);
    }

    model.addAttribute("itemDtos", itemDtos);
  }

  @GetMapping("/ex5")
  public String ex5(@RequestParam("param1") String param1, @RequestParam("param2") String param2, Model model) {
    log.info("======>" + param1 + param2);

    model.addAttribute("param1", param1);
    model.addAttribute("param2", param2);

    return "thymeleaf/ex5";
  }

  @GetMapping(value = { "/ex6", "/ex7" })
  public void ex6() {
  }

}
