package org.antoniotrentin.epidogsitting.controllers;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Review;
import org.antoniotrentin.epidogsitting.entities.payloads.ReviewCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@PreAuthorize("hasAuthority('DOGSITTER') or hasAuthority('DOGOWNER')")
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	//***** CREATE *****
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	@PostAuthorize("hasAuthority('DOGOWNER')")
	public Review createReview(@RequestBody @Validated ReviewCreatePayload body) {
		return reviewService.create(body);
	}

	//***** READ *****
	@GetMapping("")
	public Page<Review> readReviews(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return reviewService.readAll(page, size, sortBy);
	}

	//read by Id
	@GetMapping("/{id}")
	public Review readReview(@PathVariable UUID id) throws Exception {
		return reviewService.readById(id);
	}

	//read TEST
	@GetMapping("/test")
	//@PostAuthorize("hasAuthority('DOGOWNER')")
	public String readReviewTest() {
		return "Endpoint di Review funzionante!!!";
	}

	//***** UPDATE *****
	@PutMapping("/{id}")
	@PostAuthorize("hasAuthority('DOGOWNER')")
	public Review updateReview(@PathVariable UUID id, @RequestBody @Validated ReviewCreatePayload body) throws Exception {
		return reviewService.updateById(id, body);
	}

	//***** DELETE *****
	@DeleteMapping("/{id}")
	@PostAuthorize("hasAuthority('DOGOWNER')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReview(@PathVariable UUID id) throws NotFoundException {
		reviewService.deleteById(id);
	}

}
