package org.antoniotrentin.epidogsitting.services;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Dog;
import org.antoniotrentin.epidogsitting.entities.DogOwner;
import org.antoniotrentin.epidogsitting.entities.payloads.DogCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DogService {

	@Autowired
	DogRepository dogRepo;

	@Autowired
	DogOwnerService dogOwnerService;

	//***** CREATE *****
	public Dog create(DogCreatePayload a) {
		//		// se l'email è già presente nel DB lancio una eccezione
		//		dogSitterRepo.findByEmail(dsp.getEmail()).ifPresent(dogsitter -> {
		//			throw new BadRequestException("Email " + dogsitter.getEmail() + " già in uso!");
		//		});

		DogOwner dogOwnerFound = dogOwnerService.readById(a.getDogOwner());

		Dog newDog = new Dog(a.getName(), a.getAge(), a.getBreed(), a.getWeight(), a.getDescription(), dogOwnerFound);

		return dogRepo.save(newDog);
	}

	//***** READ *****
	public Page<Dog> readAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return dogRepo.findAll(pageable);
	}

	// read by Id
	public Dog readById(UUID id) throws NotFoundException {
		return dogRepo.findById(id).orElseThrow(() -> new NotFoundException("Dog non trovato"));
	}

	//***** UPDATE *****
	public Dog updateById(UUID id, DogCreatePayload ds) throws NotFoundException {
		Dog dogFound = this.readById(id);

		dogFound.setId(id);

		return dogRepo.save(dogFound);
	}

	//***** DELETE *****
	public void deleteById(UUID id) throws NotFoundException {
		Dog dogFound = this.readById(id);

		dogRepo.delete(dogFound);
	}

}
