package com.freesoft.recipemongo.service.implementations;

import com.freesoft.recipemongo.command.RecipeCommand;
import com.freesoft.recipemongo.converter.command2entity.RecipeCommandToRecipe;
import com.freesoft.recipemongo.converter.entity2command.RecipeToRecipeCommand;
import com.freesoft.recipemongo.domain.Recipe;
import com.freesoft.recipemongo.repository.reactive.RecipeReactiveRepo;
import com.freesoft.recipemongo.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RecipeServiceImpl implements RecipeService {

    private final RecipeReactiveRepo recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    protected RecipeServiceImpl(RecipeReactiveRepo recipeRepository,
                                RecipeCommandToRecipe recipeCommandToRecipe,
                                RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Flux<Recipe> getRecipes() {
        log.debug("In the RecipeServiceImpl");
        return recipeRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {
        log.debug("Search recipe by ID");
        return recipeRepository.findById(id);
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String id) {
        return recipeRepository.findById(id)
                .map(recipe -> {
                    RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
                    recipeCommand
                            .getIngredients()
                            .forEach(i -> {
                                i.setRecipeId(recipeCommand.getId());
                            });
                    return recipeCommand;
                });
    }

    @Override
    @Transactional
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand recipeCommand) {
        return recipeRepository
                .save(recipeCommandToRecipe
                        .convert(recipeCommand)
                )
                .map(recipeToRecipeCommand::convert);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        if (null != recipeRepository.findById(String.valueOf(id))) {
            recipeRepository.deleteById(String.valueOf(id));
        }

        return Mono.empty();
    }
}
