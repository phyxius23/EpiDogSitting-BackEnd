package org.antoniotrentin.epidogsitting.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
	private String postalCode;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	// costruttore
	public Address(String street, String city, String province, String postalCode, User user) {
		this.street = street;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.user = user;
	}
}
