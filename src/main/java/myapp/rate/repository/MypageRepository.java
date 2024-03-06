package myapp.rate.repository;

import myapp.rate.domain.UserWriteContent;

import java.util.List;

public interface MypageRepository {
    /**
     * DB에서 사용자가 작성한 글을 가져옴
     * @param userId
     * @return
     */
    List<UserWriteContent> getContentByUserId(String userId);
}
