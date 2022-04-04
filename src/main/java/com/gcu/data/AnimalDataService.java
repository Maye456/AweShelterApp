package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.gcu.model.AnimalEntity;

@Service
public class AnimalDataService implements IAnimalDataAccess<AnimalEntity> 
{
	@Autowired
	private AnimalRepository animalRepository;
	@SuppressWarnings("unused")
	
	Logger logger = LoggerFactory.getLogger(AnimalDataService.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	// Non-Default Constructor
	public AnimalDataService(AnimalRepository animalRepository, DataSource dataSource)
	{
		this.animalRepository = animalRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public AnimalEntity getById(int Id) {
		// Log the API Call
        logger.info("Entering AnimalDataService.getById()");
        // Log the API Call
        logger.info("Exiting AnimalDataService.getById()");
        
		return animalRepository.findById((long) Id).orElse(null);
	}

	@Override
	public List<AnimalEntity> getAnimals() {
		// Log the API Call
        logger.info("Entering AnimalDataService.getAnimals()");
        
		List<AnimalEntity> animals = (List<AnimalEntity>) animalRepository.findAll();
		
		// Log the API Call
        logger.info("Exiting AnimalDataService.getAnimals()");
		return animals;
	}

	@Override
	public List<AnimalEntity> searchAnimal(String searchTerm) {
		// Log the API Call
        logger.info("Entering AnimalDataService.searchAnimal()");
        
		List<AnimalEntity> result = animalRepository.findByNameContainingIgnoreCase(searchTerm);
		
		// Log the API Call
        logger.info("Exiting AnimalDataService.searchAnimal()");
		return result;
	}

	@Override
	public int addOne(AnimalEntity newAnimal) {
		// Log the API Call
        logger.info("Entering AnimalDataService.addOne()");
        
		AnimalEntity result = animalRepository.save(newAnimal);
		if (result == null)
        {
            return 0;
        }
		
		// Log the API Call
        logger.info("Exiting AnimalDataService.addOne()");
        return (int) result.getId();
	}

	@Override
	public boolean deleteOne(long id) {
		// Log the API Call
        logger.info("Entering AnimalDataService.deleteOne()");
        
		animalRepository.deleteById(id);
		
		// Log the API Call
        logger.info("Exiting AnimalDataService.deleteOne()");
		return true;
	}

	@Override
	public AnimalEntity updateOne(long idToUpdate, AnimalEntity updateAnimal) {
		// Log the API Call
        logger.info("Entering AnimalDataService.updateOne()");
        
		AnimalEntity result = animalRepository.save(updateAnimal);
		
		// Log the API Call
        logger.info("Exiting AnimalDataService.updateOne()");
		return result;
	}
}
