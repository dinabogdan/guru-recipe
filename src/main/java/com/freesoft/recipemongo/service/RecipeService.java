package com.freesoft.recipemongo.service;

import com.freesoft.recipemongo.command.RecipeCommand;
import com.freesoft.recipemongo.domain.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface RecipeService {

    Flux<Recipe> getRecipes();

    Mono<Recipe> findById(String id);

    Mono<RecipeCommand> findCommandById(String id);

    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand);

    Mono<Void> deleteById(Long id);
}
