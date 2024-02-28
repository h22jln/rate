package myapp.rate.service;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {

    // 비밀번호를 BCrypt로 해싱하는 메소드
    public static String hashPassword(String password) {
        // 비밀번호를 BCrypt 해시로 변환
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // 해시된 비밀번호를 검증하는 메소드
    public static boolean verifyPassword(String hashedPassword, String password) {
        // 해시된 비밀번호와 입력된 비밀번호를 비교
        return BCrypt.checkpw(password, hashedPassword);
    }
}


