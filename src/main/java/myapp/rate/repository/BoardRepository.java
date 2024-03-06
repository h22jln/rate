package myapp.rate.repository;

import myapp.rate.domain.MapContent;
import myapp.rate.domain.WriteForm;

import java.util.List;
import java.util.Map;

public interface BoardRepository{
    /**
     * DB에 글 등록
     * @param
     * @return
     */
    public int contentSave(WriteForm writeForm);

    /**
     * 지도에 뿌릴 게시글 DB에서 가져오기
     * @return
     */
    List<MapContent> getMapContents();

    /**
     * 게시글 상세 DB에서 가져오기
     * @param idx
     * @return
     */
    MapContent getMapContent(int idx);
}
