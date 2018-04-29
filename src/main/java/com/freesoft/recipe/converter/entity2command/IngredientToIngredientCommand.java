package com.freesoft.recipe.converter.entity2command;

import com.freesoft.recipe.command.IngredientCommand;
import com.freesoft.recipe.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitMeasureToUnitMeasureCommand uomConverter;

    protected IngredientToIngredientCommand(UnitMeasureToUnitMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientCommand command = new IngredientCommand();
        command.setId(ingredient.getId());
        if (ingredient.getRecipe() != null) {
            command.setRecipeId(ingredient.getRecipe().getId());
        }
        command.setAmount(ingredient.getAmount());
        command.setDescription(ingredient.getDescription());
        command.setUom(uomConverter.convert(ingredient.getUom()));
        return command;
    }
}
