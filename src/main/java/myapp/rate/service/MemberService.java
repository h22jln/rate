package myapp.rate.service;

import myapp.rate.domain.JoinForm;

public interface MemberService {
    public void joinProceed(JoinForm form);
    public boolean idDuplicateCheck(String id);
    public boolean nicknameDuplicateCheck(String nickname);
}
