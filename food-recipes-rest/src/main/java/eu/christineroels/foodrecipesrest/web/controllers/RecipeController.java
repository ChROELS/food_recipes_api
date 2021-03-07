package eu.christineroels.foodrecipesrest.web.controllers;

import eu.christineroels.foodrecipesrest.services.RecipeService;
import eu.christineroels.foodrecipesrest.web.models.RecipeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
@Slf4j
@RequestMapping("/api/food/recipes")
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
    @PostMapping(path ={""},consumes = {"application/json"},produces = {"application/json"})
    public ResponseEntity<RecipeDto> createRecipe(@Valid @RequestBody RecipeDto recipeDto){
        RecipeDto savedRecipe = recipeService.saveNewRecipe(recipeDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/food/recipes/"+savedRecipe.getRecipeId());
        return new ResponseEntity<>(savedRecipe,headers,HttpStatus.CREATED);
    }
    @DeleteMapping(path={"/delete/{recipeId}"},produces = {"application/json"})
    public ResponseEntity<RecipeDto> deleteRecipe(@PathVariable("recipeId")UUID recipeId){
        RecipeDto recipeToDelete = recipeService.getRecipeById(recipeId);
        HttpHeaders headers = new HttpHeaders();
        if(recipeService.containsRecipe(recipeToDelete)){
            recipeService.deleteRecipe(recipeId);
            headers.add("Status","200 OK");
            //Returns an http status OK, a json message and a response header
            return new ResponseEntity<>(recipeToDelete,headers,HttpStatus.OK);
        }
        return new ResponseEntity<>(recipeToDelete,HttpStatus.BAD_REQUEST);
    }
    @PutMapping(path={"/{recipeId}"},produces = {"application/json"})
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable("recipeId")UUID recipeId, @Valid @RequestBody RecipeDto recipeDto) {
        HttpHeaders headers = new HttpHeaders();
        RecipeDto updated = recipeService.updateRecipe(recipeId, recipeDto);
        headers.add("Location", "/api/food/recipes/"+updated.getRecipeId());
        return new ResponseEntity<>(updated, headers, HttpStatus.OK);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON;
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public String handleHttpMediaTypeNotSupportedException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON;
    }

}
