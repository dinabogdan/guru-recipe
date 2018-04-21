package com.freesoft.recipe.service;

import com.freesoft.recipe.command.IngredientCommand;

public interface IngredientService {
    
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
