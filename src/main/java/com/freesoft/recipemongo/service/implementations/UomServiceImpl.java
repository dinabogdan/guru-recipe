package com.freesoft.recipemongo.service.implementations;

import com.freesoft.recipemongo.command.UnitMeasureCommand;
import com.freesoft.recipemongo.converter.entity2command.UnitMeasureToUnitMeasureCommand;
import com.freesoft.recipemongo.repository.reactive.UnitMeasureReactiveRepo;
import com.freesoft.recipemongo.service.UomService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class UomServiceImpl implements UomService {

    private final UnitMeasureReactiveRepo uomRepository;
    private final UnitMeasureToUnitMeasureCommand uomConverter;

    private UomServiceImpl(final UnitMeasureReactiveRepo uomRepository,
                           final UnitMeasureToUnitMeasureCommand uomConverter) {
        this.uomRepository = uomRepository;
        this.uomConverter = uomConverter;
    }

    @Override
    public Flux<UnitMeasureCommand> listAllUoms() {
        return uomRepository
                .findAll()
                .map(uomConverter::convert);
    }
}
