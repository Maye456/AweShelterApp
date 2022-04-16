package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger logger = LoggerFactory.getLogger(AnimalBusinessService.class);
    
	/**
	 * Get the animal by id
	 */
	@Override
	public AnimalModel getById(int Id) {
		// Log the API Call
        logger.info("Entering AnimalBusinessService.getById()");
        
		AnimalEntity result = service.getById(Id);
        AnimalModel animal = new AnimalModel(
                result.getId(),
                result.getName(),
                result.getAge(),
                result.getWeight(),
                result.getBreed(),
                result.getDescription()
                );
        
        // Log the API Call
        logger.info("Exiting AnimalBusinessService.getById()");
        return animal;
	}

	/**
	 * Get all the animals 
	 */
	@Override
	public List<AnimalModel> getAnimals() {
		// Log the API Call
        logger.info("Entering AnimalBusinessService.getAnimals()");
        
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
        
        // Log the API Call
        logger.info("Exiting AnimalBusinessService.getAnimals()");
        return animals;
	}

	/**
	 * Get all the animals from what is searched
	 */
	@Override
	public List<AnimalModel> searchAnimals(String searchTerm) {
		// Log the API Call
        logger.info("Entering AnimalBusinessService.searchAnimals()");
        
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
        
        // Log the API Call
        logger.info("Exiting AnimalBusinessService.searchAnimals()");
        return animals;
	}

	/**
	 * This will add one animal 
	 */
	@Override
	public int addOne(AnimalModel newAnimal) {
		// Log the API Call
        logger.info("Entering AnimalBusinessService.addOne()");
        
		AnimalEntity entity = new AnimalEntity(
				newAnimal.getId(),
				newAnimal.getName(),
				newAnimal.getAge(),
				newAnimal.getWeight(),
				newAnimal.getBreed(),
				newAnimal.getDescription()
                );
		
		// Log the API Call
        logger.info("Exiting AnimalBusinessService.addOne()");
        return service.addOne(entity);
	}

	/**
	 * This will delete one animal 
	 */
	@Override
	public boolean deleteOne(long id) {
		// Log the API Call
        logger.info("Entering AnimalBusinessService.deleteOne()");
        
        // Log the API Call
        logger.info("Exiting AnimalBusinessService.deleteOne()");
		return service.deleteOne(id);
	}

	/**
	 * Update an animal
	 */
	@Override
	public AnimalModel updateOne(long idToUpdate, AnimalModel updateAnimal) {
		// Log the API Call
        logger.info("Entering AnimalBusinessService.updateOne()");
        
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
        
        // Log the API Call
        logger.info("Exiting AnimalBusinessService.updateOne()");
        return updated;
	}

}
