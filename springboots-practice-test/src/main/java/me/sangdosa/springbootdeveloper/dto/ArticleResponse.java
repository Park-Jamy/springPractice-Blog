package me.sangdosa.springbootdeveloper.dto;

import lombok.Getter;
import me.sangdosa.springbootdeveloper.domain.Article;

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.content = article.getContent();
        this.title = article.getTitle();
    }
}
