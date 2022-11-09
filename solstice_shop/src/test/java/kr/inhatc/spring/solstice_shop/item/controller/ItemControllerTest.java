package kr.inhatc.spring.solstice_shop.item.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("상품 등록 페이지 권한 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void itemFormTest() throws Exception {
        // /admin/item/new 경로로 get 요청을 보내본다.
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new"))
                // 결과를 print 하고
                .andDo(MockMvcResultHandlers.print())
                // 상태코드 OK(200)을 예상한다.
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("상품 등록 페이지 일반 회원 접근 테스트")
    @WithMockUser(username = "user", roles = "USER")
    public void itemFormNotAdminTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new"))
                .andDo(MockMvcResultHandlers.print())
                // 상태코드 Forbidden(403)을 예상한다.
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
