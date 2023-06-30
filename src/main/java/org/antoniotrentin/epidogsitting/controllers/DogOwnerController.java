package org.antoniotrentin.epidogsitting.controllers;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogOwner;
import org.antoniotrentin.epidogsitting.entities.payloads.DogOwnerCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.services.DogOwnerService;
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
@RequestMapping("/dogowners")
@PreAuthorize("hasAuthority('DOGOWNER')")
public class DogOwnerController {

	@Autowired
	DogOwnerService dogOwnerService;

	//***** CREATE *****
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public DogOwner createDogOwner(@RequestBody @Validated DogOwnerCreatePayload body) {
		return dogOwnerService.create(body);
	}

	//***** READ *****
	@GetMapping("")
	public Page<DogOwner> readDogOwners(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return dogOwnerService.readAll(page, size, sortBy);
	}

	//read by Id
	@GetMapping("/{id}")
	public DogOwner readDogOwner(@PathVariable UUID id) throws Exception {
		return dogOwnerService.readById(id);
	}

	//read TEST
	@GetMapping("/test")
	public String readDogOwnerTest() {
		return "Endpoint di DogOwner funzionante!!!";
	}

	//***** UPDATE *****
	@PutMapping("/{id}")
	public DogOwner updateDogOwner(@PathVariable UUID id, @RequestBody @Validated DogOwnerCreatePayload body)
			throws Exception {
		return dogOwnerService.updateById(id, body);
	}

	//***** DELETE *****
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDogOwner(@PathVariable UUID id) throws NotFoundException {
		dogOwnerService.deleteById(id);
	}

}
