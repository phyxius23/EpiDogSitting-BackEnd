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
@Table(name = "dogsitters")
public class DogSitter extends User {

	@OneToMany(mappedBy = "dogSitter")
	private List<Service> services;

	@OneToMany(mappedBy = "dogSitter")
	private List<Review> reviews;

	@OneToMany(mappedBy = "dogSitter")
	private List<Booking> bookings;

	// costruttore
	public DogSitter(String name, String surname, String email, String password, Address address) {
		super(name, surname, email, password, address);
		setRole(Role.DOGSITTER);
	}

}
