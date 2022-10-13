package kr.inhatc.spring.solstice_shop.member.dto;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class MemberFormDto {
    private String name;
    private String email;
    private String password;
    private String address;
}
