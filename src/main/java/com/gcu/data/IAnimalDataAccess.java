package com.gcu.data;

import java.util.List;

/**
 * methods for animal; list, add, delete, update
 */
public interface IAnimalDataAccess <T>
{
	public T getById(int Id);
	public List<T> getAnimals();
	public List<T> searchAnimal(String searchTerm);
	public int addOne(T newAnimal);
	public boolean deleteOne(long id);
	public T updateOne(long idToUpdate, T updateAnimal);
}
