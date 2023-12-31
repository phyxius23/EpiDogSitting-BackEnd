package org.antoniotrentin.epidogsitting.services;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.entities.OfferingType;
import org.antoniotrentin.epidogsitting.entities.payloads.DogSitterCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.BadRequestException;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.repositories.DogSitterRepository;
import org.antoniotrentin.epidogsitting.repositories.UserRepository;
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

	@Autowired
	UserRepository userRepo;

	// ***** CREATE *****
	public DogSitter create(DogSitterCreatePayload d) {
		// se l'email è già presente nel DB lancio una eccezione
		userRepo.findByEmail(d.getEmail()).ifPresent(dogsitter -> {
			throw new BadRequestException("Email " + dogsitter.getEmail() + " già in uso!");
		});

		DogSitter newDogSitter = new DogSitter(d.getName(), d.getSurname(), d.getEmail(), d.getPassword());

		return dogSitterRepo.save(newDogSitter);
	}

	//***** READ *****
	public Page<DogSitter> readAll(int page, int size, String sortBy, String postalCode, String name,
			OfferingType offeringType) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		// ******* CODICE DEFINITIVO *******
		if (!postalCode.equals("") && name.equals("") && offeringType == null) {
			return dogSitterRepo.findByAddressPostalCode(postalCode, pageable);
		}

		if (!postalCode.equals("") && !name.equals("") && offeringType == null) {
			return dogSitterRepo.findByByAddressPostalCodeByName(postalCode, name, pageable);
		}

		if (!postalCode.equals("") && name.equals("") && offeringType != null) {
			return dogSitterRepo.findByByAddressPostalCodeByOfferingType(postalCode, offeringType, pageable);
		}

		if (postalCode.equals("") && !name.equals("") && offeringType == null) {
			return dogSitterRepo.findByNameContaining(name, pageable);
		}

		if (postalCode.equals("") && name.equals("") && offeringType != null) {
			return dogSitterRepo.findByOfferingType(offeringType, pageable);
		}

		if (postalCode.equals("") && name.equals("") && offeringType == null) {
			return dogSitterRepo.findAll(pageable);
		}

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
	public DogSitter updateById(UUID id, DogSitterCreatePayload d) throws NotFoundException {
		DogSitter dogSitterFound = this.readById(id);

		dogSitterFound.setId(id);
		dogSitterFound.setName(d.getName());
		dogSitterFound.setSurname(d.getSurname());
		dogSitterFound.setEmail(d.getEmail());
		dogSitterFound.setPassword(d.getPassword());

		return dogSitterRepo.save(dogSitterFound);
	}

	//***** DELETE *****
	public void deleteById(UUID id) throws NotFoundException {
		DogSitter dogSitterFound = this.readById(id);

		dogSitterRepo.delete(dogSitterFound);
	}

}
