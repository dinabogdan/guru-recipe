package com.freesoft.recipemongo.control;

import com.freesoft.recipemongo.command.RecipeCommand;
import com.freesoft.recipemongo.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    protected RecipeController(@NotNull final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showRecipe(@PathVariable String id, Model model) {
        log.debug("Search a recipe by Id");
        model.addAttribute("recipe", recipeService.findById(id));
        return "show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe-form";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe-form";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute RecipeCommand command,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> {
                log.error(e.toString());
            });
            return "recipe-form";
        }
        Mono<RecipeCommand> savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.block().getId() + "/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        recipeService.deleteById(new Long(id));
        return "redirect:/";
    }
}
