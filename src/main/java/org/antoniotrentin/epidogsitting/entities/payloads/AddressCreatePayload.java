package org.antoniotrentin.epidogsitting.entities.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressCreatePayload {

	@NotNull(message = "La via è richiesta")
	//@Size(min = 3, max = 30, message = "Street must be between 3 and 30 characters")
	String street;

	@NotNull(message = "La città è richiesta")
	String city;

	@NotNull(message = "La provincia è richiesta")
	String province;

	@NotNull(message = "Il CAP è richiesto")
	@Size(min = 5, max = 5, message = "Il CAP deve essere di 5 numeri")
	String postalCode;

}
