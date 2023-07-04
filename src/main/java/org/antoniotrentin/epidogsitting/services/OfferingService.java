package org.antoniotrentin.epidogsitting.services;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.entities.Offering;
import org.antoniotrentin.epidogsitting.entities.OfferingType;
import org.antoniotrentin.epidogsitting.entities.payloads.OfferingCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.repositories.OfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OfferingService {

	@Autowired
	OfferingRepository offeringRepo;

	@Autowired
	DogSitterService dogSitterService;

	//***** CREATE *****
	public Offering create(OfferingCreatePayload o) {
		DogSitter dogSitterFound = dogSitterService.readById(o.getDogSitter());

		Offering newOffering = null;

		if (offeringRepo.findByDogSitterIdAndType(o.getDogSitter(), OfferingType.valueOf(o.getType())).isEmpty()) {
			newOffering = new Offering(OfferingType.valueOf(o.getType()), o.getPrice(), dogSitterFound);
		} else {
			throw new NotFoundException("Servizio " + o.getType() + " già presente nel DB per il dogsitter specificato.");
		}

		return offeringRepo.save(newOffering);
	}

	//***** READ *****
	public Page<Offering> readAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return offeringRepo.findAll(pageable);
	}

	// read by Id
	public Offering readById(UUID id) throws NotFoundException {
		return offeringRepo.findById(id).orElseThrow(() -> new NotFoundException("Servizio non trovato"));
	}

	//***** UPDATE *****
	public Offering updateById(UUID id, OfferingCreatePayload o) throws NotFoundException {
		//DogSitter dogSitterFound = dogSitterService.readById(o.getDogSitter());

		Offering offeringFound = this.readById(id);

		if (offeringFound.getType() == OfferingType.valueOf(o.getType())) {
			offeringFound.setId(id);
			offeringFound.setType(OfferingType.valueOf(o.getType()));
			offeringFound.setPrice(o.getPrice());
			offeringFound.setDogSitter(dogSitterService.readById(o.getDogSitter()));
		} else {
			throw new NotFoundException("Il tipo di servizio non può essere modificato.");
		}

		return offeringRepo.save(offeringFound);
	}

	//***** DELETE *****
	public void deleteById(UUID id) throws NotFoundException {
		Offering offeringFound = this.readById(id);

		offeringRepo.delete(offeringFound);
	}

}
