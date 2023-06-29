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
	private PasswordEncoder bcrypt;

	//	EndPoint che testa il funzionamento
	//	@GetMapping("/register/dogsitters")
	//	public String viewMessage() {
	//		return "Ben arrivato nella pagina";
	//	}

	@PostMapping("/register/dogsitters")
	public ResponseEntity<DogSitter> register(@RequestBody @Validated DogSitterCreatePayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));

		DogSitter createdDogSitter = dogSitterService.create(body);

		createdDogSitter.setPassword(body.getPassword());

		return new ResponseEntity<DogSitter>(createdDogSitter, HttpStatus.CREATED);
	}

	@PostMapping("/register/dogowner")
	public ResponseEntity<DogOwner> register(@RequestBody @Validated DogOwnerCreatePayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));
		DogOwner createdDogOwner = dogOwnerService.create(body);
		return new ResponseEntity<DogOwner>(createdDogOwner, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body)
			throws NotFoundException {

		//User user = userService.findByUserName(body.getUserName());
		User user = userService.findByEmail(body.getEmail());
		System.out.println(user);

		String plainPW = body.getPassword();
		String hashedPW = user.getPassword();

		if (!bcrypt.matches(plainPW, hashedPW))
			throw new UnauthorizedException("Credenziali non valide");

		String token = JWTTools.createToken(user);
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

	//	@PostMapping("/register")
	//	public ResponseEntity<User> register(@RequestBody @Validated UserCreatePayload body) {
	//
	//		body.setPassword(bcrypt.encode(body.getPassword()));
	//		User createdUser = userService.create(body);
	//		return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
	//	}
	//

	//	@PostMapping("/login/{type}")
	//	public ResponseEntity<AuthenticationSuccessfullPayload> login(@PathVariable String type,
	//			@RequestBody UserLoginPayload body) throws NotFoundException {
	//
	//		String plainPW;
	//		String hashedPW;
	//		String token = null;
	//
	//		switch (type) {
	//		case "DOGSITTER":
	//			DogSitter dogSitterFound = dogSitterService.readByEmail(body.getEmail());
	//
	//			plainPW = body.getPassword();
	//			hashedPW = dogSitterFound.getPassword();
	//
	//			if (!bcrypt.matches(plainPW, hashedPW))
	//				throw new UnauthorizedException("Credenziali non valide");
	//
	//			token = JWTTools.createToken(dogSitterFound);
	//			break;
	//		case "DOGOWNER":
	//			DogOwner dogOwnerFound = dogOwnerService.readByEmail(body.getEmail());
	//
	//			plainPW = body.getPassword();
	//			hashedPW = dogOwnerFound.getPassword();
	//
	//			if (!bcrypt.matches(plainPW, hashedPW))
	//				throw new UnauthorizedException("Credenziali non valide");
	//
	//			token = JWTTools.createToken(dogOwnerFound);
	//			break;
	//		}
	//
	//		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	//	}

	//	@PostMapping("/register")
	//	public ResponseEntity<User> register(@RequestBody @Validated UserCreatePayload body) {
	//
	//		body.setPassword(bcrypt.encode(body.getPassword()));
	//		User createdUser = userService.create(body);
	//		return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
	//	}
	//
	//	@PostMapping("/login")
	//	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body)
	//			throws NotFoundException {
	//
	//		User user = userService.findByUserName(body.getUserName());
	//
	//		String plainPW = body.getPassword();
	//		String hashedPW = user.getPassword();
	//
	//		if (!bcrypt.matches(plainPW, hashedPW))
	//			throw new UnauthorizedException("Credenziali non valide");
	//
	//		String token = JWTTools.createToken(user);
	//		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	//	}

}
