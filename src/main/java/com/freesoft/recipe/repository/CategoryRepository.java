package com.freesoft.recipe.repository;

import com.freesoft.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Override
    Optional<Category> findById(Long aLong);

    Optional<Category> findByDescription(String description);

}
