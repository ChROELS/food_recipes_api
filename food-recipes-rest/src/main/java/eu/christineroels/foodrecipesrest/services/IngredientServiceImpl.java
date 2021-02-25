package eu.christineroels.foodrecipesrest.services;

import eu.christineroels.foodrecipesrest.domain.Ingredient;
import eu.christineroels.foodrecipesrest.service.repositories.IngredientRepository;
import eu.christineroels.foodrecipesrest.web.mappers.IngredientMapper;
import eu.christineroels.foodrecipesrest.web.models.IngredientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper){
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public void saveIngredient(IngredientDto ingredientDto) {
        Ingredient ingredientToSave = ingredientMapper.DtoToIngredient(ingredientDto);
        ingredientRepository.save(ingredientToSave);
    }

    @Override
    public IngredientDto getIngredientById(UUID ingredientId) {
        Ingredient foundIngredient = ingredientRepository.getOne(ingredientId);
        return ingredientMapper.ingredientToDto(foundIngredient);
    }

    @Override
    public IngredientDto updateIngredient(UUID ingredientId, IngredientDto ingredientDto) {
        if(ingredientRepository.existsById(ingredientId)) {
            Ingredient ingredientToUpdate = ingredientRepository.getOne(ingredientId);
            ingredientRepository.delete(ingredientToUpdate);
            ingredientToUpdate.setName(ingredientDto.getName());
            ingredientToUpdate.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            ingredientToUpdate.setLastUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
            ingredientRepository.save(ingredientToUpdate);
            return ingredientMapper.ingredientToDto(ingredientToUpdate);
        }else{
            return null;
        }

    }

    @Override
    public void deleteIngredient(UUID ingredientId) {
        if(ingredientRepository.existsById(ingredientId)) {
            ingredientRepository.deleteById(ingredientId);
        }
    }

    @Override
    public List<IngredientDto> getAllByName(String name) {
        List<Ingredient> allSorted = ingredientRepository.findAll(Sort.by("name"));
        List<Ingredient> allMatching = new ArrayList<>();
        for (Ingredient ig: allSorted
             ) {
            if(ig.getName().equalsIgnoreCase(name)){
                allMatching.add(ig);
            }
        }
        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        for (Ingredient ign: allMatching
             ) {
            ingredientDtoList.add(ingredientMapper.ingredientToDto(ign));
        }
        return ingredientDtoList;
    }
}
