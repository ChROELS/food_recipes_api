package eu.christineroels.foodrecipesrest.web.controllers;

import com.sun.net.httpserver.Headers;
import eu.christineroels.foodrecipesrest.services.IngredientService;
import eu.christineroels.foodrecipesrest.web.mappers.IngredientMapper;
import eu.christineroels.foodrecipesrest.web.models.IngredientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/api/food/ingredients")
@RestController
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @GetMapping(path="/{ingredientId}",produces = {"application/json"})
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable("ingredientId") UUID id){
        if(ingredientService.containsIngredientId(id)) {
            return new ResponseEntity<IngredientDto>(ingredientService.getIngredientById(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(path="",consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<List<IngredientDto>> getAll(){
        List<IngredientDto> allSaved = ingredientService.getAll();
        return new ResponseEntity<>(allSaved,HttpStatus.OK);
    }
    @GetMapping(path="/all/{name}",consumes = {"application/json"}, produces = {"application/json"})
    public List<IngredientDto> getAllByName(@Valid @PathVariable("name") String name){
        List<IngredientDto> allSaved = ingredientService.getAllByName(name);
        return allSaved;
    }
    @PostMapping(path = "", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity addIngredient(@Valid @RequestBody IngredientDto ingredientDto){
        ingredientService.saveIngredient(ingredientDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/food/ingredients/"+ingredientDto.getIngredientId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
    @PutMapping(path = "/{ingredientId}",produces = {"application/json"})
    public ResponseEntity<IngredientDto> updateIngredient(@PathVariable("ingredientId") UUID ingredientId,
                                                          @Valid @RequestBody IngredientDto ingredientDto){
        if(ingredientService.containsIngredientId(ingredientId)) {
            IngredientDto updatedIng = ingredientService.updateIngredient(ingredientId, ingredientDto);
            return new ResponseEntity<>(updatedIng, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(path = "")
    public ResponseEntity updateAll(){
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }
    @DeleteMapping(path = "/{ingredientId}")
    public ResponseEntity<String> deleteIngredient(@PathVariable("ingredientId") UUID ingredientId){
        if(ingredientService.containsIngredientId(ingredientId)) {
            ingredientService.deleteIngredient(ingredientId);
            return new ResponseEntity<>("Ingredient deleted.",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Ingredient id invalid or not found.",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping(path = "")
    public ResponseEntity deleteAll(){
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }
    @PatchMapping(path = "")
    public ResponseEntity patchAll(){
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }
    @PatchMapping(path = "/{ingredientId}")
    public ResponseEntity patchAll(@PathVariable("ingredientId") UUID ingredientId){
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }

}
