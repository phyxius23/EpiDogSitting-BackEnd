package org.antoniotrentin.epidogsitting.repositories;

import java.util.Optional;
import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogSitterRepository extends JpaRepository<DogSitter, UUID> {

	Optional<DogSitter> findByEmail(String email);

	Optional<DogSitter> findByName(String name);

	Page<DogSitter> findByNameContaining(String name, Pageable pageable);

	//Page<DogSitter> findByAddressContaining(@Nullable Address address, Pageable pageable);

	//List<DogSitter> findByAddressPostalCode(String postalCode);

	Page<DogSitter> findByAddressPostalCode(String postalCode, Pageable pageable);

	//	@Query("SELECT ds FROM dogsitters ds JOIN FETCH t1.tabella2List t2 WHERE t1.id = :id")
	//  DogSitter findTabella1AndTabella2ById(@Param("id") Long id);
	//	@Query("SELECT ds FROM DogSitter ds WHERE c.address = :provincia")
	//	List<DogSitter> findByPostalCode(@Param("provincia") String provincia);

}
