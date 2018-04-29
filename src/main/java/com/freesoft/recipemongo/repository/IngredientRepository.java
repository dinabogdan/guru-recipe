package com.freesoft.recipemongo.repository;

import com.freesoft.recipemongo.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
