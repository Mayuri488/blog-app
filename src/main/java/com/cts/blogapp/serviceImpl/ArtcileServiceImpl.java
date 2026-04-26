package com.cts.blogapp.serviceImpl;

import com.cts.blogapp.dto.ArticleDto;
import com.cts.blogapp.entity.Article;
import com.cts.blogapp.exceptiion.ResourceNotFoundException;
import com.cts.blogapp.repositories.ArtcileRepository;
import com.cts.blogapp.repositories.CategoryRepository;
import com.cts.blogapp.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtcileServiceImpl implements ArticleService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public ArtcileRepository articleRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    public ArticleDto createArticle(ArticleDto articleDto){

        // ⭐ CRITICAL FIX
        articleDto.setId(null);

        var entity = modelMapper.map(articleDto, Article.class);
        entity.setCreatedAt(LocalDateTime.now());

        if(articleDto.getCategoryId() != null){
            var category = categoryRepository.findById(articleDto.getCategoryId())
                    .orElseThrow(ResourceNotFoundException::new);
            entity.setCategory(category);
        }

        var article = articleRepository.save(entity);
        System.out.println(article);
        return modelMapper.map(article, ArticleDto.class);
    }

    @Override
    public List<ArticleDto> getArticles() {
        List<Article> allArticles = articleRepository.findAll();
        System.out.println(allArticles);
        List<ArticleDto> articleDtos = allArticles.stream().map(article -> modelMapper.map(article, ArticleDto.class)).collect(Collectors.toList());
        return articleDtos;
    }

    @Override
    public ArticleDto findById(Long articleId) {
        var article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);

        return modelMapper.map(article,ArticleDto.class);
    }

    @Override
    public void deleteArticle(Long articleId) {
        var article = articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article Not Found"));
        articleRepository.delete(article);
    }

}
