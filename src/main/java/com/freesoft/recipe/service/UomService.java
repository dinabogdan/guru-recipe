package com.freesoft.recipe.service;

import com.freesoft.recipe.command.UnitMeasureCommand;

import java.util.Set;

public interface UomService {

    Set<UnitMeasureCommand> listAllUoms();
}
