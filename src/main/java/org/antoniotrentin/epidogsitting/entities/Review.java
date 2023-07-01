package org.antoniotrentin.epidogsitting.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "reviews")
public class Review {

	@Id
	@GeneratedValue
	private UUID id;

	private int rate;
	private String comment;

	@ManyToOne
	@JsonBackReference
	private DogSitter dogSitter;

	@ManyToOne
	@JsonBackReference
	private DogOwner dogOwner;

	// costruttore
	public Review(int rate, String comment, DogSitter dogSitter, DogOwner dogOwner) {
		this.rate = rate;
		this.comment = comment;
		this.dogSitter = dogSitter;
		this.dogOwner = dogOwner;
	}

}
