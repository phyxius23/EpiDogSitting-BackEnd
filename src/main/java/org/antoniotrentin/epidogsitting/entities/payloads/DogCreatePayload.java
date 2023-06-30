package org.antoniotrentin.epidogsitting.entities.payloads;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DogCreatePayload {

	@NotNull(message = "Il nome è obbligatorio")
	@Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
	String name;
	@NotNull(message = "L'età è obbligatoria")
	int age;
	@NotNull(message = "La razza è obbligatoria")
	String breed;
	@NotNull(message = "Il peso è obbligatorio")
	int weight;
	@NotNull(message = "La descrizione è obbligatoria")
	String description;
	@NotNull(message = "È obbligatorio impostare il proprietario")
	UUID dogOwner;

	public DogCreatePayload(
			@NotNull(message = "Il nome è obbligatorio") @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30") String name,
			@NotNull(message = "L'età è obbligatoria") int age, @NotNull(message = "La razza è obbligatoria") String breed,
			@NotNull(message = "Il peso è obbligatorio") int weight,
			@NotNull(message = "La descrizione è obbligatoria") String description,
			@NotNull(message = "È obbligatorio impostare il proprietario") UUID dogOwner) {
		this.name = name;
		this.age = age;
		this.breed = breed;
		this.weight = weight;
		this.description = description;
		this.dogOwner = dogOwner;
	}
}
