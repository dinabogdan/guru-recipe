package com.freesoft.recipe.service.implementations;

import com.freesoft.recipe.command.IngredientCommand;
import com.freesoft.recipe.converter.entity2command.IngredientToIngredientCommand;
import com.freesoft.recipe.domain.Recipe;
import com.freesoft.recipe.repository.RecipeRepository;
import com.freesoft.recipe.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Component
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientConverter;
    private final RecipeRepository recipeRepository;

    private IngredientServiceImpl(final IngredientToIngredientCommand ingredientConverter,
                                  final RecipeRepository recipeRepository) {
        this.ingredientConverter = ingredientConverter;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            log.error("recipe id not found {}", recipeId);
        }

        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional = recipe
                .getIngredients()
                .stream()
                .filter(i -> i.getId().equals(ingredientId))
                .map(ingredient -> ingredientConverter.convert(ingredient))
                .findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            log.error("ingredient id not found {}", ingredientId);
        }

        return ingredientCommandOptional.get();
    }
}
