package org.antoniotrentin.epidogsitting.controllers;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Dog;
import org.antoniotrentin.epidogsitting.entities.payloads.DogCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.services.DogService;
import org.antoniotrentin.epidogsitting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/api/dogs")
@PreAuthorize("hasAuthority('DOGOWNER')")
public class DogController {

	@Autowired
	DogService dogService;

	@Autowired
	UserService userService;

	//***** CREATE DOG ***** testata ok
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Dog createDog(@RequestBody @Validated DogCreatePayload body) {

		// Recupera l'utente corrente dal sistema di autenticazione
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		// Recupera i dettagli dell'utente dal database o da un altro servizio
		UUID userId = userService.findByEmail(email).getId();

		return dogService.create(userId, body);
	}

	//***** UPDATE DOG BY ID ***** testata ok
	@PutMapping("/{dogId}")
	public Dog updateDog(@PathVariable UUID dogId, @RequestBody @Validated DogCreatePayload body) throws Exception {

		// Recupera l'utente corrente dal sistema di autenticazione
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		// Recupera i dettagli dell'utente dal database o da un altro servizio
		UUID userId = userService.findByEmail(email).getId();

		return dogService.updateById(userId, dogId, body);
	}

	//***** DELETE DOG BY ID ***** testata ok
	@DeleteMapping("/{dogId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDog(@PathVariable UUID dogId) throws NotFoundException {
		dogService.deleteById(dogId);
	}

	//***** DA TESTARE *****
	//**********************

	//***** READ ALL DOGS WITH PAGINATION *****
	@GetMapping("")
	public Page<Dog> readDogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return dogService.readAll(page, size, sortBy);
	}

	//***** READ DOG BY ID *****
	@GetMapping("/{dogId}")
	public Dog readDog(@PathVariable UUID dogId) throws Exception {
		return dogService.readById(dogId);
	}

	// read dogs by dogowner id
	//	@GetMapping("/me")
	//	public Optional<Dog> readDogsByDogOwner(@RequestParam UUID dogOwnerId) {
	//		return dogService.readByDogOwnerId(dogOwnerId);
	//	}

}
