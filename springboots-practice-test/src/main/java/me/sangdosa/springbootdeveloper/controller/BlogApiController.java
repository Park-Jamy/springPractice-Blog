package me.sangdosa.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.sangdosa.springbootdeveloper.domain.Article;
import me.sangdosa.springbootdeveloper.dto.AddArticleRequest;
import me.sangdosa.springbootdeveloper.dto.ArticleResponse;
import me.sangdosa.springbootdeveloper.dto.UpdateArticleRequest;
import me.sangdosa.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService; //

    @PostMapping("/api/articles") // post 형식을 통해 URL 호출시 글 생성
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){ // 리턴되는 엔터티를 Article로 정하고 전달 받은 Body 값에 대한 DTO를 AddArticleRequest로 설정
        Article savedArticle = blogService.save(request); // 서비스의 save 기능을 통해 저장 처리 진행 후 Article 형태로 리턴 받음, 파라미터 값은 AddArticleRequest 형식

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle); // 생성이라는 상태에 대해서 정상적으로 처리됐다면 body 값에 리턴 받은 savedArticle을 요청받은 곳으로 리턴
    }

    @GetMapping("/api/articles") // get 형식을 통해 URL 호출시 글 목록 조회
    public ResponseEntity<List<ArticleResponse>> findAllArticles() { // 응답해주는 엔터티를 리스트형태의 ArticleResponse로 턴을 시켜 준다
        List<ArticleResponse> articles = blogService.findAll() // 서비스에서 findAll() 기능을 통해 리스트 형태의 값 추출
                .stream()// 스트림 생성 -> 컬렉션에 있는 데이터를 다루는 다양한 작업을 수행한다는 뜻 *stream()
                .map(ArticleResponse::new) // 컬렉션의 각 요소에 함수를 적용하여 다른 형태로 변환, find해서 가지고 온 엔터티 형식의 값들을 모두 ArticleResponse 형식의 DTO로 변경
                .toList(); // 결과를 리스트 형태로 변경

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}") // Id 값을 명시하여 get 형식을 통해 URL Id 값을 가진 글 내용 전달
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) { // 리턴 엔터티를 ArticleResponse로 설정하고 파라미터 값을 long 형태의 id 값으로 설정한다.
        // 이때 @PathVariable를 작성해주면 URL로 오는 {id} 값을 파라미터 값으로 자동 설정 해준다.
        Article article = blogService.findById(id); // 서비스의 findById(id)을 통해 id 값에 대한 데이터 추출

        return ResponseEntity.ok()
                .body(new ArticleResponse(article)); // 정상적으로 엔터티 값 호출 시 body에 ArticleResponse을 담아 응답한다.
    }

    @DeleteMapping("/api/articles/{id}") // Id 값을 명시하여 delete 형식을 통해 URL Id 값을 가진 글 내용 삭제
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id); // 서비스의 delete(id)을 통해 id 값에 대한 데이터 삭제

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}") // Id 값을 명시하여 put 형식을 통해 URL Id 값을 가진 글 내용 수정
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) { // URL에 명시 되어 있는 {id}값과 body 값을 파라미터로 설정
        Article updateArticle = blogService.update(id, request); // 서비스의 update 기능 진행

        return ResponseEntity.ok()
                .body(updateArticle); // updateArticle형태로 응답
    }
}
