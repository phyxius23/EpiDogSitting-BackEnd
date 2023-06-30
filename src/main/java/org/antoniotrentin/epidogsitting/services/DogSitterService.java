package org.antoniotrentin.epidogsitting.services;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.entities.payloads.DogSitterCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.BadRequestException;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.repositories.DogSitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DogSitterService {

	@Autowired
	DogSitterRepository dogSitterRepo;

	// ***** CREATE *****
	public DogSitter create(DogSitterCreatePayload d) {
		// se l'email è già presente nel DB lancio una eccezione
		dogSitterRepo.findByEmail(d.getEmail()).ifPresent(dogsitter -> {
			throw new BadRequestException("Email " + dogsitter.getEmail() + " già in uso!");
		});

		DogSitter newDogSitter = new DogSitter(d.getName(), d.getSurname(), d.getEmail(), d.getPassword());

		return dogSitterRepo.save(newDogSitter);
	}

	//***** READ *****
	public Page<DogSitter> readAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return dogSitterRepo.findAll(pageable);
	}

	// read by Id
	public DogSitter readById(UUID id) throws NotFoundException {
		return dogSitterRepo.findById(id).orElseThrow(() -> new NotFoundException("DogSitter non trovato"));
	}

	// read by email
	public DogSitter readByEmail(String email) throws NotFoundException {
		return dogSitterRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Email non trovata"));
	}

	//***** UPDATE *****
	public DogSitter update(UUID id, DogSitterCreatePayload d) throws NotFoundException {
		DogSitter dogSitterFound = this.readById(id);

		dogSitterFound.setId(id);
		dogSitterFound.setName(d.getName());
		dogSitterFound.setSurname(d.getSurname());
		dogSitterFound.setEmail(d.getEmail());
		dogSitterFound.setPassword(d.getPassword());

		return dogSitterRepo.save(dogSitterFound);
	}

	//***** DELETE *****
	public void delete(UUID id) throws NotFoundException {
		DogSitter dogSitterFound = this.readById(id);

		dogSitterRepo.delete(dogSitterFound);
	}

}
