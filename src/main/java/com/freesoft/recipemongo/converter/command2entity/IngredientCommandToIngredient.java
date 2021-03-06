package com.freesoft.recipemongo.converter.command2entity;

import com.freesoft.recipemongo.command.IngredientCommand;
import com.freesoft.recipemongo.domain.Ingredient;
import com.freesoft.recipemongo.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitMeasureCommandToUnitMeasure uomConverter;

    protected IngredientCommandToIngredient(UnitMeasureCommandToUnitMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if (ingredientCommand == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(String.valueOf(ingredientCommand.getId()));
        if (ingredientCommand.getRecipeId() != null) {
            Recipe recipe = new Recipe();
            recipe.setId(String.valueOf(ingredientCommand.getRecipeId()));
            //ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setUom(uomConverter.convert(ingredientCommand.getUom()));
        return ingredient;
    }
}
