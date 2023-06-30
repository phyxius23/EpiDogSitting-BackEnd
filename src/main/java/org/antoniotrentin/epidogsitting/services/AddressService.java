package org.antoniotrentin.epidogsitting.services;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Address;
import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.entities.payloads.AddressCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	DogSitterService dogSitterService;

	//***** CREATE *****
	public Address create(AddressCreatePayload a, UUID id) {
		DogSitter dogSitterFound = dogSitterService.readById(id);

		Address newAddress = new Address(a.getStreet(), a.getCity(), a.getProvince(), a.getPostalCode());

		return addressRepo.save(newAddress);
	}

	//***** READ *****
	public Page<Address> readAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return addressRepo.findAll(pageable);
	}

	// read by Id
	public Address readById(UUID id) throws NotFoundException {
		return addressRepo.findById(id).orElseThrow(() -> new NotFoundException("Indirizzo non trovato"));
	}

	//***** UPDATE *****
	public Address updateById(UUID id, AddressCreatePayload ds) throws NotFoundException {
		Address addressFound = this.readById(id);

		addressFound.setId(id);

		return addressRepo.save(addressFound);
	}

	//***** DELETE *****
	public void deleteById(UUID id) throws NotFoundException {
		Address addressFound = this.readById(id);

		addressRepo.delete(addressFound);
	}
}
