package org.antoniotrentin.epidogsitting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.entities.payloads.DogSitterCreatePayload;
import org.antoniotrentin.epidogsitting.repositories.DogSitterRepository;
import org.antoniotrentin.epidogsitting.repositories.UserRepository;
import org.antoniotrentin.epidogsitting.services.DogSitterService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class EpidogsittingApplicationTests {

	private String serviceUrl = "http://localhost:3001/";

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserRepository userRepo;

	@Mock
	private DogSitterRepository dogSitterRepo;

	@InjectMocks
	private DogSitterService dogSitterService;

	@Test
	public void testReadAllDogSitters() {
		// Mocking the repository
		Pageable pageable = PageRequest.of(0, 10, Sort.by("sortBy"));
		List<DogSitter> dogSitters = new ArrayList<>();
		dogSitters.add(new DogSitter("Aldo", "Baglio", "aldobaglio@gmail.com", "1234"));
		Page<DogSitter> page = new PageImpl<>(dogSitters, pageable, 1);

		when(dogSitterRepo.findAll(pageable)).thenReturn(page);

		// Calling the service method
		Page<DogSitter> result = dogSitterService.readAll(0, 10, "sortBy", "", "", null);

		// Assertions
		assertNotNull(result);
		assertEquals(1, result.getTotalElements());
		// Additional assertions based on your expected data
	}

	@Test
	public void testCreateDogSitter() {
		// Mocking the repository
		DogSitterCreatePayload payload = new DogSitterCreatePayload();
		payload.setName("Test");
		payload.setSurname("User");
		payload.setEmail("testuser@example.com");
		payload.setPassword("password");
		when(dogSitterRepo.save(Mockito.any(DogSitter.class))).thenReturn(new DogSitter());

		// Calling the service method
		DogSitter result = dogSitterService.create(payload);

		// Assertions
		assertNotNull(result);
		// Additional assertions based on your expected data
	}

	// Manca l'autorizzazione nell'header per poter eseguire il test correttamente
	@Disabled
	@Test
	public void testGetDogSitters() throws Exception {
		String servicePath = "/dogsitters";
		int page = 0;
		int size = 10;
		String sortBy = "id";
		mockMvc
				.perform(get(serviceUrl + servicePath).param("page", String.valueOf(page)).param("size", String.valueOf(size))
						.param("sortBy", sortBy))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void contextLoads() {
	}

}
