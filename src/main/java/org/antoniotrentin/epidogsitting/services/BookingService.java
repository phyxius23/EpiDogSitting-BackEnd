package org.antoniotrentin.epidogsitting.services;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Booking;
import org.antoniotrentin.epidogsitting.entities.DogOwner;
import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.entities.Offering;
import org.antoniotrentin.epidogsitting.entities.StateBooking;
import org.antoniotrentin.epidogsitting.entities.payloads.BookingCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepo;

	@Autowired
	DogOwnerService dogOwnerService;

	@Autowired
	DogSitterService dogSitterService;

	@Autowired
	OfferingService offeringService;

	//***** CREATE *****
	public Booking create(BookingCreatePayload b) {

		Offering offeringFound = offeringService.readById(b.getOffering());
		DogSitter dogSitterFound = dogSitterService.readById(b.getDogSitter());
		DogOwner dogOwnerFound = dogOwnerService.readById(b.getDogOwner());

		Booking newBooking = new Booking(b.getStartingDate(), b.getEndingDate(), StateBooking.valueOf(b.getState()),
				b.getMessage(), b.getPrice(), offeringFound, dogSitterFound, dogOwnerFound);

		return bookingRepo.save(newBooking);
	}

	//***** READ *****
	public Page<Booking> readAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return bookingRepo.findAll(pageable);
	}

	// read by Id
	public Booking readById(UUID id) throws NotFoundException {
		return bookingRepo.findById(id).orElseThrow(() -> new NotFoundException("Prenotazione non trovata"));
	}

	//***** UPDATE *****
	public Booking updateById(UUID id, BookingCreatePayload b) throws NotFoundException {
		Booking bookingFound = this.readById(id);

		bookingFound.setId(id);
		bookingFound.setStartingDate(b.getStartingDate());
		bookingFound.setEndingDate(b.getEndingDate());
		bookingFound.setState(StateBooking.valueOf(b.getState()));
		bookingFound.setMessage(b.getMessage());
		bookingFound.setPrice(b.getPrice());
		bookingFound.setOffering(offeringService.readById(b.getOffering()));
		bookingFound.setDogSitter(dogSitterService.readById(b.getDogSitter()));
		bookingFound.setDogOwner(dogOwnerService.readById(b.getDogOwner()));

		return bookingRepo.save(bookingFound);
	}

	//***** DELETE *****
	public void deleteById(UUID id) throws NotFoundException {
		Booking bookingFound = this.readById(id);

		bookingRepo.delete(bookingFound);
	}
}
