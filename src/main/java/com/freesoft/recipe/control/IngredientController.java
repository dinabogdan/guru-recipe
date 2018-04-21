package com.freesoft.recipe.control;

import com.freesoft.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;


    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Get the ingredients");
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(recipeId)));
        return "ingredients-list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient{ingredientId}")
    public String showIngredientById(@PathVariable String recipeId, @PathVariable String ingredientId,
                                     Model model) {
        //model.addAttribute("ingredient", )
        return "show-ingredient";
    }
}
