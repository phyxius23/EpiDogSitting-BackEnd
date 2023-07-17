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
@Table(name = "dogsitters")
public class DogSitter extends User {

	//	private String description;

	//	@OneToMany(mappedBy = "dogSitter", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	//	@JsonManagedReference
	//	private List<Image> images = new ArrayList<>();

	@OneToMany(mappedBy = "dogSitter", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Offering> offerings = new ArrayList<>();

	@OneToMany(mappedBy = "dogSitter", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Review> reviews = new ArrayList<>();

	@OneToMany(mappedBy = "dogSitter", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Booking> bookings = new ArrayList<>();

	// costruttore
	public DogSitter(String name, String surname, String email, String password) {
		super(name, surname, email, password);
		setRole(Role.DOGSITTER);
		this.offerings = new ArrayList<>();
	}

}
