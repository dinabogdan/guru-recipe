package com.freesoft.recipemongo.service;

import com.freesoft.recipemongo.command.RecipeCommand;
import com.freesoft.recipemongo.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    RecipeCommand findById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(Long id);
}
