package com.freesoft.recipe.service.implementations;

import com.freesoft.recipe.command.UnitMeasureCommand;
import com.freesoft.recipe.converter.entity2command.UnitMeasureToUnitMeasureCommand;
import com.freesoft.recipe.repository.UnitMeasureRepository;
import com.freesoft.recipe.service.UomService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UomServiceImpl implements UomService {

    private final UnitMeasureRepository uomRepository;
    private final UnitMeasureToUnitMeasureCommand uomConverter;

    private UomServiceImpl(final UnitMeasureRepository uomRepository,
                           final UnitMeasureToUnitMeasureCommand uomConverter) {
        this.uomRepository = uomRepository;
        this.uomConverter = uomConverter;
    }

    @Override
    public Set<UnitMeasureCommand> listAllUoms() {
        Set<UnitMeasureCommand> uoms = new HashSet<>();
        uomRepository.findAll()
                .forEach(e -> uoms.add(uomConverter.convert(e)));
        return uoms;
    }
}