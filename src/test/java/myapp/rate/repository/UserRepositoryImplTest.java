package myapp.rate.repository;

import myapp.rate.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryImplTest {

    @Autowired
    UserRepositoryImpl repository;

    @Test
    @DisplayName("회원가입_단순저장")
    public void join(){
//        JoinForm joinForm = new JoinForm("test", "닉네임", "password", "password", "이름");
//        repository.joinMember(joinForm);
    }

    @Test
    @DisplayName("아이디 중복체크")
    public void idCheck(){
        boolean test = repository.idCheck("test");
        boolean test2 = repository.idCheck("test2");

        Assertions.assertThat(test).isEqualTo(false);
        Assertions.assertThat(test2).isEqualTo(true);
    }

    @Test
    @DisplayName("아이디로 멤버 찾")
    public void findMember(){
        User test = repository.findUser("shj6282");
        System.out.println("test = " + test);

    }
}