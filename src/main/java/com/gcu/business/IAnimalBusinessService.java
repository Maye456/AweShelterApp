package com.gcu.business;

import java.util.List;

import com.gcu.model.AnimalModel;

public interface IAnimalBusinessService 
{
	/**
	 * Get the animal by id
	 */
	public AnimalModel getById(int Id);
	
	/**
	 * Get all the animals 
	 */
	public List<AnimalModel> getAnimals();
	
	/**
	 * Get all the animals from what is searched
	 */
	public List<AnimalModel> searchAnimals(String searchTerm);

	/**
	 * This will add one animal 
	 */
	public int addOne(AnimalModel newAnimal);
	
	/**
	 * This will delete one animal 
	 */
	public boolean deleteOne(long id);
	
	/**
	 * Update an animal
	 */
	public AnimalModel updateOne(long idToUpdate, AnimalModel updateAnimal);
}
