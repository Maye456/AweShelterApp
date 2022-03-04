package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.AnimalDataService;
import com.gcu.model.AnimalEntity;
import com.gcu.model.AnimalModel;

@Service
public class AnimalBusinessService implements IAnimalBusinessService 
{
	@Autowired
	AnimalDataService service;
	
	@Override
	public AnimalModel getById(int Id) {
		AnimalEntity result = service.getById(Id);
        AnimalModel animal = new AnimalModel(
                result.getId(),
                result.getName(),
                result.getAge(),
                result.getWeight(),
                result.getBreed(),
                result.getDescription()
                );
        return animal;
	}

	@Override
	public List<AnimalModel> getAnimals() {
		// Fetch the list of entities
        List<AnimalEntity> animalE = service.getAnimals();
        // Create an empty list of orders
        List<AnimalModel> animals = new ArrayList<AnimalModel>();
        // For each entity in the list, create a new order and add to orders
        for (AnimalEntity entity: animalE)
        {
            animals.add(
                    // Translate from Entity to Order Model
                    new AnimalModel(
                    		entity.getId(),
                    		entity.getName(),
                    		entity.getAge(),
                    		entity.getWeight(),
                    		entity.getBreed(),
                    		entity.getDescription())
                    );
        }
        return animals;
	}

	@Override
	public List<AnimalModel> searchAnimals(String searchTerm) {
		// Fetch the list of entities
        List<AnimalEntity> animalE = service.searchAnimal(searchTerm);
        // Create an empty list of orders
        List<AnimalModel> animals = new ArrayList<AnimalModel>();
        // For each entity in the list, create a new order and add to orders
        for (AnimalEntity entity: animalE)
        {
            animals.add(
                    // Translate from Entity to Order Model
                    new AnimalModel(
                    		entity.getId(),
                    		entity.getName(),
                    		entity.getAge(),
                    		entity.getWeight(),
                    		entity.getBreed(),
                    		entity.getDescription())
                    );
        }
        return animals;
	}

	@Override
	public int addOne(AnimalModel newAnimal) {
		AnimalEntity entity = new AnimalEntity(
				newAnimal.getId(),
				newAnimal.getName(),
				newAnimal.getAge(),
				newAnimal.getWeight(),
				newAnimal.getBreed(),
				newAnimal.getDescription()
                );
        return service.addOne(entity);
	}

	@Override
	public boolean deleteOne(long id) {
		return service.deleteOne(id);
	}

	@Override
	public AnimalModel updateOne(long idToUpdate, AnimalModel updateAnimal) {
		AnimalEntity entity = new AnimalEntity(
				updateAnimal.getId(),
				updateAnimal.getName(),
				updateAnimal.getAge(),
				updateAnimal.getWeight(),
				updateAnimal.getBreed(),
				updateAnimal.getDescription()
                );
        AnimalEntity result = service.updateOne(idToUpdate, entity);
        
        AnimalModel updated = new AnimalModel(
        		result.getId(),
                result.getName(),
                result.getAge(),
                result.getWeight(),
                result.getBreed(),
                result.getDescription()
                );
        return updated;
	}

}
