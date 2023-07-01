package org.antoniotrentin.epidogsitting.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dogs")
public class Dog {

	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	private int age;
	private String breed;
	private int weight;
	private String description;

	//@JsonIgnore
	//@JsonBackReference
	//@JsonManagedReference
	@ManyToOne
	@JsonManagedReference
	private DogOwner dogOwner;

	// costruttore
	public Dog(String name, int age, String breed, int weight, String description, DogOwner dogOwner) {
		this.name = name;
		this.age = age;
		this.breed = breed;
		this.weight = weight;
		this.description = description;
		this.dogOwner = dogOwner;
	}

}
