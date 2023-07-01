package org.antoniotrentin.epidogsitting.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	@JsonBackReference
	private List<Offering> offerings;

	@OneToMany(mappedBy = "dogSitter")
	@JsonBackReference
	private List<Review> reviews;

	@OneToMany(mappedBy = "dogSitter")
	@JsonBackReference
	private List<Booking> bookings;

	// costruttore
	public DogSitter(String name, String surname, String email, String password) {
		super(name, surname, email, password);
		setRole(Role.DOGSITTER);
		this.offerings = new ArrayList<>();
		this.reviews = new ArrayList<>();
		this.bookings = new ArrayList<>();
	}

	//	public void addFilm(Film film) {
	//		preferiti.add(film);
	//		film.getUsers().add(this);
	//	}
	//
	//	public void removeFilm(Film film) {
	//		preferiti.remove(film);
	//		film.getUsers().remove(this);
	//	}

}
