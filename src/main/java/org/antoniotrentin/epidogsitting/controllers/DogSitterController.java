package org.antoniotrentin.epidogsitting.controllers;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.entities.OfferingType;
import org.antoniotrentin.epidogsitting.entities.User;
import org.antoniotrentin.epidogsitting.entities.payloads.DogSitterCreatePayload;
import org.antoniotrentin.epidogsitting.services.DogSitterService;
import org.antoniotrentin.epidogsitting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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
@RequestMapping("/api/dogsitters")
@PreAuthorize("hasAuthority('DOGSITTER') or hasAuthority('DOGOWNER')")
public class DogSitterController {

	@Autowired
	DogSitterService dogSitterService;

	@Autowired
	UserService userService;

	//***** CREATE *****
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public DogSitter createDogSitter(@RequestBody @Validated DogSitterCreatePayload body) {
		return dogSitterService.create(body);
	}

	//***** READ *****
	@GetMapping("")
	public Page<DogSitter> readDogSitters(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "") String postalCode, @RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") OfferingType offeringType) {
		return dogSitterService.readAll(page, size, sortBy, postalCode, name, offeringType);
	}

	//read by Id
	@GetMapping("/profile/{id}")
	@PostAuthorize("hasAuthority('DOGSITTER')")
	public DogSitter readDogSitter(@PathVariable UUID id) throws Exception {
		return dogSitterService.readById(id);
	}

	@GetMapping("/profile/me")
	public ResponseEntity<User> getProfiloUtente() {

		// Recupera l'utente corrente dal sistema di autenticazione
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		// Recupera i dettagli dell'utente dal database o da un altro servizio
		User user = userService.findByEmail(email);

		// Restituisci i dettagli dell'utente come risposta
		return ResponseEntity.ok(user);
	}

	//read TEST
	@GetMapping("/test")
	@PostAuthorize("hasAuthority('DOGSITTER')")
	public String readDogSitterTest() {
		return "Endpoint di DogSitter funzionante!!!";
	}

	//***** UPDATE *****
	@PutMapping("/{id}")
	public DogSitter updateDogSitter(@PathVariable UUID id, @RequestBody @Validated DogSitterCreatePayload body)
			throws Exception {
		return dogSitterService.updateById(id, body);
	}

	//***** DELETE *****
	@DeleteMapping("/profile/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDogSitter(@PathVariable UUID id) throws Exception {
		dogSitterService.deleteById(id);
	}
}
