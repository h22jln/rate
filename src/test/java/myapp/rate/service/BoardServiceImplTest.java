package myapp.rate.service;

import myapp.rate.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class BoardServiceImplTest {
    @Autowired
    BoardServiceImpl boardService;
    @Test
    @DisplayName("주소검색")
    public void findAddress(){
        Map<String, String> cordinate = boardService.getCordinate("서울시 동대문구 장한로 26가길 29");
        System.out.println("cordinate.toString() = " + cordinate.toString());

    }
}