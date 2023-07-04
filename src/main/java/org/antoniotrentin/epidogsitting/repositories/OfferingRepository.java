package org.antoniotrentin.epidogsitting.repositories;

import java.util.Optional;
import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Offering;
import org.antoniotrentin.epidogsitting.entities.OfferingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferingRepository extends JpaRepository<Offering, UUID> {
	Optional<Offering> findByDogSitterIdAndType(UUID dogSitterId, OfferingType type);
}
