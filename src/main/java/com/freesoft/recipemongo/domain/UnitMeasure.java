package com.freesoft.recipemongo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UnitMeasure {

    private String id;
    private String description;
}
