package org.antoniotrentin.epidogsitting.entities.payloads;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AddressCreatePayload {

	@NotNull(message = "La via è richiesta")
	String street;

	@NotNull(message = "La città è richiesta")
	String city;

	@NotNull(message = "La provincia è richiesta")
	String province;

	@Pattern(regexp = "\\d{5}", message = "Il CAP deve essere composto da 5 cifre")
	String postalCode;

	@NotNull(message = "L'id dell'utente è obbligatorio")
	UUID user;

	public AddressCreatePayload(@NotNull(message = "La via è richiesta") String street,
			@NotNull(message = "La città è richiesta") String city,
			@NotNull(message = "La provincia è richiesta") String province,
			@Pattern(regexp = "\\d{5}", message = "Il CAP deve essere composto da 5 cifre") String postalCode,
			@NotNull(message = "L'id dell'utente è obbligatorio") UUID user) {
		this.street = street;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode; // regex non funzionante
		this.user = user;
	}
	//	public AddressCreatePayload(@NotNull(message = "La via è richiesta") String street,
	//			@NotNull(message = "La città è richiesta") String city,
	//			@NotNull(message = "La provincia è richiesta") String province,
	//			@Pattern(regexp = "\\d{5}", message = "Il CAP deve essere composto da 5 cifre") String postalCode) {
	//		this.street = street;
	//		this.city = city;
	//		this.province = province;
	//		this.postalCode = postalCode; // regex non funzionante
	//	}

}
