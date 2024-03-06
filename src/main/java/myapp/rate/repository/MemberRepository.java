package myapp.rate.repository;

import myapp.rate.domain.JoinForm;
import myapp.rate.domain.Member;
import myapp.rate.domain.WriteForm;

public interface MemberRepository {
    /**
     * DB에 회원 정보 등록
     * @param joinForm
     * @return
     */
    public int joinMember(JoinForm joinForm);

    /**
     * ID 중복체크
     * @param id
     * @return
     */
    public boolean idCheck(String id);/**
     * 닉네임 중복체크
     * @param nickName
     * @return
     */
    public boolean nicknameCheck(String nickName);

    /**
     * 아이디로 멤버 찾기
     * @param id
     * @return
     */
    public Member findMember(String id);


}
