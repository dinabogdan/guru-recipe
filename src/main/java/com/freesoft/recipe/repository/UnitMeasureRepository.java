package com.freesoft.recipe.repository;

import com.freesoft.recipe.domain.UnitMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitMeasureRepository extends CrudRepository<UnitMeasure, Long> {

    Optional<UnitMeasure> findByDescription(String description);

}
