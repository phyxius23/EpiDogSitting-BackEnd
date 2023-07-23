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

	public User findById(UUID id) throws NotFoundException {
		return userRepo.findById(id).orElseThrow(() -> new NotFoundException("Utente con Id:" + id + " non trovato!!"));
	}

	public User findByEmail(String email) throws NotFoundException {
		return userRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente:" + email + " non trovato!!"));
	}

}
