package com.freesoft.recipe.service.implementations;

import com.freesoft.recipe.command.RecipeCommand;
import com.freesoft.recipe.converter.command2entity.RecipeCommandToRecipe;
import com.freesoft.recipe.converter.entity2command.RecipeToRecipeCommand;
import com.freesoft.recipe.domain.Recipe;
import com.freesoft.recipe.exception.NotFoundException;
import com.freesoft.recipe.repository.RecipeRepository;
import com.freesoft.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    protected RecipeServiceImpl(RecipeRepository recipeRepository,
                                RecipeCommandToRecipe recipeCommandToRecipe,
                                RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("In the RecipeServiceImpl");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipe -> recipes.add(recipe));
        return recipes;
    }

    @Override
    public RecipeCommand findById(Long id) {
        log.debug("Search recipe by ID");
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (!recipe.isPresent()) {
            throw new NotFoundException("Recipe not found exception");
        }
        return recipeToRecipeCommand.convert(recipe.get());
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Save RecipeID {}", savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long id) {
        if (null != recipeRepository.findById(id)) {
            recipeRepository.deleteById(id);
        }
    }
}