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
	public DogOwner create(DogOwnerCreatePayload dop) {
		// se l'email è già presente nel DB lancio una eccezione
		dogOwnerRepo.findByEmail(dop.getEmail()).ifPresent(dogowner -> {
			throw new BadRequestException("Email " + dogowner.getEmail() + " già in uso!");
		});

		DogOwner newDogOwner = new DogOwner(dop.getName(), dop.getSurname(), dop.getEmail(), dop.getPassword(),
				dop.getAddress());

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
	public DogOwner update(UUID id, DogOwnerCreatePayload ds) throws NotFoundException {
		DogOwner dogOwnerFound = this.readById(id);

		dogOwnerFound.setId(id);
		dogOwnerFound.setName(ds.getName());
		dogOwnerFound.setSurname(ds.getSurname());
		dogOwnerFound.setEmail(ds.getEmail());
		dogOwnerFound.setPassword(ds.getPassword());

		return dogOwnerRepo.save(dogOwnerFound);
	}

	//***** DELETE *****
	public void delete(UUID id) throws NotFoundException {
		DogOwner dogOwnerFound = this.readById(id);

		dogOwnerRepo.delete(dogOwnerFound);
	}

}
