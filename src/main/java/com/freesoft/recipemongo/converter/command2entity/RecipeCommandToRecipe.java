package com.freesoft.recipemongo.converter.command2entity;

import com.freesoft.recipemongo.command.RecipeCommand;
import com.freesoft.recipemongo.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    protected RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter,
                                    IngredientCommandToIngredient ingredientConverter,
                                    NotesCommandToNotes notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(String.valueOf(recipeCommand.getId()));
        recipe.setCookTime(recipe.getCookTime());
        recipe.setPrepTime(recipe.getPrepTime());
        recipe.setDescription(recipe.getDescription());
        recipe.setDifficulty(recipe.getDifficulty());
        recipe.setDirections(recipe.getDirections());
        recipe.setServings(recipe.getServings());
        recipe.setSource(recipe.getSource());
        recipe.setUrl(recipe.getUrl());
        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
            recipeCommand
                    .getCategories()
                    .forEach(categoryCommand -> recipe
                            .getCategories()
                            .add(categoryConverter.convert(categoryCommand))
                    );
        }

        if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
            recipeCommand
                    .getIngredients()
                    .forEach(ingredientCommand -> recipe
                            .getIngredients()
                            .add(ingredientConverter.convert(ingredientCommand))
                    );
        }

        return recipe;
    }
}
