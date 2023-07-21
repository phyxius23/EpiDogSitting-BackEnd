package org.antoniotrentin.epidogsitting.repositories;

import java.util.List;
import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
	public List<Favorite> findByDogOwnerId(UUID dogOwnerId);
}
