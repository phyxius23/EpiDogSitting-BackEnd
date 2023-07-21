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
	ImageRepository imageRepository;

	public List<Image> list() {
		return imageRepository.findByOrderById();
	}

	public Optional<Image> getOne(UUID id) {
		return imageRepository.findById(id);
	}

	public Image save(Image image) {
		return imageRepository.save(image);
	}

	public void delete(UUID id) {
		imageRepository.deleteById(id);
	}

	public boolean exists(UUID id) {
		return imageRepository.existsById(id);
	}
}
