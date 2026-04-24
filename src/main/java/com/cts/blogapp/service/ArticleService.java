package com.cts.blogapp.service;

import com.cts.blogapp.dto.ArticleDto;
import com.cts.blogapp.entity.Article;

import java.util.List;

public interface ArticleService {
    public ArticleDto createArticle(ArticleDto articleDto);
    public List<ArticleDto> getArticles();
    public ArticleDto findById(Long articleId);

}
