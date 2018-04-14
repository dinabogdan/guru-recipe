package com.freesoft.recipe.control;

import com.freesoft.recipe.domain.Category;
import com.freesoft.recipe.domain.UnitMeasure;
import com.freesoft.recipe.repository.CategoryRepository;
import com.freesoft.recipe.repository.UnitMeasureRepository;
import com.freesoft.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {


    private final RecipeService recipeService;

    protected IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Here it's index!");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }


}
