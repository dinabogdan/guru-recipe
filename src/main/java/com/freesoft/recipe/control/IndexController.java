package com.freesoft.recipe.control;

import com.freesoft.recipe.domain.Category;
import com.freesoft.recipe.domain.UnitMeasure;
import com.freesoft.recipe.repository.CategoryRepository;
import com.freesoft.recipe.repository.UnitMeasureRepository;
import com.freesoft.recipe.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    private final RecipeService recipeService;

    protected IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        LOGGER.debug("Here it's index!");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }


}
