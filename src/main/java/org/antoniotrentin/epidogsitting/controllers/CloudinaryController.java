package org.antoniotrentin.epidogsitting.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.antoniotrentin.epidogsitting.entities.Dog;
import org.antoniotrentin.epidogsitting.entities.Image;
import org.antoniotrentin.epidogsitting.entities.payloads.Message;
import org.antoniotrentin.epidogsitting.services.CloudinaryService;
import org.antoniotrentin.epidogsitting.services.DogOwnerService;
import org.antoniotrentin.epidogsitting.services.DogService;
import org.antoniotrentin.epidogsitting.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@PreAuthorize("hasAuthority('DOGSITTER') or hasAuthority('DOGOWNER')")
@CrossOrigin
public class CloudinaryController {

	@Autowired
	CloudinaryService cloudinaryService;

	@Autowired
	ImageService imageService;

	@Autowired
	DogService dogService;

	@Autowired
	DogOwnerService dogOwnerService;

	@GetMapping("/list")
	public ResponseEntity<List<Image>> list() {
		List<Image> list = imageService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	//***** CREATE IMAGE TO DOG *****
	@PostMapping("/{dogId}/image/upload")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> uploadImageDog(@PathVariable UUID dogId,
			@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());

		if (bi == null) {
			return new ResponseEntity(new Message("Immagine non valida"), HttpStatus.BAD_REQUEST);
		}
		Map result = cloudinaryService.upload(multipartFile);

		//		DogOwner dogOwnerFound = dogOwnerService.readById(userId);
		Dog dogFound = dogService.readById(dogId);

		Image image = new Image((String) result.get("original_filename"), (String) result.get("url"),
				(String) result.get("public_id"), dogFound);
		Image ImageSaved = imageService.save(image);
		System.out.println(image);

		return new ResponseEntity(ImageSaved, HttpStatus.OK);
	}

	//***** UPDATE IMAGE TO DOG *****
	@PutMapping("/{dogId}/update/{imageId}/image/upload")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateImage(@PathVariable UUID dogId, @PathVariable("imageId") UUID imageId,
			@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {

		// qui mi occupo della cancellazione della precedente immagine
		if (!imageService.exists(imageId))
			return new ResponseEntity(new Message("L'immagine non esiste"), HttpStatus.NOT_FOUND);

		Image imageFounded = imageService.getOne(imageId).get();

		Map resultDelete = cloudinaryService.delete(imageFounded.getImageId());

		imageService.delete(imageId);

		// qui mi occupo del salvataggio della nuova immagine	
		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());

		if (bi == null) {
			return new ResponseEntity(new Message("Immagine non valida"), HttpStatus.BAD_REQUEST);
		}
		Map resultCreate = cloudinaryService.upload(multipartFile);

		Dog dogFound = dogService.readById(dogId);

		Image image = new Image((String) resultCreate.get("original_filename"), (String) resultCreate.get("url"),
				(String) resultCreate.get("public_id"), dogFound);
		Image ImageSaved = imageService.save(image);

		return new ResponseEntity(ImageSaved, HttpStatus.OK);
	}

	//***** DELETE *****
	@DeleteMapping("/delete/{imageId}")
	public ResponseEntity<?> delete(@PathVariable("imageId") UUID imageId) throws IOException {

		if (!imageService.exists(imageId))
			return new ResponseEntity(new Message("L'immagine non esiste"), HttpStatus.NOT_FOUND);

		Image image = imageService.getOne(imageId).get();

		Map result = cloudinaryService.delete(image.getImageId());

		imageService.delete(imageId);

		return new ResponseEntity(new Message("L'immagine è stata eliminata"), HttpStatus.OK);
	}
}
