package org.antoniotrentin.epidogsitting.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dogowners")
public class DogOwner extends User {

	@OneToMany(mappedBy = "dogOwner")
	private List<Dog> dogs;

	@OneToMany(mappedBy = "dogOwner")
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "dogOwner")
	private List<Review> reviews;

	@OneToMany(mappedBy = "dogOwner")
	private List<Booking> bookings;

	// costruttore
	public DogOwner(String name, String surname, String email, String password, Address address) {
		super(name, surname, email, password, address);
		setRole(Role.DOGOWNER);
	}

}
