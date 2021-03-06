package com.gcu.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.IAnimalBusinessService;
import com.gcu.model.AnimalModel;
import com.gcu.model.SearchAnimalModel;

@Controller
@RequestMapping("/animal")
public class AnimalController 
{
	@Autowired
	IAnimalBusinessService animalService;
	
	Logger logger = LoggerFactory.getLogger(AnimalController.class);
	
	@GetMapping("/all")
    public String showAll(Model model)
    {
		// Log the API Call
        logger.info("Entering AnimalController.getAnimals()");
        
        List<AnimalModel> animals = animalService.getAnimals();
        model.addAttribute("title", "Show all animals");
        model.addAttribute("searchModel", new SearchAnimalModel());
        model.addAttribute("animals", animals);
        
        // Log the API Call
        logger.info("Exiting AnimalController.getAnimals()");
        return "adopt";
    }
	
	@GetMapping("/addNewForm")
	public String displayAddNewForm(Model model)
	{
		model.addAttribute("title", "Add new animal");
		model.addAttribute("animalModel", new AnimalModel());
		return "create";
	}
	
	@PostMapping("/addNew") 
    public String addProcedure(@Valid AnimalModel newAnimal, BindingResult bindingResult, Model model) 
    {
		// Log the API Call
        logger.info("Entering AnimalController.addOne()");
        
        animalService.addOne(newAnimal);
        
        List<AnimalModel> animals = animalService.getAnimals(); 
        
        model.addAttribute("animals", animals); 
        model.addAttribute("searchModel", new SearchAnimalModel()); 
        
        // Log the API Call
        logger.info("Exiting AnimalController.addOne()");
        return "adopt";
    } 
	
	@GetMapping("/searchForm")
	public String displaySearchForm(Model model)
	{
		model.addAttribute("title", "Search Animals");
		model.addAttribute("searchAnimalModel", new SearchAnimalModel());
		return "search";
	}
	
	@PostMapping("/searchResults")
	public String showAllAnimals(@Valid SearchAnimalModel searchModel, BindingResult bindingResult, Model model)
	{
		// Log the API Call
        logger.info("Entering AnimalController.searchAnimals()");
		
		// Check for validation errors
		if (bindingResult.hasErrors())
		{
			model.addAttribute("title", "Search for Animals");
			return "search";
		}
		List<AnimalModel> animals = animalService.searchAnimals(searchModel.getSearchTerm());
		model.addAttribute("title", "Search for Animals");
		model.addAttribute("searchModel", searchModel);
		model.addAttribute("animals", animals);
		
		// Log the API Call
        logger.info("Exiting AnimalController.searchAnimals()");
		return "adopt";
	}
	
	@GetMapping("/admin") 
	public String showAnimalsForAdmin(Model model)
	{  
		// Log the API Call
        logger.info("Entering AnimalController.getAnimalsADMIN()");
        
		List<AnimalModel> animals = animalService.getAnimals();
        model.addAttribute("title", "Edit or Delete Animals");
        model.addAttribute("searchModel", new SearchAnimalModel());
        model.addAttribute("animals", animals);
        
        // Log the API Call
        logger.info("Exiting AnimalController.getAnimalsADMIN()");
        return "admin";
	}
	
	@PostMapping("/delete") 
	public String deleteAnimal(@Valid AnimalModel animal, BindingResult bindingResult, Model model) 
	{
		// Log the API Call
        logger.info("Entering AnimalController.deleteOne()");
        
		animalService.deleteOne(animal.getId());
		
		List<AnimalModel> animals = animalService.getAnimals(); 
		
		model.addAttribute("animals", animals); 
		model.addAttribute("searchModel", new SearchAnimalModel()); 
		
		// Log the API Call
        logger.info("Exiting AnimalController.deleteOne()");
		return "adopt";
	}
	
	@PostMapping("/editForm") 
	public String displayEditForm(AnimalModel animalModel, Model model)
	{
		model.addAttribute("title", "Edit Animal");
		model.addAttribute("animalModel", animalModel);
		return "editAnimal";
	}
	
	@PostMapping("/doUpdate") 
	public String updateAnimal(@Valid AnimalModel animal, BindingResult bindingResult, Model model)
	{
		// Log the API Call
        logger.info("Entering AnimalController.updateOne()");
        
		animalService.updateOne(animal.getId(), animal);
		
		List<AnimalModel> animals = animalService.getAnimals();
		
		model.addAttribute("animals", animals); 
		model.addAttribute("searchModel", new SearchAnimalModel()); 
		
		// Log the API Call
        logger.info("Exiting AnimalController.updateOne()");
		return "adopt";
	}
}
