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
	private LocalDateTime startingDate;
	private LocalDateTime endingDate;
	@Enumerated(EnumType.STRING)
	private StateBooking state;
	private String message;
	private double price;

	@OneToOne
	private Offering offering;

	@ManyToOne
	private DogSitter dogSitter;

	@ManyToOne
	private DogOwner dogOwner;

	// costruttore
	public Booking(LocalDateTime startingDate, LocalDateTime endingDate, StateBooking state, String message, double price,
			Offering offering, DogSitter dogSitter, DogOwner dogOwner) {
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.state = state;
		this.message = message;
		this.price = price;
		this.offering = offering;
		this.dogSitter = dogSitter;
		this.dogOwner = dogOwner;
	}

}
