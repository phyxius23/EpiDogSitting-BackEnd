package org.antoniotrentin.epidogsitting.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue
	private UUID id;
	private LocalDateTime starting;
	private LocalDateTime ending;
	@Enumerated(EnumType.STRING)
	private StateBooking state;
	private String message;
	private double price;

	@OneToOne
	private Service service;

	@ManyToOne
	private DogSitter dogSitter;

	@ManyToOne
	private DogOwner dogOwner;

	// costruttore
	public Booking(LocalDateTime starting, LocalDateTime ending, String message, double price, DogOwner dogOwner,
			DogSitter dogSitter) {
		this.starting = starting;
		this.ending = ending;
		this.state = StateBooking.IN_ATTESA;
		this.message = message;
		this.price = price;
		this.dogOwner = dogOwner;
		this.dogSitter = dogSitter;
	}

}
