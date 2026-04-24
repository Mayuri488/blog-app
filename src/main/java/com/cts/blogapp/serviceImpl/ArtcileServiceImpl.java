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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtcileServiceImpl implements ArticleService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public ArtcileRepository artcileRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    public ArticleDto createArticle(ArticleDto articleDto){
        var entity = modelMapper.map(articleDto,Article.class);
        entity.setCreatedAt(LocalDateTime.now());
        if(articleDto.getCategoryId() != null){
            var category = categoryRepository.findById(articleDto.getCategoryId()).orElseThrow(ResourceNotFoundException::new);
            entity.setCategory(category);
        }
        var savedArticle = artcileRepository.save(entity);
        return modelMapper.map(savedArticle,ArticleDto.class);
    }

    @Override
    public List<ArticleDto> getArticles() {
        List<Article> allArticles = artcileRepository.findAll();
        System.out.println(allArticles);
        List<ArticleDto> articleDtos = allArticles.stream().map(article -> modelMapper.map(article, ArticleDto.class)).collect(Collectors.toList());
        return articleDtos;
    }

    @Override
    public ArticleDto findById(Long articleId) {
        var article = artcileRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        return modelMapper.map(article,ArticleDto.class);
    }


}
