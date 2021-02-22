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
    @PostMapping(path ={"/new"}, produces = {"application/json"})
    public ResponseEntity<RecipeDto> createRecipe(@Valid @RequestBody RecipeDto recipeDto){
        RecipeDto savedRecipe = recipeService.saveNewRecipe(recipeDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Recipe Created", "Recipe has been created");
        return new ResponseEntity<>(savedRecipe,headers,HttpStatus.CREATED);
    }
    @DeleteMapping(path={"/delete/{recipeId}"},produces = {"application/json"})
    public ResponseEntity<RecipeDto> deleteRecipe(@PathVariable("recipeId")UUID recipeId){
        RecipeDto recipeToDelete = recipeService.getRecipeById(recipeId);
        HttpHeaders headers = new HttpHeaders();
        if(recipeService.containsRecipe(recipeToDelete)){
            recipeService.deleteRecipe(recipeId);
            headers.add("ResponseOK","Recipe has been deleted");
            //Returns an http status OK and a response body as string to inform the user
            return new ResponseEntity<>(recipeToDelete,headers,HttpStatus.OK);
        }
        //Returns an http status OK and a response body as string to inform the user
        headers.add("ResponseNotOK","Recipe does not exist");
        return new ResponseEntity<>(recipeToDelete,headers,HttpStatus.BAD_REQUEST);
    }
}
