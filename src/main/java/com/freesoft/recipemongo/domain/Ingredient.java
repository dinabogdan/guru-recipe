package com.freesoft.recipemongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Document
public class Ingredient {

    private String id;

    private String description;
    private BigDecimal amount;
    private Recipe recipe;
    private UnitMeasure uom;

    public Ingredient(String description, BigDecimal amount, UnitMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient() {

    }

}
