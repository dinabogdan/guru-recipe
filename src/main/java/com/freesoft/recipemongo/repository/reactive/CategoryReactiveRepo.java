package com.freesoft.recipemongo.repository.reactive;

import com.freesoft.recipemongo.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryReactiveRepo extends ReactiveMongoRepository<Category, String> {

    Mono<Category> findByDescription(String description);

}
