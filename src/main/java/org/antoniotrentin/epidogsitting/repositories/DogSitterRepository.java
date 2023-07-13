package org.antoniotrentin.epidogsitting.repositories;

import java.util.Optional;
import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.entities.OfferingType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DogSitterRepository extends JpaRepository<DogSitter, UUID> {

	Optional<DogSitter> findByEmail(String email);

	Optional<DogSitter> findByName(String name);

	Page<DogSitter> findByAddressPostalCode(String postalCode, Pageable pageable);

	Page<DogSitter> findByNameContaining(String name, Pageable pageable);

	@Query("SELECT d FROM DogSitter d JOIN d.offerings o WHERE o.type = :offeringType")
	Page<DogSitter> findByOfferingType(@Param("offeringType") OfferingType offeringType, Pageable pageable);

	@Query("SELECT d FROM DogSitter d JOIN d.address a WHERE a.postalCode = :postalCode AND d.name = :name")
	Page<DogSitter> findByByAddressPostalCodeByName(@Param("postalCode") String postalCode, @Param("name") String name,
			Pageable pageable);

	@Query("SELECT d FROM DogSitter d JOIN d.offerings o JOIN d.address a WHERE a.postalCode = :postalCode AND o.type = :offeringType")
	Page<DogSitter> findByByAddressPostalCodeByOfferingType(@Param("postalCode") String postalCode,
			@Param("offeringType") OfferingType offeringType, Pageable pageable);

	//Page<DogSitter> findByAddressPostalCode(String postalCode);

	//Page<DogSitter> findByAddressContaining(@Nullable Address address, Pageable pageable);

	//List<DogSitter> findByAddressPostalCode(String postalCode);

	//	Page<DogSitter> findByOfferingType(String type, Pageable pageable);

	//	@Query("SELECT d FROM DogSitter d JOIN d.offerings o WHERE o.type = :offeringType")
	//	Page<DogSitter> findByOfferingType(@Param("offeringType") OfferingType offeringType, Pageable pageable);

	//	@Query("SELECT d FROM DogSitter d JOIN d.offerings o WHERE o.type = :offeringType")
	//  @Query("SELECT d FROM DogSitter d JOIN d.offerings o WHERE o.type = :offeringType IN (SELECT d FROM DogSitter d JOIN d.address a WHERE a.postalCode = :postalCode)")
	//	@Query("SELECT d FROM DogSitter d JOIN d.offerings o JOIN d.address a WHERE a.postalCode = :postalCode AND o.type = :offeringType")
	//	Page<DogSitter> findByByAddressPostalCodeByOfferingType(@Param("postalCode") String postalCode,
	//			@Param("offeringType") OfferingType offeringType, Pageable pageable);

}
