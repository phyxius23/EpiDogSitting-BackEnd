package org.antoniotrentin.epidogsitting.controllers;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.services.DogSitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dogsitters")
@PreAuthorize("hasAuthority('DOGSITTER') or hasAuthority('DOGOWNER')")
public class DogSitterController {

	@Autowired
	DogSitterService dogSitterService;

	//	@Autowired
	//	private PasswordEncoder bcrypt;

	@GetMapping("")
	@PostAuthorize("hasAuthority('DOGSITTER')")
	public String readDogSitters() {
		return "Benvenuto!";
	}

	@GetMapping("/profile/{id}")
	@PostAuthorize("hasAuthority('DOGSITTER')")
	public DogSitter readDogSitter(@PathVariable UUID id) throws Exception {
		return dogSitterService.readById(id);
	}

	@DeleteMapping("/profile/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDogSitter(@PathVariable UUID id) throws Exception {
		dogSitterService.delete(id);
	}

	//	@PostMapping("")
	//	@ResponseStatus(HttpStatus.CREATED)
	//	public Utente createUtente(@RequestBody @Validated RegistrazioneUtentePayload u) {
	//		return utenteService.create(u);
	//	}
	//
	//	@GetMapping("")
	//	public Page<Utente> readUtenti(@RequestParam(defaultValue = "0") int page,
	//			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
	//		return utenteService.readAll(page, size, sortBy);
	//	}
	//
	//	@GetMapping("/{utenteId}")
	//	public Utente readUtente(@PathVariable UUID utenteId) throws Exception {
	//		return utenteService.readById(utenteId);
	//	}
	//
	//	@PutMapping("/{utenteId}")
	//	public Utente updateUtente(@PathVariable UUID utenteId, @RequestBody RegistrazioneUtentePayload u) throws Exception {
	//		return utenteService.update(utenteId, u);
	//	}
	//
	//	@DeleteMapping("/{utenteId}")
	//	@ResponseStatus(HttpStatus.NO_CONTENT)
	//	public void deleteUtente(@PathVariable UUID utenteId) throws Exception {
	//		utenteService.delete(utenteId);
	//	}

}
