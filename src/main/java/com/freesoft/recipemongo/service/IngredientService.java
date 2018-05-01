package com.freesoft.recipemongo.service;


import com.freesoft.recipemongo.command.IngredientCommand;
import reactor.core.publisher.Mono;

public interface IngredientService {

    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientCommand> saveIngredient(IngredientCommand ingredientCommand);

}
