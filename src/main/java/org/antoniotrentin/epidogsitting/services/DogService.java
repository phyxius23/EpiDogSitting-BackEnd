package org.antoniotrentin.epidogsitting.services;

import java.util.List;
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
	public Dog create(UUID userId, DogCreatePayload d) {
		DogOwner dogOwnerFound = dogOwnerService.readById(userId);

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

	// read by dogOwnerId
	public List<Dog> readByDogOwnerId(UUID dogOwnerId) throws NotFoundException {
		List<Dog> dogs = dogRepo.findByDogOwnerId(dogOwnerId);
		if (dogs.isEmpty()) {
			throw new NotFoundException("Nessun dogs trovato");
		}
		return dogs;
	}
	//return dogRepo.findByDogOwnerId(dogOwnerId).orElseThrow(() -> new NotFoundException("Nessun dogs trovato"));

	//***** UPDATE *****
	public Dog updateById(UUID userId, UUID dogId, DogCreatePayload d) throws NotFoundException {
		Dog dogFound = this.readById(dogId);

		dogFound.setId(dogId);
		dogFound.setName(d.getName());
		dogFound.setAge(d.getAge());
		dogFound.setBreed(d.getBreed());
		dogFound.setWeight(d.getWeight());
		dogFound.setDescription(d.getDescription());
		dogFound.setDogOwner(dogOwnerService.readById(userId));

		return dogRepo.save(dogFound);
	}

	//***** DELETE *****
	public void deleteById(UUID id) throws NotFoundException {
		Dog dogFound = this.readById(id);

		dogRepo.delete(dogFound);
	}

}
