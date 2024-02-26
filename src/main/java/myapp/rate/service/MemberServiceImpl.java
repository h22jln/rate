package myapp.rate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.rate.domain.JoinForm;
import myapp.rate.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository;

    @Override
    public void joinProceed(JoinForm form, BindingResult bindingResult) {
        // ID 중복체크
        if(!repository.idCheck(form.getId())){
            bindingResult.reject("idValid", null);
            return;
        }
        // 닉네임 중복체크
        if(!repository.nicknameCheck(form.getNickname())){
            bindingResult.reject("nicknameValid", null);
            return;
        }
        // DB 저장
        int result = repository.joinMember(form);
        if (result != 1) {
            // 500에러 발생
        }
    }
}
