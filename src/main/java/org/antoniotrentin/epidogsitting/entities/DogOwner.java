package org.antoniotrentin.epidogsitting.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "dogowners")
public class DogOwner extends User {

	// costruttore
	public DogOwner(String name, String surname, String email, String password, Address address) {
		// TODO Auto-generated constructor stub
		super(name, surname, email, password, address);
		setRole(Role.DOGOWNER);
	}

}
