package org.antoniotrentin.epidogsitting.services;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogOwner;
import org.antoniotrentin.epidogsitting.entities.payloads.DogOwnerCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.BadRequestException;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.repositories.DogOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DogOwnerService {

	@Autowired
	DogOwnerRepository dogOwnerRepo;

	// ***** CREATE *****
	public DogOwner create(DogOwnerCreatePayload d) {
		// se l'email è già presente nel DB lancio una eccezione
		dogOwnerRepo.findByEmail(d.getEmail()).ifPresent(dogowner -> {
			throw new BadRequestException("Email " + dogowner.getEmail() + " già in uso!");
		});

		DogOwner newDogOwner = new DogOwner(d.getName(), d.getSurname(), d.getEmail(), d.getPassword());

		return dogOwnerRepo.save(newDogOwner);
	}

	//***** READ *****
	public Page<DogOwner> readAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return dogOwnerRepo.findAll(pageable);
	}

	// read by Id
	public DogOwner readById(UUID id) throws NotFoundException {
		return dogOwnerRepo.findById(id).orElseThrow(() -> new NotFoundException("DogOwner non trovato"));
	}

	// read by email
	public DogOwner readByEmail(String email) throws NotFoundException {
		return dogOwnerRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Email non trovata"));
	}

	//***** UPDATE *****
	public DogOwner updateById(UUID id, DogOwnerCreatePayload d) throws NotFoundException {
		DogOwner dogOwnerFound = this.readById(id);

		dogOwnerFound.setId(id);
		dogOwnerFound.setName(d.getName());
		dogOwnerFound.setSurname(d.getSurname());
		dogOwnerFound.setEmail(d.getEmail());
		dogOwnerFound.setPassword(d.getPassword());

		return dogOwnerRepo.save(dogOwnerFound);
	}

	//***** DELETE *****
	public void deleteById(UUID id) throws NotFoundException {
		DogOwner dogOwnerFound = this.readById(id);

		dogOwnerRepo.delete(dogOwnerFound);
	}

}
