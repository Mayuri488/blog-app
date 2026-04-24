package com.cts.blogapp.serviceImpl;

import com.cts.blogapp.dto.CategoryDto;
import com.cts.blogapp.entity.Category;
import com.cts.blogapp.repositories.CategoryRepository;
import com.cts.blogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        var entity= modelMapper.map(categoryDto, Category.class);
        Category category = categoryRepository.save(entity);
        return modelMapper.map(category, CategoryDto.class);
    }
}
