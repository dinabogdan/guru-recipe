package com.freesoft.recipe.service.implementations;

import com.freesoft.recipe.domain.Recipe;
import com.freesoft.recipe.repository.RecipeRepository;
import com.freesoft.recipe.service.RecipeService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    protected RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipe -> recipes.add(recipe));
        return recipes;
    }
}
