package org.antoniotrentin.epidogsitting.services;

import java.util.List;
import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogOwner;
import org.antoniotrentin.epidogsitting.entities.Favorite;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

	@Autowired
	FavoriteRepository favoriteRepo;

	@Autowired
	DogOwnerService dogOwnerService;

	@Autowired
	DogSitterService dogSitterService;

	//***** CREATE *****
	public Favorite create(UUID dogOwnerId, String dogSitterId) {

		DogOwner dogOwnerFound = dogOwnerService.readById(dogOwnerId);
		//		DogSitter dogSitterFound = dogSitterService.readById(dogSitterId);

		Favorite newFavorite = new Favorite(dogSitterId, dogOwnerFound);

		return favoriteRepo.save(newFavorite);
	}

	//***** READ *****
	public Page<Favorite> readAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return favoriteRepo.findAll(pageable);
	}

	// read by dogownerId
	public List<Favorite> readByDogOwner(UUID dogOwnerId) {

		//		UUID dogOwnerUUID = UUID.fromString(dogOwnerId);
		return favoriteRepo.findByDogOwnerId(dogOwnerId);
	}

	// read by Id
	public Favorite readById(UUID id) throws NotFoundException {
		return favoriteRepo.findById(id).orElseThrow(() -> new NotFoundException("Preferito non trovato"));
	}

	//***** UPDATE *****
	//	public Favorite updateById(UUID id, FavoritePayload f) throws NotFoundException {
	//		Favorite favoriteFound = this.readById(id);
	//
	//		favoriteFound.setId(id);
	//		
	//		//favoriteFound.setDogSitter(favoriteRepo.findById(f.getDogSitter()));
	//		//favoriteFound.setDogOwner(favoriteRepo.findById(f.getDogOwner()));	
	//
	//		return favoriteRepo.save(favoriteFound);
	//	}

	//***** DELETE *****
	public void deleteById(UUID id) throws NotFoundException {
		Favorite favoriteFound = this.readById(id);

		favoriteRepo.delete(favoriteFound);
	}

}
