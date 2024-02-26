package myapp.rate.service;

import myapp.rate.domain.JoinForm;
import org.springframework.validation.BindingResult;

public interface MemberService {
    public void joinProceed(JoinForm form, BindingResult bindingResult);
}
