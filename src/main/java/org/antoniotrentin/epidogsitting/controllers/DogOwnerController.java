package org.antoniotrentin.epidogsitting.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.antoniotrentin.epidogsitting.entities.Address;
import org.antoniotrentin.epidogsitting.entities.Dog;
import org.antoniotrentin.epidogsitting.entities.DogOwner;
import org.antoniotrentin.epidogsitting.entities.Image;
import org.antoniotrentin.epidogsitting.entities.User;
import org.antoniotrentin.epidogsitting.entities.payloads.AddressCreatePayload;
import org.antoniotrentin.epidogsitting.entities.payloads.DogCreatePayload;
import org.antoniotrentin.epidogsitting.entities.payloads.DogOwnerCreatePayload;
import org.antoniotrentin.epidogsitting.entities.payloads.Message;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.repositories.ImageRepository;
import org.antoniotrentin.epidogsitting.services.AddressService;
import org.antoniotrentin.epidogsitting.services.CloudinaryService;
import org.antoniotrentin.epidogsitting.services.DogOwnerService;
import org.antoniotrentin.epidogsitting.services.DogService;
import org.antoniotrentin.epidogsitting.services.ImageService;
import org.antoniotrentin.epidogsitting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/dogowner")
@PreAuthorize("hasAuthority('DOGOWNER')")
public class DogOwnerController {

	@Autowired
	DogOwnerService dogOwnerService;

	@Autowired
	UserService userService;

	@Autowired
	AddressService addressService;

	@Autowired
	DogService dogService;

	@Autowired
	CloudinaryService cloudinaryService;

	@Autowired
	ImageService imageService;

	@Autowired
	ImageRepository imageRepository;

	/**
	 * *** CREATE, READ, UPDATE, DELETE ***
	 * *** ********* DOGOWNER ********* ***
	 */

	//***** CREATE *****
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public DogOwner createDogOwner(@RequestBody @Validated DogOwnerCreatePayload body) {
		return dogOwnerService.create(body);
	}

	//***** READ *****

	// read all
	@GetMapping("")
	public Page<DogOwner> readDogOwners(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return dogOwnerService.readAll(page, size, sortBy);
	}

	//read by Id
	@GetMapping("/{id}")
	public DogOwner readDogOwner(@PathVariable UUID id) throws Exception {
		return dogOwnerService.readById(id);
	}

	//read myProfile
	@GetMapping("/me")
	public ResponseEntity<User> getUserProfile() {

		// Recupera l'utente corrente dal sistema di autenticazione
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();

		// Recupera i dettagli dell'utente dal database o da un altro servizio
		User user = userService.findByEmail(email);

		// Restituisci i dettagli dell'utente come risposta
		return ResponseEntity.ok(user);
	}

	//***** UPDATE *****
	@PutMapping("/{id}")
	public DogOwner updateDogOwner(@PathVariable UUID id, @RequestBody @Validated DogOwnerCreatePayload body)
			throws Exception {
		return dogOwnerService.updateById(id, body);
	}

	//***** DELETE *****
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDogOwner(@PathVariable UUID id) throws NotFoundException {
		dogOwnerService.deleteById(id);
	}

	/**
	 * *** CREATE, READ, UPDATE, DELETE ***
	 * *** ********* ADDRESS ********** ***
	 */

	//***** CREATE *****
	@PostMapping("/{userId}/address")
	@ResponseStatus(HttpStatus.CREATED)
	public Address createAddress(@PathVariable UUID userId, @RequestBody @Validated AddressCreatePayload body) {
		return addressService.create(userId, body);
	}

	//***** UPDATE *****
	@PutMapping("/{userId}/address/{addressId}")
	public Address updateAddress(@PathVariable UUID userId, @PathVariable UUID addressId,
			@RequestBody @Validated AddressCreatePayload body) throws Exception {
		return addressService.updateById(userId, addressId, body);
	}

	/**
	 * *** CREATE, READ, UPDATE, DELETE ***
	 * *** *********** DOG ************ ***
	 */

	//***** CREATE *****
	@PostMapping("/{dogownerId}/dog")
	@ResponseStatus(HttpStatus.CREATED)
	public Dog createDog(@PathVariable UUID dogownerId, @RequestBody @Validated DogCreatePayload body) {
		return dogService.create(dogownerId, body);
	}

	//***** UPDATE *****
	@PutMapping("/{dogownerId}/dog/{dogId}")
	public Dog updateDog(@PathVariable UUID dogownerId, @PathVariable UUID dogId,
			@RequestBody @Validated DogCreatePayload body) throws Exception {
		return dogService.updateById(dogownerId, dogId, body);
	}

	/**
	 * *** CREATE, READ, UPDATE, DELETE ***
	 * *** ********* IMAGE **********
	 */

	//***** CREATE *****
	@PostMapping("/{userId}/image/upload")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> uploadImage(@PathVariable UUID userId,
			@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());

		if (bi == null) {
			return new ResponseEntity(new Message("Immagine non valida"), HttpStatus.BAD_REQUEST);
		}
		Map result = cloudinaryService.upload(multipartFile);

		User userFound = userService.findById(userId);

		Image image = new Image((String) result.get("original_filename"), (String) result.get("url"),
				(String) result.get("public_id"), userFound);
		Image ImageSaved = imageService.save(image);
		System.out.println(image);

		return new ResponseEntity(ImageSaved, HttpStatus.OK);
	}

}
