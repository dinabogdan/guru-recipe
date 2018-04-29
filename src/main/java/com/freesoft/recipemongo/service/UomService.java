package com.freesoft.recipemongo.service;

import com.freesoft.recipemongo.command.UnitMeasureCommand;

import java.util.Set;

public interface UomService {

    Set<UnitMeasureCommand> listAllUoms();
}
