package org.antoniotrentin.epidogsitting.controllers;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Dog;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.services.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dogs")
@PreAuthorize("hasAuthority('DOGOWNER')")
public class DogController {

	@Autowired
	DogService dogService;

	//	//***** CREATE *****
	//	@PostMapping("")
	//	@ResponseStatus(HttpStatus.CREATED)
	//	public Dog createDog(@RequestBody @Validated DogCreatePayload body) {
	//		return dogService.create(body);
	//	}

	//***** READ *****
	@GetMapping("")
	public Page<Dog> readDogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return dogService.readAll(page, size, sortBy);
	}

	//read by Id
	@GetMapping("/{dogId}")
	public Dog readDog(@PathVariable UUID dogId) throws Exception {
		return dogService.readById(dogId);
	}

	//	//read TEST
	//	@GetMapping("/test")
	//	public String readDogTest() {
	//		return "Endpoint di Dog funzionante!!!";
	//	}

	//***** UPDATE *****
	//	@PutMapping("/update/{id}")
	//	public Dog updateDog(@PathVariable UUID id, @RequestBody @Validated DogCreatePayload body) throws Exception {
	//		return dogService.updateById(id, body);
	//	}

	//***** DELETE *****
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDog(@PathVariable UUID id) throws NotFoundException {
		dogService.deleteById(id);
	}

}
