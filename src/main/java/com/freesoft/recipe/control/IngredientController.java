package com.freesoft.recipe.control;

import com.freesoft.recipe.command.IngredientCommand;
import com.freesoft.recipe.command.RecipeCommand;
import com.freesoft.recipe.command.UnitMeasureCommand;
import com.freesoft.recipe.service.IngredientService;
import com.freesoft.recipe.service.RecipeService;
import com.freesoft.recipe.service.UomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UomService uomService;


    public IngredientController(final RecipeService recipeService,
                                final IngredientService ingredientService,
                                final UomService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Get the ingredients");
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(recipeId)));
        return "ingredients-list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredientById(@PathVariable String recipeId, @PathVariable String ingredientId,
                                     Model model) {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "show-ingredient";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String showUpdateForm(@PathVariable String recipeId, @PathVariable String ingredientId,
                                 Model model) {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        model.addAttribute("uomList", uomService.listAllUoms());
        return "ingredient-form";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@PathVariable String recipeId, @ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedIngredient = ingredientService.saveIngredient(ingredientCommand);
        log.debug("saved recipe with id {}", savedIngredient.getRecipeId());
        log.debug("saved ingredient with id {}", savedIngredient.getId());
        return "redirect:/recipe/" + savedIngredient.getRecipeId() + "/ingredient/" + savedIngredient.getId() + "/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String showIngredientForm(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findById(Long.valueOf(recipeId));
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);
        ingredientCommand.setUom(new UnitMeasureCommand());
        model.addAttribute("uomList", uomService.listAllUoms());
        return "ingredient-form";
    }
}
