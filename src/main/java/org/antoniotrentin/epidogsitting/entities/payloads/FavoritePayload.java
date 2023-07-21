package org.antoniotrentin.epidogsitting.entities.payloads;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoritePayload {

	@NotNull(message = "Il dogsitter è obbligatorio")
	UUID dogSitter;
	@NotNull(message = "Il proprietario è obbligatorio")
	UUID dogOwner;

	// costruttore
	public FavoritePayload(@NotNull(message = "Il dogsitter è obbligatorio") UUID dogSitter,
			@NotNull(message = "Il proprietario è obbligatorio") UUID dogOwner) {
		super();
		this.dogSitter = dogSitter;
		this.dogOwner = dogOwner;
	}

}
