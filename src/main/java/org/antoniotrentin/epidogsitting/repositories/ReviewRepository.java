package org.antoniotrentin.epidogsitting.repositories;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

}
