package com.gcu.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcu.model.AnimalEntity;

/**
 * Will list animals names despite cases
 */
@Repository
public interface AnimalRepository extends CrudRepository<AnimalEntity, Long>
{
	List<AnimalEntity> findByNameContainingIgnoreCase(String searchTerm);
}
