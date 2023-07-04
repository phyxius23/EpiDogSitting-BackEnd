package org.antoniotrentin.epidogsitting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.antoniotrentin.epidogsitting.entities.DogSitter;
import org.antoniotrentin.epidogsitting.repositories.DogSitterRepository;
import org.antoniotrentin.epidogsitting.services.DogSitterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
//@AutoConfigureMockMvc
class EpidogsittingApplicationTests {

	//	@Mock
	//	DogSitterRepository dogSitterRepo;

	//	@MockBean
	//	DogSitterService dogSitterService;
	//
	//	@Autowired
	//	private MockMvc mockMvc;
	//
	//	ObjectMapper mapper = new ObjectMapper();
	//
	//	@Test
	//	public void when_save_dogsitter_it_should_return_dogsitter() throws Exception {
	//		DogSitterCreatePayload request = new DogSitterCreatePayload();
	//		request.setName("Mario");
	//		//		request.setSurname("Rossi");
	//		//		request.setEmail("mariorossi@gmail.com");
	//		//		request.setPassword("1234");
	//
	//		DogSitter newDogSitter = new DogSitter();
	//		newDogSitter.setName(request.getName());
	//
	//		when(dogSitterService.create(any(DogSitterCreatePayload.class))).thenReturn(newDogSitter);
	//	}

	//	private String serviceUrl = "http://localhost:5432/";

	//	@Test
	//	void shouldGetDogSittersList() throws Exception {
	//		mockMvc.perform(MockMvcRequestBuilders.get("/dogsitters")).andExpect(status().is5xxServerError());
	//	}

	//@Autowired
	//	@Mock
	//	DogSitterRepository dogSitterRepo;

	//@Autowired
	//	@InjectMocks
	//	DogSitterService dogSitterService;

	//	@Test
	//	public void testGetDogSittersOK() throws Exception {
	//		String servicePath = "/dogsitters";
	//		int page = 0;
	//		int size = 10;
	//		String sortBy = "id";
	//		mockMvc
	//				.perform(get(serviceUrl + servicePath).param("page", String.valueOf(page)).param("size", String.valueOf(size))
	//						.param("sortBy", sortBy))
	//				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
	//	}

	//	@Test
	//	public void testFindAllDogSitters() {
	//
	//		// Mocking the repository
	//		Pageable pageable = PageRequest.of(0, 10, Sort.by("sortBy"));
	//		List<DogSitter> dogSitters = new ArrayList<>();
	//		dogSitters.add(new DogSitter());
	//		Page<DogSitter> page = new PageImpl(dogSitters, pageable, 1);
	//
	//		when(dogSitterRepo.findAll(pageable)).thenReturn(page);
	//
	//		// Calling the service method
	//		Page<DogSitter> result = dogSitterService.readAll(0, 10, "sortBy", "", "");
	//
	//		// Assertions
	//				assertNotNull(result);
	//				assertEquals(1, result.getTotalElements());
	//		// Additional assertions based on your expected data
	//
	//	}

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
	void contextLoads() {
	}

}
