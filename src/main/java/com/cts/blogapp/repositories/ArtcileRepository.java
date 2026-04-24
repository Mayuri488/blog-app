package com.cts.blogapp.repositories;

import com.cts.blogapp.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtcileRepository extends JpaRepository<Article,Long> {

    @Override
    Optional<Article> findById(Long aLong);
}
