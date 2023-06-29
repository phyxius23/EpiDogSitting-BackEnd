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
@Table(name = "favorites")
public class Favorite {

	@Id
	@GeneratedValue
	private UUID id;

	@ManyToOne
	private DogSitter dogSitter;

	@ManyToOne
	private DogOwner dogOwner;

}
