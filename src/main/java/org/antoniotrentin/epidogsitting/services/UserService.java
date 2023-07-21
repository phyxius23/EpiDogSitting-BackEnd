package org.antoniotrentin.epidogsitting.services;

import java.util.UUID;

import org.antoniotrentin.epidogsitting.entities.User;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	//	public User create(UserCreatePayload u) {
	//		userRepo.findByEmail(u.getEmail()).ifPresent(user -> {
	//			throw new BadRequestException("Email " + user.getEmail() + " already in use!");
	//		});
	//		User newUser = new User(u.getName(), u.getSurname(), u.getUserName(), u.getEmail(), u.getPassword());
	//		return userRepo.save(newUser);
	//	}

	//	public Page<User> find(int page, int size, String sortBy) {
	//		if (size < 0)
	//			size = 10;
	//		if (size > 100)
	//			size = 100;
	//		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	//
	//		return userRepo.findAll(pageable);
	//	}

	public User findById(UUID id) throws NotFoundException {
		return userRepo.findById(id).orElseThrow(() -> new NotFoundException("Utente con Id:" + id + " non trovato!!"));
	}
	//
	//	public User findByEmail(String email) throws NotFoundException {
	//		return userRepo.findByEmail(email)
	//				.orElseThrow(() -> new NotFoundException("Utente con email:" + email + "non trovato!!"));
	//	}

	//	public User findByUserName(String username) throws NotFoundException {
	//		return userRepo.findByUserName(username)
	//				.orElseThrow(() -> new NotFoundException("Utente:" + username + "non trovato!!"));
	//	}

	public User findByEmail(String email) throws NotFoundException {
		return userRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente:" + email + " non trovato!!"));
	}

	//	public User findByIdAndUpdate(UUID id, User u) throws NotFoundException {
	//		User found = this.findById(id);
	//
	//		found.setId(id);
	//		found.setName(u.getName());
	//		found.setSurname(u.getSurname());
	//		found.setEmail(u.getEmail());
	//
	//		return userRepo.save(found);
	//	}

	//	public void findByIdAndDelete(UUID id) throws NotFoundException {
	//		User found = this.findById(id);
	//		userRepo.delete(found);
	//	}

}
