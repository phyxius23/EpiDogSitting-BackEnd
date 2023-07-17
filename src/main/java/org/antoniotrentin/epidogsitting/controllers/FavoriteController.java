package org.antoniotrentin.epidogsitting.controllers;

import java.util.List;
import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Favorite;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorites")
@PreAuthorize("hasAuthority('DOGOWNER')")
public class FavoriteController {

	@Autowired
	FavoriteService favoriteService;

	//***** CREATE *****
	@PostMapping("/{dogOwnerId}/{dogSitterId}")
	@ResponseStatus(HttpStatus.CREATED)
	//	public Favorite createFavorite(@RequestBody @Validated FavoritePayload body) {
	public Favorite createFavorite(@PathVariable UUID dogOwnerId, @PathVariable String dogSitterId) {
		return favoriteService.create(dogOwnerId, dogSitterId);
	}

	//***** READ *****
	@GetMapping("")
	public Page<Favorite> readFavorites(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return favoriteService.readAll(page, size, sortBy);
	}

	@GetMapping("/dogowner/{dogOwnerId}")
	public List<Favorite> readFavoritesByDogOwner(@PathVariable UUID dogOwnerId) {
		return favoriteService.readByDogOwner(dogOwnerId);
	}

	//read by Id
	@GetMapping("/{id}")
	public Favorite readFavorite(@PathVariable UUID id) throws Exception {
		return favoriteService.readById(id);
	}

	//read TEST
	@GetMapping("/test")
	public String readFavoriteTest() {
		return "Endpoint di Favorite funzionante!!!";
	}

	//***** UPDATE *****
	//	@PutMapping("/{id}")
	//	public Favorite updateFavorite(@PathVariable UUID id, @RequestBody @Validated FavoritePayload body) throws Exception {
	//		return favoriteService.updateById(id, body);
	//	}

	//***** DELETE *****
	@DeleteMapping("/{favoriteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFavorite(@PathVariable UUID favoriteId) throws NotFoundException {
		favoriteService.deleteById(favoriteId);
	}

}
