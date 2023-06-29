package org.antoniotrentin.epidogsitting.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
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
@Table(name = "services")
public class Service {

	@Id
	@GeneratedValue
	private UUID id;
	private ServiceType type;
	private double price;

	@ManyToOne
	private DogSitter dogSitter;

	// costruttore
	public Service(ServiceType type, double price, DogSitter dogSitter) {
		this.type = type;
		this.price = price;
		this.dogSitter = dogSitter;
	}

}
