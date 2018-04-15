package com.freesoft.recipe.converter.command2entity;

import com.freesoft.recipe.command.UnitMeasureCommand;
import com.freesoft.recipe.domain.UnitMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;

@Component
public class UnitMeasureCommandToUnitMeasure implements Converter<UnitMeasureCommand, UnitMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitMeasure convert(UnitMeasureCommand unitMeasureCommand) {
        if (unitMeasureCommand == null) {
            return null;
        }

        final UnitMeasure uom = new UnitMeasure();
        uom.setId(unitMeasureCommand.getId());
        uom.setDescription(unitMeasureCommand.getDescription());
        return uom;
    }
}
