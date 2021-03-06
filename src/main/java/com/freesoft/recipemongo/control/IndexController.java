package com.freesoft.recipemongo.control;

import com.freesoft.recipemongo.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
