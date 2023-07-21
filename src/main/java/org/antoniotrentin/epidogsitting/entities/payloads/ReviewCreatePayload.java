package org.antoniotrentin.epidogsitting.entities.payloads;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCreatePayload {
	@NotNull(message = "L'età è obbligatoria")
	@Min(1)
	@Max(5)
	//@Size(min = 1, max = 5, message = "Valutazione min 1, massimo 5")
	int rate;
	@NotNull(message = "Il commento è obbligatorio")
	String comment;
	@NotNull(message = "Il dog-sitter è obbligatorio")
	UUID dogSitter;
	@NotNull(message = "Il proprietario è obbligatorio")
	UUID dogOwner;

	public ReviewCreatePayload(
			@NotNull(message = "L'età è obbligatoria") @Size(min = 1, max = 5, message = "Valutazione min 1, massimo 5") int rate,
			@NotNull(message = "Il commento è obbligatorio") String comment,
			@NotNull(message = "Il dog-sitter è obbligatorio") UUID dogSitter,
			@NotNull(message = "Il proprietario è obbligatorio") UUID dogOwner) {
		this.rate = rate;
		this.comment = comment;
		this.dogSitter = dogSitter;
		this.dogOwner = dogOwner;
	}

}
