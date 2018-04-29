package com.freesoft.recipemongo.command;

import com.freesoft.recipemongo.command.UnitMeasureCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitMeasureCommand uom;
}
