package com.gcu.controller;

import java.util.List;

import javax.validation.Valid;

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
	
	@GetMapping("/all")
    public String showAll(Model model)
    {
        List<AnimalModel> animals = animalService.getAnimals();
        model.addAttribute("title", "Show all animals");
        model.addAttribute("searchModel", new SearchAnimalModel());
        model.addAttribute("animals", animals);
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
        animalService.addOne(newAnimal);
        
        List<AnimalModel> animals = animalService.getAnimals(); 
        
        model.addAttribute("animals", animals); 
        model.addAttribute("searchModel", new SearchAnimalModel()); 
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
		System.out.println("Performing search results for " + searchModel.getSearchTerm());
		
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
		return "adopt";
	}
	
	@GetMapping("/admin") 
	public String showAnimalsForAdmin(Model model)
	{  
		List<AnimalModel> animals = animalService.getAnimals();
        model.addAttribute("title", "Edit or Delete Animals");
        model.addAttribute("searchModel", new SearchAnimalModel());
        model.addAttribute("animals", animals);
        return "admin";
	}
	
	@PostMapping("/delete") 
	public String deleteAnimal(@Valid AnimalModel animal, BindingResult bindingResult, Model model) 
	{
		animalService.deleteOne(animal.getId());
		
		List<AnimalModel> animals = animalService.getAnimals(); 
		
		model.addAttribute("animals", animals); 
		model.addAttribute("searchModel", new SearchAnimalModel()); 
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
		animalService.updateOne(animal.getId(), animal);
		
		List<AnimalModel> animals = animalService.getAnimals();
		
		model.addAttribute("animals", animals); 
		model.addAttribute("searchModel", new SearchAnimalModel()); 
		return "adopt";
	}
}
