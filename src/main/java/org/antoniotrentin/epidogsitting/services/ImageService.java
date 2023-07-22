package org.antoniotrentin.epidogsitting.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Image;
import org.antoniotrentin.epidogsitting.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageService {

	@Autowired
	ImageRepository imageRepo;

	//	// ***** READ *****
	//	// read by Id
	//	public Image readById(UUID id) throws NotFoundException {
	//		return imageRepo.findById(id).orElseThrow(() -> new NotFoundException("Immagine non trovata"));
	//	}
	//
	//	//***** DELETE *****
	//	public void deleteById(UUID id) throws NotFoundException {
	//		Image imageFound = this.readById(id);
	//
	//		imageRepo.delete(imageFound);
	//	}

	//	//***** CREATE *****
	//	@PostMapping("/{id}")
	//	@ResponseStatus(HttpStatus.CREATED)
	//	public Image createImage(@PathVariable UUID id, @RequestBody @Validated ImageCreatePayload body) {
	//		return addressService.create(id, body);
	//	}
	//
	//	//***** READ *****
	//	@GetMapping("")
	//	public Page<Image> readImage(@RequestParam(defaultValue = "0") int page,
	//			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
	//		return addressService.readAll(page, size, sortBy);
	//	}
	//
	//	//read by Id
	//	@GetMapping("/{id}")
	//	public Image readImage(@PathVariable UUID id) throws Exception {
	//		return addressService.readById(id);
	//	}
	//
	//
	//	//	//***** UPDATE *****
	//	//	@PutMapping("/{id}")
	//	//	public Address updateAddress(@PathVariable UUID id, @RequestBody @Validated AddressCreatePayload body)
	//	//			throws Exception {
	//	//		return addressService.updateById(id, body);
	//	//	}
	//
	//	//***** DELETE *****
	//	@DeleteMapping("/{id}")
	//	@ResponseStatus(HttpStatus.NO_CONTENT)
	//	public void deleteImage(@PathVariable UUID id) throws NotFoundException {
	//		addressService.deleteById(id);
	//	}

	//	QUI SOTTO GIACIONO LE FUNZIONI EREDITATE
	//
	public List<Image> list() {
		return imageRepo.findByOrderById();
	}

	public Optional<Image> getOne(UUID id) {
		return imageRepo.findById(id);
	}

	public Image save(Image image) {
		return imageRepo.save(image);
	}

	public void delete(UUID id) {
		imageRepo.deleteById(id);
	}

	public boolean exists(UUID id) {
		return imageRepo.existsById(id);
	}
}
