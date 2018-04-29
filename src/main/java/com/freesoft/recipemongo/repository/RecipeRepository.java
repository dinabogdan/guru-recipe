package com.freesoft.recipemongo.repository;

import com.freesoft.recipemongo.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {

}
