package com.freesoft.recipemongo.repository.reactive;

import com.freesoft.recipemongo.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepo extends ReactiveMongoRepository<Recipe, String> {
}
