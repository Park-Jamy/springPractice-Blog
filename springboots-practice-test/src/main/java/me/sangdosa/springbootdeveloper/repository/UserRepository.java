package me.sangdosa.springbootdeveloper.repository;

import me.sangdosa.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // email로 사용자 정보를 가지고 옴 --> JPA 는 메서드 규칙에 맞춰 메서드를 선언 하면 이름을 분석해 자동으로 쿼리 생성
}
