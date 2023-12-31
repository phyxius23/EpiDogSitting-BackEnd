package org.antoniotrentin.epidogsitting.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "offerings")
public class Offering {

	@Id
	@GeneratedValue
	private UUID id;
	@Enumerated(EnumType.STRING)
	private OfferingType type;
	private double price;

	@ManyToOne
	@JsonBackReference
	private DogSitter dogSitter;

	// costruttore
	public Offering(OfferingType type, double price, DogSitter dogSitter) {
		this.type = type;
		this.price = price;
		this.dogSitter = dogSitter;
	}

}
