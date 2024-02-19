package me.sangdosa.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.sangdosa.springbootdeveloper.domain.Article;
import me.sangdosa.springbootdeveloper.dto.ArticleListViewResponse;
import me.sangdosa.springbootdeveloper.dto.ArticleViewResponse;
import me.sangdosa.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> article = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles", article);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article") //Get 형식으로 들어온 URL확인
    public String newArticle(@RequestParam(required = false) Long id, Model model){ // 파라미터 값으로 ID 값을 선택적으로 받을 수 있음
        if(id == null) { // ID 값이 없을 경우
            model.addAttribute("article", new ArticleViewResponse()); // 기본 DTO 생성
        }
        else { // ID 값이 있을 경우
            Article article = blogService.findById(id); // ID 값에 맞는 Article 데이터 추출
            model.addAttribute("article", new ArticleViewResponse(article)); // Article을 값으로 파라미터 값으로 가지는 DTO 생성
        }

        return "newArticle"; // resource/templates 폴더 내에 "newArticle"이름의 파일을 찾아 실행
    }
}
