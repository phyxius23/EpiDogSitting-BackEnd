package org.antoniotrentin.epidogsitting.controllers;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Address;
import org.antoniotrentin.epidogsitting.entities.payloads.AddressCreatePayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
@PreAuthorize("hasAuthority('DOGSITTER') or hasAuthority('DOGOWNER')")
public class AddressController {

	@Autowired
	AddressService addressService;

	//***** CREATE ***** FUNZIONANTE A METÀ
	//	@PostMapping("")
	//	@ResponseStatus(HttpStatus.CREATED)
	//	public Address createAddress(@RequestBody @Validated AddressCreatePayload body) {
	//		return addressService.create(body);
	//	}

	//***** CREATE *****
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Address createAddress(@PathVariable UUID id, @RequestBody @Validated AddressCreatePayload body) {
		return addressService.create(id, body);
	}

	//***** READ *****
	@GetMapping("")
	public Page<Address> readAddresses(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return addressService.readAll(page, size, sortBy);
	}

	//read by Id
	@GetMapping("/{id}")
	public Address readAddress(@PathVariable UUID id) throws Exception {
		return addressService.readById(id);
	}

	//	//read TEST
	//	@GetMapping("/test")
	//	public String readAddressTest() {
	//		return "Endpoint di Address funzionante!!!";
	//	}

	//	//***** UPDATE *****
	//	@PutMapping("/{id}")
	//	public Address updateAddress(@PathVariable UUID id, @RequestBody @Validated AddressCreatePayload body)
	//			throws Exception {
	//		return addressService.updateById(id, body);
	//	}

	//***** DELETE *****
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAddress(@PathVariable UUID id) throws NotFoundException {
		addressService.deleteById(id);
	}

}
