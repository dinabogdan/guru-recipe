package com.freesoft.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitMeasure uom;

    public Ingredient(String description, BigDecimal amount, UnitMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient() {

    }

}
