package com.freesoft.recipemongo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class UnitMeasure {

    @Id
    private String id;
    private String description;
}
