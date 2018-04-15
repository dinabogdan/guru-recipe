package com.freesoft.recipe.service;

import com.freesoft.recipe.command.RecipeCommand;
import com.freesoft.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    RecipeCommand findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
