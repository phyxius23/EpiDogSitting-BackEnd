package org.antoniotrentin.epidogsitting.repositories;

import java.util.List;
import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
	List<Image> findByOrderById();
}
