package myapp.rate.service;

import lombok.AllArgsConstructor;
import myapp.rate.domain.UserWriteContent;
import myapp.rate.repository.MypageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MypageServiceImpl implements MypageService{
    private final MypageRepository mypageRepository;
    @Override
    public List<UserWriteContent> getContentByUserId(String userId) {
        return mypageRepository.getContentByUserId(userId);
    }
}
