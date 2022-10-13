package kr.inhatc.spring.solstice_shop.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.inhatc.spring.solstice_shop.test.dto.TestDto;

// @Controller
@RestController
public class TestController {

  // @RequestMapping(value = "/", method = RequestMethod.GET)
  @GetMapping("/hello")
  public String hello() {
    return "Hello World!";
  }

  @GetMapping("/test")
  public TestDto test() {
    return new TestDto("나는 문어", 24);
  }
}
