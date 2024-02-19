package me.sangdosa.springbootdeveloper.repository;

import me.sangdosa.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
