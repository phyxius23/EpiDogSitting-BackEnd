package org.antoniotrentin.epidogsitting.controllers;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Booking;
import org.antoniotrentin.epidogsitting.entities.payloads.BookingCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/bookings")
@PreAuthorize("hasAuthority('DOGSITTER') or hasAuthority('DOGOWNER')")
public class BookingController {

	@Autowired
	BookingService bookingService;

	//***** CREATE *****
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Booking createBooking(@RequestBody @Validated BookingCreatePayload body) {
		return bookingService.create(body);
	}

	//***** READ *****
	@GetMapping("")
	public Page<Booking> readBookings(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return bookingService.readAll(page, size, sortBy);
	}

	//read by Id
	@GetMapping("/{id}")
	public Booking readBooking(@PathVariable UUID id) throws Exception {
		return bookingService.readById(id);
	}

	//read TEST
	@GetMapping("/test")
	public String readBookingTest() {
		return "Endpoint di Booking funzionante!!!";
	}

	//***** UPDATE *****
	@PutMapping("/{id}")
	public Booking updateBooking(@PathVariable UUID id, @RequestBody @Validated BookingCreatePayload body)
			throws Exception {
		return bookingService.updateById(id, body);
	}

	//***** DELETE *****
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBooking(@PathVariable UUID id) throws NotFoundException {
		bookingService.deleteById(id);
	}

}
