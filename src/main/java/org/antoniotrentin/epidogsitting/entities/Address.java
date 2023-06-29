package org.antoniotrentin.epidogsitting.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue
	private UUID id;
	private String street;
	private String city;
	private String province;
	private int postalCode;

	// costruttore
	public Address(String street, String city, String province, int postalCode) {
		this.street = street;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
	}
}
