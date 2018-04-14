package com.freesoft.recipe.control;

import com.freesoft.recipe.domain.Category;
import com.freesoft.recipe.domain.UnitMeasure;
import com.freesoft.recipe.repository.CategoryRepository;
import com.freesoft.recipe.repository.UnitMeasureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    private CategoryRepository categoryRepository;
    private UnitMeasureRepository unitMeasureRepository;

    protected IndexController(CategoryRepository categoryRepository,
                              UnitMeasureRepository unitMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitMeasureRepository = unitMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {

        Optional<Category> optionalCategory = categoryRepository.findByDescription("American");
        Optional<UnitMeasure> optionalUnitMeasure = unitMeasureRepository.findByDescription("Teaspoon");

        System.out.print("Category ID is: " + optionalCategory.get().getId());
        System.out.println("Unit Measure is: " + optionalUnitMeasure.get().getId());

        return "index";
    }


}
