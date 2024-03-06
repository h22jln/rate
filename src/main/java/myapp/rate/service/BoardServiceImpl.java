package myapp.rate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.rate.domain.MapContent;
import myapp.rate.domain.WriteForm;
import myapp.rate.repository.BoardRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService{
    private final RestTemplate restTemplate;
    private final BoardRepository boardRepository;

    private static final String KAKAO_TOKEN = "90dcf471febc0f02ee5885114c1e15f5";

    @Override
    public int contentSave(WriteForm form) {
        return boardRepository.contentSave(form);
    }

    @Override
    public Map<String, String> getCordinate(String address) {
        Map<String, String> result = new HashMap<>();
        String latitude = null;
        String longitude = null;

        String url = "https://dapi.kakao.com/v2/local/search/address.JSON?query="+address;

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK "+KAKAO_TOKEN);

        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        // 통신
        ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if (respEntity != null && respEntity.getBody() != null) {
            String respBody = respEntity.getBody();
            // ObjectMapper 생성
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;

            // JSON 문자열을 JsonNode로 파싱
            try {
                jsonNode = objectMapper.readTree(respBody);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            longitude = jsonNode.get("documents").get(0).get("address").get("x").asText();
            latitude = jsonNode.get("documents").get(0).get("address").get("y").asText();

            result.put("latitude", latitude);
            result.put("longitude", longitude);
        }
        return result;
    }

    @Override
    public List<MapContent> getMapContents() {
        return boardRepository.getMapContents();
    }

    @Override
    public MapContent getMapContent(int idx) {
        return boardRepository.getMapContent(idx);
    }
}
