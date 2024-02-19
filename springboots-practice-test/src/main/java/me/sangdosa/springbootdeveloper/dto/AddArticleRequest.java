package me.sangdosa.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sangdosa.springbootdeveloper.domain.Article;

@NoArgsConstructor // 파라미터가 없는 디폴트 생성자를 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 생성
@Getter // 모든 필드 값들에 대한 get 메소드 생성
public class AddArticleRequest {

    private String title; // title 필드값 생성
    private String content; // content 필드값 생성

    public Article toEntity(){ // DTO 형식에서 Entitiy 형식으로 변경
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
