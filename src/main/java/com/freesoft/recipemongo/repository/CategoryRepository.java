package com.freesoft.recipemongo.repository;

import com.freesoft.recipemongo.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, String> {
    Optional<Category> findById(Long aLong);

    Optional<Category> findByDescription(String description);

}
