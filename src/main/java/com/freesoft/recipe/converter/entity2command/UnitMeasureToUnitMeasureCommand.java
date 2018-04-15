package com.freesoft.recipe.converter.entity2command;

import com.freesoft.recipe.command.CategoryCommand;
import com.freesoft.recipe.command.UnitMeasureCommand;
import com.freesoft.recipe.domain.Category;
import com.freesoft.recipe.domain.UnitMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitMeasureToUnitMeasureCommand implements Converter<UnitMeasure, UnitMeasureCommand> {

    @Nullable
    @Synchronized
    @Override
    public UnitMeasureCommand convert(UnitMeasure unitMeasure) {
        if (unitMeasure == null) {
            return null;
        }

        final UnitMeasureCommand unitMeasureCommand = new UnitMeasureCommand();
        unitMeasureCommand.setId(unitMeasure.getId());
        unitMeasureCommand.setDescription(unitMeasure.getDescription());
        return unitMeasureCommand;
    }
}
