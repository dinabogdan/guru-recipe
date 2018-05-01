package com.freesoft.recipemongo.repository.reactive;

import com.freesoft.recipemongo.domain.UnitMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UnitMeasureReactiveRepo extends ReactiveMongoRepository<UnitMeasure, String> {
}
