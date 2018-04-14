package com.freesoft.recipe.control;

import com.freesoft.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    protected RecipeController(@NotNull final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showRecipe(@PathVariable Long id,
                             Model model) {
        log.debug("Search a recipe by Id");
        model.addAttribute("recipe", recipeService.findById(id));
        return "show";
    }

}
