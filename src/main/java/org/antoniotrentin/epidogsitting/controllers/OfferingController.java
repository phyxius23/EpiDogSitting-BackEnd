package org.antoniotrentin.epidogsitting.controllers;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Offering;
import org.antoniotrentin.epidogsitting.entities.payloads.OfferingCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.services.OfferingService;
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
@RequestMapping("/services")
@PreAuthorize("hasAuthority('DOGSITTER')")
public class OfferingController {

	@Autowired
	OfferingService offeringService;

	//***** CREATE *****
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Offering createOffering(@RequestBody @Validated OfferingCreatePayload body) {
		return offeringService.create(body);
	}

	//***** READ *****
	@GetMapping("")
	public Page<Offering> readOfferings(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return offeringService.readAll(page, size, sortBy);
	}

	//read by Id
	@GetMapping("/{id}")
	public Offering readOffering(@PathVariable UUID id) throws Exception {
		return offeringService.readById(id);
	}

	//read TEST
	@GetMapping("/test")
	public String readOfferingTest() {
		return "Endpoint di Offering funzionante!!!";
	}

	//***** UPDATE *****
	@PutMapping("/{id}")
	public Offering updateOffering(@PathVariable UUID id, @RequestBody @Validated OfferingCreatePayload body)
			throws Exception {
		return offeringService.updateById(id, body);
	}

	//***** DELETE *****
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOffering(@PathVariable UUID id) throws NotFoundException {
		offeringService.deleteById(id);
	}

}
