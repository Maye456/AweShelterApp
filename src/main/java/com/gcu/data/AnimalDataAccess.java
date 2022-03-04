package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.model.AnimalMapper;
import com.gcu.model.AnimalModel;

public class AnimalDataAccess implements IAnimalDataAccess<AnimalModel> 
{
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	DataSource dataSource;
	JdbcTemplate jdbcTemplate;
	
	public AnimalDataAccess(DataSource dataSource)
	{
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public AnimalModel getById(int Id) {
		AnimalModel result = jdbcTemplate.queryForObject(
				"SELECT * FROM animal WHERE Id = ?",
				new AnimalMapper(),
				new Object[] {Id});
		return result;
	}

	@Override
	public List<AnimalModel> getAnimals() {
		return jdbcTemplate.query(
				"SELECT * FROM animal", 
				new AnimalMapper());
	}

	@Override
	public List<AnimalModel> searchAnimal(String searchTerm) {
		return jdbcTemplate.query(
				"SELECT * FROM animal WHERE Name LIKE ?",
				new AnimalMapper(),
				new Object[] {"%" + searchTerm + "%"});
	}

	@Override
	public int addOne(AnimalModel newAnimal) {
		return jdbcTemplate.update(
				"INSERT INTO animal (Name, Age, Weight, Breed, Description) VALUES (?, ?, ?, ?, ?)",
				newAnimal.getName(),
				newAnimal.getAge(),
				newAnimal.getWeight(),
				newAnimal.getBreed(),
				newAnimal.getDescription());
	}

	@Override
	public boolean deleteOne(long id) {
		int updateResult = jdbcTemplate.update(
				"DELETE FROM animal WHERE Id = ?",
				new Object[] {id});
		return (updateResult > 0);
	}

	@Override
	public AnimalModel updateOne(long idToUpdate, AnimalModel updateAnimal) {
		int result = jdbcTemplate.update(
				"UPDATE animal SET Name = ?, Age = ?, Weight = ?, Breed = ?, Description = ? WHERE ID = ?",
				updateAnimal.getName(),
				updateAnimal.getAge(),
				updateAnimal.getWeight(),
				updateAnimal.getBreed(),
				updateAnimal.getDescription(),
				idToUpdate);
		if (result > 0)
		{
			return updateAnimal;
		}
		else
		{
			return null;
		}
	}
}
