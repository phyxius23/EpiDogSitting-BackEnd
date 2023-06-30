package org.antoniotrentin.epidogsitting.controllers;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Dog;
import org.antoniotrentin.epidogsitting.entities.payloads.DogCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.services.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	//***** CREATE *****
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Dog createDog(@RequestBody @Validated DogCreatePayload body) {
		return dogService.create(body);
	}

	//***** READ *****
	@GetMapping("")
	public Page<Dog> readDogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return dogService.readAll(page, size, sortBy);
	}

	//read by Id
	@GetMapping("/{id}")
	public Dog readDog(@PathVariable UUID id) throws Exception {
		return dogService.readById(id);
	}

	//read TEST
	@GetMapping("/test")
	public String readDogTest() {
		return "Si è loggato!!!";
	}

	//***** UPDATE *****
	@PutMapping("/{id}")
	public Dog updateDog(@PathVariable UUID id, @RequestBody DogCreatePayload body) throws Exception {
		return dogService.updateById(id, body);
	}

	//***** DELETE *****
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDog(@PathVariable UUID id) throws NotFoundException {
		dogService.deleteById(id);
	}

}
