package myapp.rate.service;

import myapp.rate.domain.MapContent;
import myapp.rate.domain.WriteForm;

import java.util.List;
import java.util.Map;

public interface BoardService {
    /**
     * 게시글 저장
     * @param form
     * @return
     */
    int contentSave(WriteForm form);

    /**
     * 좌표 변환
     * @param address
     * @return
     */
    Map<String, String> getCordinate(String address);

    /**
     * 지도에 뿌릴 게시글 가져오기
     * @return
     */
    List<MapContent> getMapContents();

    /**
     * 게시글 상세 가져오기
     * @param idx
     * @return
     */
    MapContent getMapContent(int idx);

}
