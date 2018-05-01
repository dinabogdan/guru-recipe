package com.freesoft.recipemongo.service;

import com.freesoft.recipemongo.command.UnitMeasureCommand;
import reactor.core.publisher.Flux;

public interface UomService {

    Flux<UnitMeasureCommand> listAllUoms();
}
