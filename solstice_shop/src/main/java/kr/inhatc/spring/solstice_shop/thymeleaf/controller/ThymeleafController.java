package kr.inhatc.spring.solstice_shop.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

  @GetMapping("/thymeleaf/ex1")
  public String ex1(Model model) {
    model.addAttribute("data", "아싸뵤");
    return "thymeleaf/ex1";
  }
}
