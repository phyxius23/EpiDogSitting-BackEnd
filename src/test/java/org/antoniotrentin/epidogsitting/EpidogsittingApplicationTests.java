package org.antoniotrentin.epidogsitting;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class EpidogsittingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private String serviceUrl = "http://localhost:5432/";

	@Test
	void shouldGetDogSittersList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/dogsitters")).andExpect(status().is5xxServerError());
	}

	//@Autowired
	//	@Mock
	//	DogSitterRepository dogSitterRepo;

	//@Autowired
	//	@InjectMocks
	//	DogSitterService dogSitterService;

	@Test
	void contextLoads() {
	}

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
}
