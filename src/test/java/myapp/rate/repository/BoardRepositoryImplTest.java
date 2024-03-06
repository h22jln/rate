package myapp.rate.repository;

import myapp.rate.domain.MapContent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryImplTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    void getMapComment(){
        List<MapContent> mapContents = boardRepository.getMapContents();
        for (MapContent mapContent : mapContents) {
            System.out.println("mapContent = " + mapContent);
        }
    }
}