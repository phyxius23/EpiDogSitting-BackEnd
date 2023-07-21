package org.antoniotrentin.epidogsitting.auth;

import org.antoniotrentin.epidogsitting.auth.payloads.AuthenticationSuccessfullPayload;
import org.antoniotrentin.epidogsitting.entities.DogOwner;
import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.entities.User;
import org.antoniotrentin.epidogsitting.entities.payloads.DogOwnerCreatePayload;
import org.antoniotrentin.epidogsitting.entities.payloads.DogSitterCreatePayload;
import org.antoniotrentin.epidogsitting.entities.payloads.UserLoginPayload;
import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
import org.antoniotrentin.epidogsitting.exceptions.UnauthorizedException;
import org.antoniotrentin.epidogsitting.services.AddressService;
import org.antoniotrentin.epidogsitting.services.DogOwnerService;
import org.antoniotrentin.epidogsitting.services.DogSitterService;
import org.antoniotrentin.epidogsitting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService userService;

	@Autowired
	DogSitterService dogSitterService;

	@Autowired
	DogOwnerService dogOwnerService;

	@Autowired
	AddressService addressService;

	@Autowired
	private PasswordEncoder bcrypt;

	@PostMapping("/register/dogsitters")
	public ResponseEntity<DogSitter> register(@RequestBody @Validated DogSitterCreatePayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));

		DogSitter createdDogSitter = dogSitterService.create(body);

		createdDogSitter.setPassword(body.getPassword());

		return new ResponseEntity<DogSitter>(createdDogSitter, HttpStatus.CREATED);
	}

	@PostMapping("/register/dogowners")
	public ResponseEntity<DogOwner> register(@RequestBody @Validated DogOwnerCreatePayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));

		DogOwner createdDogOwner = dogOwnerService.create(body);

		createdDogOwner.setPassword(body.getPassword());

		return new ResponseEntity<DogOwner>(createdDogOwner, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body)
			throws NotFoundException {

		//User user = userService.findByUserName(body.getUserName());
		User user = userService.findByEmail(body.getEmail());

		String plainPW = body.getPassword();
		String hashedPW = user.getPassword();

		if (!bcrypt.matches(plainPW, hashedPW))
			throw new UnauthorizedException("Credenziali non valide");

		String token = JWTTools.createToken(user);
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}
