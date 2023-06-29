package org.antoniotrentin.epidogsitting.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "dogsitters")
public class DogSitter extends User {

	// costruttore
	public DogSitter(String name, String surname, String email, String password, Address address) {
		// TODO Auto-generated constructor stub
		super(name, surname, email, password, address);
		setRole(Role.DOGSITTER);
	}

	@Override
	public String toString() {
		return "DogSitter [getAuthorities()=" + getAuthorities() + ", getUsername()=" + getUsername()
				+ ", isAccountNonExpired()=" + isAccountNonExpired() + ", isAccountNonLocked()=" + isAccountNonLocked()
				+ ", isCredentialsNonExpired()=" + isCredentialsNonExpired() + ", isEnabled()=" + isEnabled()
				+ ", getPassword()=" + getPassword() + ", getId()=" + getId() + ", getName()=" + getName() + ", getSurname()="
				+ getSurname() + ", getEmail()=" + getEmail() + ", getRole()=" + getRole() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
