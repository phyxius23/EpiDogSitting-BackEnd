package org.antoniotrentin.epidogsitting.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "images")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String name;
	private String imageUrl;
	private String imageId;

	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@OneToOne
	@JoinColumn(name = "dog_id")
	@JsonIgnore
	private Dog dog;

	//	@ManyToOne
	//	@JsonBackReference
	//	private DogSitter dogSitter;

	//	public Image() {
	//	}

	public Image(String n, String iu, String ii, User u) {
		this.name = n;
		this.imageUrl = iu;
		this.imageId = ii;
		this.user = u;
	}

	public Image(String n, String iu, String ii, Dog d) {
		this.name = n;
		this.imageUrl = iu;
		this.imageId = ii;
		this.dog = d;
	}

	//	public int getId() {
	//		return id;
	//	}
	//
	//	public void setId(int id) {
	//		this.id = id;
	//	}

	//	public String getName() {
	//		return name;
	//	}
	//
	//	public void setName(String name) {
	//		this.name = name;
	//	}
	//
	//	public String getImagenUrl() {
	//		return imagenUrl;
	//	}
	//
	//	public void setImagenUrl(String imagenUrl) {
	//		this.imagenUrl = imagenUrl;
	//	}
	//
	//	public String getImagenId() {
	//		return imagenId;
	//	}
	//
	//	public void setImagenId(String imagenId) {
	//		this.imagenId = imagenId;
	//	}
}
