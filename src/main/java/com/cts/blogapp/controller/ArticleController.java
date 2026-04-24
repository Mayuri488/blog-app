package com.cts.blogapp.controller;

import com.cts.blogapp.dto.ArticleDto;
import com.cts.blogapp.serviceImpl.ArtcileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    @Autowired
    public ArtcileServiceImpl articleService;


    @PostMapping
    public ArticleDto createArticle(@RequestBody ArticleDto articleDto){
        return articleService.createArticle(articleDto);
    }

    @GetMapping
    public List<ArticleDto> getAllArticles(){
       return articleService.getArticles();
    }

    @GetMapping("/{articleId}")
    public ArticleDto getArticleById(@PathVariable("articleId") Long articleId){
        return articleService.findById(articleId);
    }
}
