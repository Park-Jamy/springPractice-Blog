package me.sangdosa.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.sangdosa.springbootdeveloper.domain.Article;
import me.sangdosa.springbootdeveloper.dto.AddArticleRequest;
import me.sangdosa.springbootdeveloper.dto.UpdateArticleRequest;
import me.sangdosa.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

        private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) { // 리턴 받는 값을 Article 형식으로 정하고, 파라미터 값은 AddArticleRequest으로 지정
        return blogRepository.save(request.toEntity()); // 전달 받은 DTO 파라미터 값을 엔터티 형태로 변경 후 save 기능 진행 -> 저장 후 값 리턴
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional // 트랜젝션이 가능, 수행 하는 것에 대한 명시
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id) // ID 값을 통해 엔터티 형태의 값 호출
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update( request.getTitle(), request.getContent() ); // 호출된 값에 대해서 파라미터 값으로 넘어온 값으로 업데이트 진행

        return article;
    }
}
