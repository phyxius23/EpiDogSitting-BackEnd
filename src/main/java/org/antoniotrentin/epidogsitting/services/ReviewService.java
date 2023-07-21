package org.antoniotrentin.epidogsitting.services;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogOwner;
import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.entities.Review;
import org.antoniotrentin.epidogsitting.entities.payloads.ReviewCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepo;

	@Autowired
	DogSitterService dogSitterService;

	@Autowired
	DogOwnerService dogOwnerService;

	//***** CREATE *****
	public Review create(ReviewCreatePayload r) {
		DogSitter dogSitterFound = dogSitterService.readById(r.getDogSitter());
		DogOwner dogOwnerFound = dogOwnerService.readById(r.getDogOwner());

		Review newReview = new Review(r.getRate(), r.getComment(), dogSitterFound, dogOwnerFound);

		return reviewRepo.save(newReview);
	}

	//***** READ *****
	public Page<Review> readAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return reviewRepo.findAll(pageable);
	}

	// read by Id
	public Review readById(UUID id) throws NotFoundException {
		return reviewRepo.findById(id).orElseThrow(() -> new NotFoundException("Recensione non trovata"));
	}

	//***** UPDATE *****
	public Review updateById(UUID id, ReviewCreatePayload d) throws NotFoundException {
		Review reviewFound = this.readById(id);

		reviewFound.setId(id);
		reviewFound.setRate(d.getRate());
		reviewFound.setComment(d.getComment());
		reviewFound.setDogSitter(dogSitterService.readById(d.getDogSitter()));
		reviewFound.setDogOwner(dogOwnerService.readById(d.getDogOwner()));

		return reviewRepo.save(reviewFound);
	}

	//***** DELETE *****
	public void deleteById(UUID id) throws NotFoundException {
		Review reviewFound = this.readById(id);

		reviewRepo.delete(reviewFound);
	}

}
