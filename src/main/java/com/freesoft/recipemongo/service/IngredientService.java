package com.freesoft.recipemongo.service;


import com.freesoft.recipemongo.command.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredient(IngredientCommand ingredientCommand);

}
