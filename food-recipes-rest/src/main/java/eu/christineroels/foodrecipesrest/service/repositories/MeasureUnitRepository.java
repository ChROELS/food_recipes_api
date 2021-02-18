package eu.christineroels.foodrecipesrest.service.repositories;

import eu.christineroels.foodrecipesrest.domain.MeasureUnit;
import eu.christineroels.foodrecipesrest.domain.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface MeasureUnitRepository extends JpaRepository<MeasureUnit, UUID> {



}
