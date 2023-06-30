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
	public Dog create(DogCreatePayload d) {
		DogOwner dogOwnerFound = dogOwnerService.readById(d.getDogOwner());

		Dog newDog = new Dog(d.getName(), d.getAge(), d.getBreed(), d.getWeight(), d.getDescription(), dogOwnerFound);

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
	public Dog updateById(UUID id, DogCreatePayload d) throws NotFoundException {
		Dog dogFound = this.readById(id);

		dogFound.setId(id);
		dogFound.setName(d.getName());
		dogFound.setAge(d.getAge());
		dogFound.setBreed(d.getBreed());
		dogFound.setWeight(d.getWeight());
		dogFound.setDescription(d.getDescription());
		dogFound.setDogOwner(dogOwnerService.readById(d.getDogOwner()));

		return dogRepo.save(dogFound);
	}

	//***** DELETE *****
	public void deleteById(UUID id) throws NotFoundException {
		Dog dogFound = this.readById(id);

		dogRepo.delete(dogFound);
	}

}
