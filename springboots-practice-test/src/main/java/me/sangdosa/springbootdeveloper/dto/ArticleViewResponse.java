package me.sangdosa.springbootdeveloper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sangdosa.springbootdeveloper.domain.Article;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Article article) { // 글에 대한 데이터 추출 및 생성 날짜 추가
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }

}
