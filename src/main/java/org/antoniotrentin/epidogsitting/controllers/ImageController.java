package org.antoniotrentin.epidogsitting.controllers;

import org.antoniotrentin.epidogsitting.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
@PreAuthorize("hasAuthority('DOGSITTER') or hasAuthority('DOGOWNER')")
public class ImageController {

	@Autowired
	ImageService imageService;

	//***** DELETE *****
	//	@DeleteMapping("/delete/{imageId}")
	//	@ResponseStatus(HttpStatus.NO_CONTENT)
	//	public void deleteImage(@PathVariable UUID imageId) throws NotFoundException {
	//		imageService.deleteById(imageId);
	//	}

}
