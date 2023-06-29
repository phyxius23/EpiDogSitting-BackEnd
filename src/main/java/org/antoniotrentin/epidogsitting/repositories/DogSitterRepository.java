package org.antoniotrentin.epidogsitting.repositories;

import java.util.Optional;
import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogSitterRepository extends JpaRepository<DogSitter, UUID> {

	public Optional<DogSitter> findByEmail(String email);

}
