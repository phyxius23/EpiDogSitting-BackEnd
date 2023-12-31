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
