package cyrillic_software_test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import cyrillic_software.CyrillicSoftware;
import cyrillic_software.models.Account;
import cyrillic_software.models.User;
import cyrillic_software.services.AccountService;
import cyrillic_software.utils.DatabaseTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CyrillicSoftware.class)
@AutoConfigureMockMvc
public class AccountTest {
	
	@Autowired
	MockMvc mockMvc;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Before
	public void setUpAccount() { 
		accountService.add(new Account("type1", new User("email1@gmail.com", "User1", "12345678")));
		accountService.add(new Account("type2", new User("email2@gmail.com", "User2", "12345678")));
		accountService.add(new Account("type3", new User("email3@gmail.com", "User3", "12345678")));
		accountService.add(new Account("type4", new User("email4@gmail.com", "User4", "12345678")));
		accountService.add(new Account("type5", new User("email5@gmail.com", "User5", "12345678")));
	}
	
	@After
	public void afterTeacher() throws SQLException {
		DatabaseTestUtils.resetAutoIncrementColumns(applicationContext, "account", "user");
	}
	
	@Test
	public void getAccount() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/account/all").accept(MediaType.APPLICATION_JSON_UTF8))
			.andDo(print())
			.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}

}
