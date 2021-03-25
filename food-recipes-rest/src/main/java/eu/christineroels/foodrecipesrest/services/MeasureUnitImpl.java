package eu.christineroels.foodrecipesrest.services;

import eu.christineroels.foodrecipesrest.domain.MeasureUnit;
import eu.christineroels.foodrecipesrest.service.repositories.MeasureUnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class MeasureUnitImpl implements MeasureUnitService{
    private MeasureUnitRepository measureUnitRepository;
    @Override
    public List<MeasureUnit> getAll() {
        return measureUnitRepository.findAll();
    }

    @Override
    public void saveMeasureUnit(MeasureUnit measureUnit)throws Exception {
        if(!measureUnitRepository.findAll().contains(measureUnit)) {
            measureUnitRepository.save(measureUnit);
        }
    }

    @Override
    public MeasureUnit getMeasureUntById(UUID id) {
        return measureUnitRepository.findById(id).isPresent()?measureUnitRepository.findById(id).get():null;
    }

    @Override
    public MeasureUnit updateMeasureUnit(UUID id, MeasureUnit measureUnit) {
        MeasureUnit unit = new MeasureUnit();
        Optional<MeasureUnit> toUpdate = measureUnitRepository.findById(id);
        if(toUpdate.isPresent()){
            unit = toUpdate.get();
            unit.setShortSymbol(measureUnit.getShortSymbol());
        }
        measureUnitRepository.deleteById(id);
        return measureUnitRepository.save(unit);
    }

    @Override
    public void deleteMeasureUnit(UUID uuid) {
            measureUnitRepository.deleteById(uuid);
    }
}
