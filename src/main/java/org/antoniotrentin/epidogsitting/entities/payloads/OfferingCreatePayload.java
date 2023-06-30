package org.antoniotrentin.epidogsitting.entities.payloads;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferingCreatePayload {

	@NotNull(message = "Il tipo di servizio è obbligatorio")
	String type;;
	@NotNull(message = "Il prezzo è obbligatorio")
	double price;
	@NotNull(message = "Il dogsitter è obbligatorio")
	UUID dogSitter;

	// costruttore
	public OfferingCreatePayload(@NotNull(message = "Il tipo di servizio è obbligatorio") String type,
			@NotNull(message = "Il prezzo è obbligatorio") double price,
			@NotNull(message = "Il dogsitter è obbligatorio") UUID dogSitter) {
		this.type = type;
		this.price = price;
		this.dogSitter = dogSitter;
	}

}
