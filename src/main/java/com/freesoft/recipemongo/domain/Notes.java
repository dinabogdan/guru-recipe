package com.freesoft.recipemongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(exclude = "recipe")
@Document
public class Notes {

    private String id;
    private Recipe recipe;
    private String recipeNotes;

}
