package org.antoniotrentin.epidogsitting.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

	//@JsonManagedReference
	//@JsonBackReference
	//(mappedBy = "dogOwner")
	@OneToMany(mappedBy = "dogOwner", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Dog> dogs;

	@OneToMany(mappedBy = "dogOwner", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	//(mappedBy = "dogOwner")
	@JsonManagedReference
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "dogOwner", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	//(mappedBy = "dogOwner")
	@JsonManagedReference
	private List<Review> reviews;

	@OneToMany(mappedBy = "dogOwner", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	//(mappedBy = "dogOwner")
	@JsonManagedReference
	private List<Booking> bookings;

	// costruttore
	public DogOwner(String name, String surname, String email, String password) {
		super(name, surname, email, password);
		setRole(Role.DOGOWNER);
		this.dogs = new ArrayList<>();
		this.favorites = new ArrayList<>();
		this.reviews = new ArrayList<>();
		this.bookings = new ArrayList<>();
	}

}
