//package org.antoniotrentin.epidogsitting.controllers;
//
//import java.util.UUID;
//
//import org.antoniotrentin.epidogsitting.entities.User;
//import org.antoniotrentin.epidogsitting.exceptions.NotFoundException;
//import org.antoniotrentin.epidogsitting.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
////@RestController
////@RequestMapping("/users")
//public class UserController {
//	@Autowired
//	private UserService userService;
//
//	// testata OK
//	@GetMapping("")
//	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
//			@RequestParam(defaultValue = "id") String sortBy) {
//		return userService.find(page, size, sortBy);
//	}
//
//	// testata OK
//	//	@PostMapping("")
//	//	@ResponseStatus(HttpStatus.CREATED)
//	//	public User saveUser(@RequestBody @Validated UserCreatePayload body) {
//	//		return userService.create(body);
//	//	}
//
//	// testata OK
//	@GetMapping("/{userId}")
//	public User getUser(@PathVariable UUID userId) throws Exception {
//		return userService.findById(userId);
//	}
//
//	//Request method 'PUT' is not supported  --> testata
//	@PutMapping("/{userId}")
//	public User updateUser(@PathVariable UUID userId, @RequestBody User body) throws Exception {
//		return userService.findByIdAndUpdate(userId, body);
//	}
//
//	// testata OK
//	@DeleteMapping("/{userId}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void deleteUser(@PathVariable UUID userId) throws NotFoundException {
//		userService.findByIdAndDelete(userId);
//	}
//
//}