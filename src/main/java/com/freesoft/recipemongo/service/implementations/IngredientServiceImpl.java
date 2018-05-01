package com.freesoft.recipemongo.service.implementations;

import com.freesoft.recipemongo.command.IngredientCommand;
import com.freesoft.recipemongo.converter.command2entity.IngredientCommandToIngredient;
import com.freesoft.recipemongo.converter.entity2command.IngredientToIngredientCommand;
import com.freesoft.recipemongo.domain.Ingredient;
import com.freesoft.recipemongo.domain.Recipe;
import com.freesoft.recipemongo.repository.RecipeRepository;
import com.freesoft.recipemongo.repository.reactive.RecipeReactiveRepo;
import com.freesoft.recipemongo.repository.reactive.UnitMeasureReactiveRepo;
import com.freesoft.recipemongo.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Component
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientConverter;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeReactiveRepo reactiveRecipeRepository;
    private final UnitMeasureReactiveRepo reactiveUomRepository;
    private final RecipeRepository recipeRepository;

    protected IngredientServiceImpl(final IngredientToIngredientCommand ingredientConverter,
                                    final RecipeRepository recipeRepository,
                                    final IngredientCommandToIngredient ingredientCommandToIngredient,
                                    final RecipeReactiveRepo reactiveRecipeRepository,
                                    final UnitMeasureReactiveRepo reactiveUomRepository) {
        this.ingredientConverter = ingredientConverter;
        this.recipeRepository = recipeRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.reactiveRecipeRepository = reactiveRecipeRepository;
        this.reactiveUomRepository = reactiveUomRepository;
    }

    @Override
    public Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {
        return reactiveRecipeRepository.findById(recipeId)
                .map(recipe -> recipe.getIngredients()
                        .stream()
                        .filter(ingredient -> ingredient.getId().equalsIgnoreCase(ingredientId))
                        .findFirst())
                .filter(Optional::isPresent)
                .map(ingredient -> {
                    IngredientCommand ingredientCommand = ingredientConverter.convert(ingredient.get());
                    return ingredientCommand;
                });
    }

    @Override
    public Mono<IngredientCommand> saveIngredient(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(String.valueOf(ingredientCommand.getRecipeId()));
        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found for id {}", ingredientCommand.getRecipeId());
            return Mono.just(new IngredientCommand());
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
                ingredientFound.setUom(reactiveUomRepository
                        .findById(String.valueOf(ingredientCommand.getUom().getId())).block());
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

            IngredientCommand ingredientCommandSaved = ingredientConverter.convert(savedIngredientOptional.get());
            ingredientCommandSaved.setRecipeId(recipe.getId());
            return Mono.just(ingredientCommandSaved);
        }
    }
}
