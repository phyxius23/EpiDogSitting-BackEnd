package org.antoniotrentin.epidogsitting.repositories;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Offering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferingRepository extends JpaRepository<Offering, UUID> {

}
