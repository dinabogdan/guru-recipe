package com.freesoft.recipemongo.service.implementations;

import com.freesoft.recipe.exception.NotFoundException;
import com.freesoft.recipemongo.command.IngredientCommand;
import com.freesoft.recipemongo.converter.command2entity.IngredientCommandToIngredient;
import com.freesoft.recipemongo.converter.entity2command.IngredientToIngredientCommand;
import com.freesoft.recipemongo.domain.Ingredient;
import com.freesoft.recipemongo.domain.Recipe;
import com.freesoft.recipemongo.repository.RecipeRepository;
import com.freesoft.recipemongo.repository.UnitMeasureRepository;
import com.freesoft.recipemongo.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientConverter;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitMeasureRepository uomRepository;

    protected IngredientServiceImpl(final IngredientToIngredientCommand ingredientConverter,
                                    final RecipeRepository recipeRepository,
                                    final IngredientCommandToIngredient ingredientCommandToIngredient,
                                    final UnitMeasureRepository uomRepository) {
        this.ingredientConverter = ingredientConverter;
        this.recipeRepository = recipeRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.uomRepository = uomRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(String.valueOf(recipeId));
        if (!recipeOptional.isPresent()) {
            log.error("recipe id not found {}", recipeId);
            throw new NotFoundException("Recipe with recipeID " + recipeId + " not found");
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
            throw new NotFoundException("Ingredient with ingredientId " + ingredientId + " not found");
        }

        IngredientCommand ingredientCommand = ingredientCommandOptional.get();
        ingredientCommand.setRecipeId(recipe.getId());
        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredient(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(String.valueOf(ingredientCommand.getRecipeId()));
        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found for id {}", ingredientCommand.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();
            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setAmount(ingredientCommand.getAmount());
                ingredientFound.setUom(uomRepository
                        .findById(String.valueOf(ingredientCommand.getUom().getId()))
                        .orElseThrow(() -> new NotFoundException("UnitOfMeasure not found")));
            } else {
                Ingredient ingredientToSave = ingredientCommandToIngredient.convert(ingredientCommand);
                //ingredientToSave.setRecipe(recipe);
                recipe.addIngredient(ingredientToSave);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);
            Optional<Ingredient> savedIngredientOptional = savedRecipe
                    .getIngredients()
                    .stream()
                    .filter(i -> i.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if (!savedIngredientOptional.isPresent()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(i -> i.getDescription().equals(ingredientCommand.getDescription()))
                        .findFirst();
            }
            return ingredientConverter.convert(savedIngredientOptional.get());
        }
    }
}
