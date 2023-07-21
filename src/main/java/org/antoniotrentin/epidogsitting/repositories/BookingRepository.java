package org.antoniotrentin.epidogsitting.repositories;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

}
