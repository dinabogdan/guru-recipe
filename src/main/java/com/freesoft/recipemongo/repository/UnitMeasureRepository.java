package com.freesoft.recipemongo.repository;

import com.freesoft.recipemongo.domain.UnitMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitMeasureRepository extends CrudRepository<UnitMeasure, String> {

    Optional<UnitMeasure> findByDescription(String description);

}
