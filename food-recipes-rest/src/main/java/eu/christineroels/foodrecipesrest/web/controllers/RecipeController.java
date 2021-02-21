package eu.christineroels.foodrecipesrest.web.controllers;

import eu.christineroels.foodrecipesrest.services.RecipeService;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@Valid @RequestBody RecipeDto recipeDto){
        RecipeDto savedRecipe = recipeService.saveNewRecipe(recipeDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/food/recipes/new/"+savedRecipe.getRecipeId().toString());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }
}
