package org.antoniotrentin.epidogsitting.entities.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AddressCreatePayload {

	@NotNull(message = "La via è richiesta")
	//@Size(min = 3, max = 30, message = "Street must be between 3 and 30 characters")
	String street;

	@NotNull(message = "La città è richiesta")
	String city;

	@NotNull(message = "La provincia è richiesta")
	String province;

	//@NotNull(message = "Il CAP è richiesto")
	@Pattern(regexp = "\\d{5}", message = "Il CAP deve essere composto da 5 cifre")
	//@Size(min = 5, max = 5, message = "Il CAP deve essere di 5 numeri")
	String postalCode;

	public AddressCreatePayload(@NotNull(message = "La via è richiesta") String street,
			@NotNull(message = "La città è richiesta") String city,
			@NotNull(message = "La provincia è richiesta") String province,
			@Pattern(regexp = "\\d{5}", message = "Il CAP deve essere composto da 5 cifre") String postalCode) {
		this.street = street;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode; // regex non funzionante
	}

}
