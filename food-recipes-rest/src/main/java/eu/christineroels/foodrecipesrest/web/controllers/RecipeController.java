package eu.christineroels.foodrecipesrest.web.controllers;

import eu.christineroels.foodrecipesrest.services.RecipeService;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/food/recipes/")
@RestController
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(path={"/{recipeId}"},produces = {"application/json"})
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable("recipeId") UUID recipeId){
        return new ResponseEntity<>(recipeService.getRecipeById(recipeId), HttpStatus.OK);
    }
}
