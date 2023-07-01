package org.antoniotrentin.epidogsitting.entities.payloads;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

//@Getter
//@Setter
@Data
public class BookingCreatePayload {

	@NotNull(message = "Data di inizio servizio obbligatoria")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") // Formato desiderato per la data e l'ora
	LocalDateTime startingDate;
	@NotNull(message = "Data di inizio servizio obbligatoria")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") // Formato desiderato per la data e l'ora
	LocalDateTime endingDate;
	@NotNull(message = "Lo stato della fattura è obbligatorio")
	String state;
	@NotNull(message = "Il messaggio è obbligatorio")
	String message;
	@NotNull(message = "Il prezzo è obbligatorio")
	double price;
	@NotNull(message = "Il proprietario è obbligatorio")
	UUID dogOwner;
	@NotNull(message = "Il dogsitter è obbligatorio")
	UUID dogSitter;
	@NotNull(message = "Il servizio è obbligatorio")
	UUID offering;

	public BookingCreatePayload(@NotNull(message = "Data di inizio servizio obbligatoria") LocalDateTime startingDate,
			@NotNull(message = "Data di inizio servizio obbligatoria") LocalDateTime endingDate,
			@NotNull(message = "Lo stato della fattura è obbligatorio") String state,
			@NotNull(message = "Il messaggio è obbligatorio") String message,
			@NotNull(message = "Il prezzo è obbligatorio") double price,
			@NotNull(message = "Il proprietario è obbligatorio") UUID dogOwner,
			@NotNull(message = "Il dogsitter è obbligatorio") UUID dogSitter,
			@NotNull(message = "Il servizio è obbligatorio") UUID offering) {
		super();
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.state = state;
		this.message = message;
		this.price = price;
		this.dogOwner = dogOwner;
		this.dogSitter = dogSitter;
		this.offering = offering;
	}

}
